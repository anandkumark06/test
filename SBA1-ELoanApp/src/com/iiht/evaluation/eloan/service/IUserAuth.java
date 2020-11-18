package com.iiht.evaluation.eloan.service;

import com.iiht.evaluation.eloan.exception.ELoanException;
import com.iiht.evaluation.eloan.model.User;

public interface IUserAuth {
	
	User authenticate(String userName,String password) throws ELoanException;
	User applyLoan(User user)throws ELoanException;
}