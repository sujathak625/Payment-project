package com.cgi.fsdc.controller;

import com.cgi.fsdc.model.TransactionRequest;
import com.cgi.fsdc.service.TransactionService;
import com.cgi.fsdc.service.FraudDetectionService;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Locale;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

	private final TransactionService transactionService;
	private final FraudDetectionService fraudDetectionService;
	private final MessageSource messageSource;

	public TransactionController(TransactionService transactionService, FraudDetectionService fraudDetectionService, MessageSource messageSource) {
		this.transactionService = transactionService;
		this.fraudDetectionService = fraudDetectionService;
		this.messageSource = messageSource;
	}

	@PostMapping("/create")
	public ResponseEntity<String> createTransaction(@RequestBody TransactionRequest transactionRequest) {
		transactionService.saveTransactionDetails(transactionRequest);
		String successMessage = messageSource.getMessage("success.transaction.created", null, Locale.getDefault());
		return ResponseEntity.ok(successMessage);
	}

	@GetMapping("/fraud-detection/{customerId}")
	public ResponseEntity<String> detectFraud(@PathVariable Integer customerId) {
		boolean isFraudulent = fraudDetectionService.detectFraud(customerId);
		if (isFraudulent) {
			String fraudMessage = messageSource.getMessage("fraud.detection.failure", new Object[]{customerId}, Locale.getDefault());
			return ResponseEntity.status(403).body(fraudMessage);
		} else {
			String successMessage = messageSource.getMessage("fraud.detection.success", new Object[]{customerId}, Locale.getDefault());
			return ResponseEntity.ok(successMessage);
		}
	}
}
