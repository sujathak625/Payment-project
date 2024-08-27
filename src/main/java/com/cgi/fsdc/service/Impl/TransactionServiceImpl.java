package com.cgi.fsdc.service.Impl;

import com.cgi.fsdc.entity.Transaction;
import com.cgi.fsdc.model.TransactionRequest;
import com.cgi.fsdc.repository.TransactionRepository;
import com.cgi.fsdc.service.TransactionService;
import com.cgi.fsdc.utilities.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.Instant;

@Service
public class TransactionServiceImpl implements TransactionService {

	private final TransactionRepository transactionRepository;

	public TransactionServiceImpl(TransactionRepository transactionRepository) {
		this.transactionRepository = transactionRepository;
	}

	@Override
	@Transactional
	public void saveTransactionDetails(TransactionRequest transactionRequest) {
		String encryptedCardNumber = SecurityUtils.encrypt(transactionRequest.getCardNumber());

		Transaction transaction = new Transaction();
		transaction.setAmount(transactionRequest.getAmount());
		transaction.setCurrency(transactionRequest.getCurrency());
		transaction.setCustomerName(transactionRequest.getCustomerName());
		transaction.setDeviceId(transactionRequest.getDeviceId());
		transaction.setStatus(transactionRequest.getStatus());
		transaction.setCardNumber(encryptedCardNumber);
		transaction.setExpiryDate(transactionRequest.getExpiryDate());
		transaction.setCreateTime(Instant.now());
		transactionRepository.save(transaction);
	}
}
