package com.cgi.fsdc.service;

import com.cgi.fsdc.model.TransactionRequest;

public interface TransactionService {

	void saveTransactionDetails(TransactionRequest transactionRequest);
}