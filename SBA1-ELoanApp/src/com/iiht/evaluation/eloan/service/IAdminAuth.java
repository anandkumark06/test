package com.iiht.evaluation.eloan.service;

import java.util.List;

import com.iiht.evaluation.eloan.exception.ELoanException;
import com.iiht.evaluation.eloan.model.LoanInfo;

public interface IAdminAuth {
	
	List<LoanInfo> getAll() throws ELoanException;

}