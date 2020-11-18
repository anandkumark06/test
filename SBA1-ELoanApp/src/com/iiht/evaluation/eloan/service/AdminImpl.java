package com.iiht.evaluation.eloan.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.iiht.evaluation.eloan.dao.ConnectionDao;
import com.iiht.evaluation.eloan.exception.ELoanException;
import com.iiht.evaluation.eloan.model.Attributes;
import com.iiht.evaluation.eloan.model.LoanInfo;


public class AdminImpl implements IAdminAuth{

	
	@Override
	public List<LoanInfo> getAll() throws ELoanException {
			List<LoanInfo> openLoans=new ArrayList<LoanInfo>();
			
			LoanInfo dispLoan = new LoanInfo();
			ResultSet rs ;
			try{
				Connection conn = ConnectionDao.connect(); 
				PreparedStatement ps = conn.prepareStatement(Attributes.GET_OPEN_LOANS);
			
			rs = ps.executeQuery();
			while(rs.next()) {
				dispLoan.setApplno(rs.getString("LOAN_APPL_ID"));
				dispLoan.setUsername(rs.getString("USER_NAME"));
				dispLoan.setStatus(rs.getString("STATUS"));
				dispLoan.setAmtrequest(rs.getInt("LOAN_AMOUNT"));
				dispLoan.setDoa(rs.getString("LOAN_APPL_DATE"));
				dispLoan.setMobile(rs.getString("MOBILE"));
				dispLoan.setAddress(rs.getString("ADDRESS"));
				dispLoan.setPurpose(rs.getString("PURPOSE"));
				dispLoan.setBindicator(rs.getString("LOAN_BILL_IND"));
				dispLoan.setBstructure(rs.getString("LOAN_BUS_STRC"));
				
				openLoans.add(dispLoan);
				}
			} catch (SQLException e) {
				throw new ELoanException("SQL Exception: Unable to fetch loans information. Try again");
			}
			return openLoans;
	}

	
	}
