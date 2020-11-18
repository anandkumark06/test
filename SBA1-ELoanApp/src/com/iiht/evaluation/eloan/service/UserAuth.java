package com.iiht.evaluation.eloan.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.iiht.evaluation.eloan.dao.ConnectionDao;
import com.iiht.evaluation.eloan.exception.ELoanException;
import com.iiht.evaluation.eloan.model.Attributes;
import com.iiht.evaluation.eloan.model.User;


public class UserAuth implements IUserAuth{

	private static String GET_USER_QRY=
			"SELECT * FROM adb_users WHERE user_name=?";
	private static String ADD_USER_QRY=
			"INSERT INTO adb_users VALUES(?,?,?)";
	
	@Override
	public User authenticate(String userName,String password) throws ELoanException {
		User user=null;
		try(Connection con = ConnectionDao.connect();
				PreparedStatement pst = con.prepareStatement(Attributes.GET_ROLE_INFO)){
			pst.setString(1, userName);
			ResultSet rs = pst.executeQuery();
			boolean rsNextExists = rs.next();

			user = new User();
			if(userName.equalsIgnoreCase("admin")) {
				user.setRole("admin");
			} else  {
				if(!rsNextExists) {
					System.out.println("Username not in db");
					throw new ELoanException("User doesn't exist");
				}else {
				user.setUsername(rs.getString(1));
				user.setPassword(rs.getString(2));
				user.setRole(rs.getString(3));
				if (!(userName.equalsIgnoreCase(user.getUsername()) && password.equals(user.getPassword()))) {
					System.out.println("Username password dont match with db");
					throw new ELoanException("Invalid user credentials");
				}else {
					System.out.println("Username password are matching with db");
				}
		} }
			}catch (SQLException e) {
			throw new ELoanException("SQL Exception");
		}
		return user;
	}

	@Override
	public User applyLoan(User user) throws ELoanException {		
		try(Connection con = ConnectionDao.connect();
				PreparedStatement pst = con.prepareStatement(ADD_USER_QRY)){
			
			pst.setString(1, user.getUsername());
			pst.setString(2, user.getPassword());
			pst.setString(3, user.getRole());
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
			throw new ELoanException("User details could not be saved");
		}
		return user;
	}

}
