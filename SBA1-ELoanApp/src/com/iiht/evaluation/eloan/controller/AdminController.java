package com.iiht.evaluation.eloan.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iiht.evaluation.eloan.dao.ConnectionDao;
import com.iiht.evaluation.eloan.exception.ELoanException;
import com.iiht.evaluation.eloan.model.Attributes;
import com.iiht.evaluation.eloan.model.LoanInfo;
import com.iiht.evaluation.eloan.service.AdminImpl;
import com.iiht.evaluation.eloan.service.IAdminAuth;



@WebServlet({"/admin","/listall","/calemi","/processloan","/rejectloan","/updatestatus"})
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ConnectionDao connDao;
	//
	private IAdminAuth adminDao;
	
	public AdminController() {
		this.adminDao = new AdminImpl();
	}

	
	public void setConnDao(ConnectionDao connDao) {
		this.connDao = connDao;
	}
	
	HttpSession session = null;
	LoanInfo loanInf = new LoanInfo();
	
	public void init(ServletConfig config) {
		String jdbcURL = config.getServletContext().getInitParameter("jdbcUrl");
		String jdbcUsername = config.getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = config.getServletContext().getInitParameter("jdbcPassword");
		System.out.println(jdbcURL + jdbcUsername + jdbcPassword);
		this.connDao = new ConnectionDao(jdbcURL, jdbcUsername, jdbcPassword);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		session = request.getSession();
		String incomingAction = request.getServletPath();
		System.out.println("incomingAction:"+incomingAction);
		String viewName = "";
		try {
			switch (incomingAction) {
			case "/listall" : 
				viewName = listall(request, response);
				break;
			case "/processloan":
				System.out.println("in step 1:"+request.getParameter("applId"));
				viewName=process(request,response);
				break;
			case "/calemi":
				viewName=calemi(request,response);
				break;
			case "/rejectloan":
				viewName=rejectloan(request,response);
				break;
			case "/updatestatus":
				viewName=updatestatus(request,response);
				break;
			case "/logout":
				viewName = adminLogout(request, response);
				break;	
			default : viewName = "notfound.jsp"; break;		
			}
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
		RequestDispatcher dispatch = 
					request.getRequestDispatcher(viewName);
		dispatch.forward(request, response);
		
		
	}

	private String rejectloan(HttpServletRequest request, HttpServletResponse response) throws ELoanException {
		System.out.println("In reject method-applId:"+request.getParameter("applId"));
		String applId = (request.getParameter("applId"));
		System.out.println("In reject method-Applno:"+applId);
		try{
			Connection conn = ConnectionDao.connect(); 
			PreparedStatement ps = conn.prepareStatement(Attributes.UPD_REJ_LOAN_STATUS);			
			
			ps.setString(1, applId);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			throw new ELoanException("SQL Exception: Loan not processed. Try again");
		}
		request.setAttribute("isApproved", false);

		return "adminhome1.jsp";
	}


	private String updatestatus(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		/* write the code for updatestatus of loan and return to admin home page */
		
		return null;
	}
	private String calemi(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
	/* write the code to calculate emi for given applno and display the details */
		
		return null;
	}
	private String process(HttpServletRequest request, HttpServletResponse response) throws SQLException, ELoanException {
		//System.out.println("In process method "+request.getParameter("applId"));
		String applId = (request.getParameter("applId"));
		System.out.println("Applno:"+applId);
		try{
			Connection conn = ConnectionDao.connect(); 
			PreparedStatement ps = conn.prepareStatement(Attributes.UPD_LOAN_STATUS);			
			
			ps.setString(1, applId);
			ps.executeUpdate();
			
			String a = calculateLoanInfo(applId);
			
			//ps = conn.prepareStatement(Attributes.INS_LOAN_INFO);
		
		} catch (SQLException e) {
			throw new ELoanException("SQL Exception: Loan request not submitted. Try again");
		}

		request.setAttribute("isApproved", true);

		return  "calemi.jsp";
	}
	private String calculateLoanInfo(String applId) throws ELoanException {
		
		//,LOAN_CLOSURE_DT,LOAN_EMI_AMT,TOTAL_PAYMENT_AMT,LOAN_INTEREST_RATE
		loanInf = getLoanDetails(applId);
		
		int interestRate = 10;
		
		int totalPaymentAmt = (int) ((loanInf.getAmtrequest()) * Math.pow((1+interestRate/100),loanInf.getTerm()/12));
		System.out.println("totalPaymentAmt:"+totalPaymentAmt);
		
		int loanEmiAmt = totalPaymentAmt / (loanInf.getTerm()/12);
		System.out.println("loanEmiAmt:"+loanEmiAmt);
		
		LocalDate loanClosureDt = LocalDate.now().plusMonths(loanInf.getTerm()+1);
		System.out.println("loanClosureDt:"+loanClosureDt);
		
		try(Connection con = ConnectionDao.connect();
				PreparedStatement pst = con.prepareStatement(Attributes.INS_LOAN_INFO)){
			pst.setString(1, applId);
			pst.setInt(2, loanInf.getAmtrequest());
			pst.setInt(3, loanInf.getTerm());
			pst.setDate(4, Date.valueOf(loanClosureDt));
			pst.setInt(5, loanEmiAmt );
			pst.setInt(6, totalPaymentAmt );
			pst.setInt(7, interestRate);
			
			pst.executeUpdate();
			
			
			System.out.println("Approved Loan inserted");
			
		} catch (Exception e) {
			throw new ELoanException("SQL Exception: Cannot process Loan");
		}
		return "Success";
	}
	
	


		private LoanInfo getLoanDetails(String applId) throws ELoanException {
			loanInf = new LoanInfo();
			try(Connection con = ConnectionDao.connect();
					PreparedStatement pst = con.prepareStatement(Attributes.GET_LOAN_APPL_INFO)){
				pst.setString(1, applId);
				ResultSet rs = pst.executeQuery();
				
				while(rs.next()) {
				loanInf.setApplno(rs.getString("LOAN_APPL_ID"));
				loanInf.setUsername(rs.getString("USER_NAME"));
				loanInf.setStatus(rs.getString("STATUS"));
				loanInf.setAmtrequest(rs.getInt("LOAN_AMOUNT"));
				loanInf.setDoa(rs.getString("LOAN_APPL_DATE"));
				loanInf.setMobile(rs.getString("MOBILE"));
				loanInf.setAddress(rs.getString("ADDRESS"));
				loanInf.setTerm(rs.getInt("LOAN_TERM_MNTHS"));
				loanInf.setPurpose(rs.getString("PURPOSE"));
				loanInf.setBindicator(rs.getString("LOAN_BILL_IND"));
				loanInf.setBstructure(rs.getString("LOAN_BUS_STRC"));
				}
			}catch(SQLException e){
					throw new ELoanException("SQL Exception: Unable to get loan details");
				}
			return loanInf;
	}


	private String adminLogout(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	/* write code to return index page */
		return null;
	}

	private String listall(HttpServletRequest request, HttpServletResponse response) throws SQLException, ELoanException {
	/* write the code to display all the loans */
		request.setAttribute("dispLoansInfo", adminDao.getAll());
		return "listall.jsp";
	}

	public List<LoanInfo> getAll() throws ELoanException {
		return LoanInfo.getAll();
	}
	
}