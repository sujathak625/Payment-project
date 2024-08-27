package com.cgi.fsdc.service.Impl;

import com.cgi.fsdc.entity.Transaction;
import com.cgi.fsdc.repository.TransactionRepository;
import com.cgi.fsdc.service.FraudDetectionService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FraudDetectionServiceImpl implements FraudDetectionService {

    private final TransactionRepository transactionRepository;

    public FraudDetectionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public boolean detectFraud(Integer customerId) {
        LocalDateTime twoHoursAgo = LocalDateTime.now().minusHours(2);
        List<Transaction> transactions = transactionRepository.findByCustomerIdAndCreateTimeAfter(customerId, twoHoursAgo);
        long deviceCount = transactions.stream()
                .map(Transaction::getDeviceId)
                .distinct()
                .count();
        return transactions.size() >= 50 && deviceCount > 1;
    }
}
