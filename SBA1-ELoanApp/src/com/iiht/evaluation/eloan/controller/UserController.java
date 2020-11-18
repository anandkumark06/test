package com.iiht.evaluation.eloan.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

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
import com.iiht.evaluation.eloan.model.User;
import com.iiht.evaluation.eloan.service.IUserAuth;
import com.iiht.evaluation.eloan.service.UserAuth;



@WebServlet({ "/login","/logout","/registernewuser","/placeloan","/trackloan","/editLoanProcess","/editloan"})
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
private ConnectionDao connDao;
	
	public void setConnDao(ConnectionDao connDao) {
		this.connDao = connDao;
	}
	
	IUserAuth userAuth = null;
	User user = new User();
	HttpSession session = null;
	
	public void init(ServletConfig config) {
		String jdbcURL = config.getServletContext().getInitParameter("jdbcUrl");
		String jdbcUsername = config.getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = config.getServletContext().getInitParameter("jdbcPassword");
		System.out.println(jdbcURL + jdbcUsername + jdbcPassword);
		this.connDao = new ConnectionDao(jdbcURL, jdbcUsername, jdbcPassword);
		
		userAuth = new UserAuth();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		session = request.getSession();
		String incomingAction = request.getServletPath();
		System.out.println("action:"+incomingAction);
		String viewName = "";
		try {
			switch (incomingAction) {
			case "/registernewuser":
				viewName=registernewuser(request,response);
				break;
			case "/login":
				user = new User();
				user.setUsername(request.getParameter("loginid"));
				user.setPassword(request.getParameter("password"));
				user.setEmailId(request.getParameter("emailId"));
				System.out.println("User Controller:"+request.getParameter("loginid")+":::"+request.getParameter("password"));
				user = userAuth.authenticate(user.getUsername(),user.getPassword());
				if((user.getRole()).equals("user")) {
					session.setAttribute("user", user);
					viewName="userhome.jsp";
				}else {
					session.setAttribute("admin", user);
					viewName="adminhome1.jsp";
				}
				break;
			case "/validate":
				viewName=validate(request,response);
				break;
			case "/placeloan":
				viewName=placeloan(request,response);
				break;
			case "/application1":
				viewName=application1(request,response);
				break;
			case "/editLoanProcess"  :
				viewName=editLoanProcess(request,response);
				break;
			case "/registeruser":
				viewName=registerUser(request,response);
				break;
			case "/register":
				viewName = register(request, response);
				break;
			case "/application":
				viewName = application(request, response);
				break;
			case "/trackloan":
				viewName = trackloan(request, response);
				break;
			case "/editloan":
				viewName = editloan(request, response);
				break;	
			case  "/displaystatus" :
				viewName=displaystatus(request,response);
				break;
			case  "/logout" :
				viewName="index.jsp";
				session.removeAttribute("user");
				session.removeAttribute("admin");
				break;
			default : viewName = "notfound.jsp"; break;	
			}
		} catch (Exception ex) {
			
			throw new ServletException(ex.getMessage());
		}
			RequestDispatcher dispatch = request.getRequestDispatcher(viewName);
			dispatch.forward(request, response);
	}

	private String validate(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		/* write the code to validate the user */
		
		return null;
	}
	private String placeloan(HttpServletRequest request, HttpServletResponse response) throws ELoanException {
		/* write the code to place the loan information */
		LoanInfo newLoan = new LoanInfo();
		String vw;
		newLoan.setPurpose(request.getParameter("purpose"));
		newLoan.setAmtrequest(Integer.parseInt(request.getParameter("amtRequested")));
		newLoan.setBstructure(request.getParameter("bstructure"));
		newLoan.setBindicator(request.getParameter("bindicator"));
		newLoan.setMobile(request.getParameter("mobile"));
		newLoan.setEmail(request.getParameter("email"));
		newLoan.setAddress(request.getParameter("address"));
		newLoan.setTerm(Integer.parseInt(request.getParameter("term")));

			try{
					Connection conn = ConnectionDao.connect(); 
					PreparedStatement ps = conn.prepareStatement(Attributes.INS_APPLY_LOAN);			
				ps.setString(1, user.getUsername());
				ps.setString(2, request.getParameter("loanName"));
				ps.setInt(3, newLoan.getAmtrequest());
				ps.setString(4, newLoan.getBstructure());
				ps.setString(5, newLoan.getBindicator());
				ps.setString(6, newLoan.getMobile());
				ps.setString(7, newLoan.getAddress());
				ps.setString(8, newLoan.getPurpose());
				ps.setInt(9, newLoan.getTerm());
				
				ps.executeUpdate();
				vw = "userhome1.jsp";
				
				} catch (SQLException e) {
					throw new ELoanException("SQL Exception: Loan request not submitted. Try again");
				}

		return vw;
	}

	private String getUserInfo(String userName,String returnColumn) throws ELoanException, SQLException {
		System.out.println("returnColumn:"+returnColumn);
		System.out.println("userName:"+userName);

		String returnValue;
		try(Connection con = ConnectionDao.connect();
				PreparedStatement pst = con.prepareStatement(Attributes.GET_USER_INFO)){

			pst.setString(1, userName);
			ResultSet rs = pst.executeQuery();
			returnValue = rs.getString("EMAIL_ID");
			//returnValue = rs.getString(returnColumn);
			System.out.println("returnValue:"+returnValue);
		}
		return returnValue;
		
	}

	private String application1(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	/* write the code to display the loan application page */
		
		return null;
	}
	private String editLoanProcess(HttpServletRequest request, HttpServletResponse response) throws SQLException, ELoanException {
		/* write the code to edit the loan info */
		LoanInfo editLoanInfo = new LoanInfo();
		editLoanInfo.setPurpose(request.getParameter("purpose"));
		editLoanInfo.setAmtrequest(Integer.parseInt(request.getParameter("amtrequest")));
		editLoanInfo.setBstructure(request.getParameter("bstructure"));
		editLoanInfo.setBindicator(request.getParameter("bindicator"));
		editLoanInfo.setAddress(request.getParameter("address"));

		try{
			Connection conn = ConnectionDao.connect(); 
			PreparedStatement ps = conn.prepareStatement(Attributes.UPD_LOAN_REQ);	
			System.out.println("In Edit Loan processmethod 3");

		ps.setString(6, user.getUsername());
		ps.setInt(1, editLoanInfo.getAmtrequest());
		ps.setString(2, editLoanInfo.getAddress());
		ps.setString(3, editLoanInfo.getPurpose());
		ps.setString(4, editLoanInfo.getBstructure());
		ps.setString(5, editLoanInfo.getBindicator());
		System.out.println("EditLoan - Update done");
		
		ps.executeUpdate();
		} catch (SQLException e) {
			throw new ELoanException("SQL Exception: Unable to update loan details");		}
		
		return "editloan.jsp";
	}
	private String registerUser(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		/* write the code to redirect page to read the user details */
		return "newuserui.jsp";
	}
	private String registernewuser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		System.out.println("In registernewuser method");
		/* write the code to create the new user account read from user and return to index page */
		User newUser = new User();
		
		newUser.setUsername(request.getParameter("userName"));
		newUser.setDateOfBirth(LocalDate.parse(request.getParameter("dateOfBirth")));
		newUser.setGender(request.getParameter("gender"));
		newUser.setEmailId(request.getParameter("emailId"));
		newUser.setPassword(request.getParameter("pwd"));
		System.out.println("user:"+request.getParameter("userName"));

				
				try{
					Connection conn = ConnectionDao.connect(); 
					PreparedStatement ps = conn.prepareStatement(Attributes.INS_NEW_USER);			
				System.out.println("connection established to DB");
				System.out.println("newUser.getUsername():"+newUser.getUsername());
				ps.setString(1, newUser.getUsername());
				ps.setDate(2, Date.valueOf(newUser.getDateOfBirth()));
				ps.setString(3, newUser.getGender());
				ps.setString(4, newUser.getEmailId());
				ps.setString(5, newUser.getPassword());
				
				ps.executeUpdate();
				
				ps = conn.prepareStatement(Attributes.INS_ROLES);
				ps.setString(1, newUser.getUsername());
				ps.setString(2, newUser.getPassword());
				ps.executeUpdate();
				
				} catch (SQLException e) {
					e.printStackTrace();
				}


		request.setAttribute("newUser", newUser);
		//request.getRequestDispatcher("newuserui.jsp").forward(request, response);
		
		return "newuserui.jsp";
	}
	
	private String register(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		/* write the code to redirect to register page */
		
		return null;
	}
	private String displaystatus(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		/* write the code the display the loan status based on the given application
		   number 
		*/
		
		return null;
	}

	private String editloan(HttpServletRequest request, HttpServletResponse response) throws ELoanException {
	/* write a code to return to editloan page */
		LoanInfo inf = getLoanDetails();
		if((inf.getStatus()).equalsIgnoreCase("Open")) {
			System.out.println("Loan status : "+inf.getStatus());
		} else {
			throw new ELoanException("You cannot edit/update a processed loan.");
		}
		return "editloanui.jsp";
	}

	private String trackloan(HttpServletRequest request, HttpServletResponse response) throws ELoanException {
	/* write a code to return to trackloan page */
		getLoanDetails();
		return "trackloan.jsp";
	}

	private LoanInfo getLoanDetails() throws ELoanException {
		LoanInfo loanInf = new LoanInfo();
		try(Connection con = ConnectionDao.connect();
				PreparedStatement pst = con.prepareStatement(Attributes.GET_LOAN_INFO)){
			//,,,,,STATUS
		pst.setString(1, user.getUsername());
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
			loanInf.setApplno(rs.getString("LOAN_APPL_ID"));
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
			session.setAttribute("loanDetails", loanInf);
		}catch(SQLException e){
				throw new ELoanException("SQL Exception: Unable to get loan details");
			}
		return loanInf;
		
	}

	private String application(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	/* write a code to return to trackloan page */
		return null;
	}
}