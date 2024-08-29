package com.cgi.fsdc.serviceImpl;

import com.cgi.fsdc.entity.Transaction;
import com.cgi.fsdc.model.request.TransactionRequest;
import com.cgi.fsdc.repository.TransactionRepository;
import com.cgi.fsdc.service.CustomerService;
import com.cgi.fsdc.service.Impl.TransactionServiceImpl;
import com.cgi.fsdc.service.TransactionService;
import com.cgi.fsdc.utilities.SecurityUtils;
import com.cgi.fsdc.utilities.enums.Currency;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class TransactionServiceImplTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private CustomerService customerService;

    private TransactionService transactionService;

    public TransactionServiceImplTest() {
        MockitoAnnotations.openMocks(this);
        transactionService = new TransactionServiceImpl(transactionRepository, customerService);
    }

    @Test
    void testBlockCard_Success() {
        int customerId = 1001;
        when(customerService.blockCard(eq(customerId))).thenReturn(true);
        boolean result = transactionService.blockCard(customerId, true);
        assertTrue(result);
        verify(customerService, times(1)).blockCard(eq(customerId));
    }

    @Test
    void testBlockCard_Failure() {
        int customerId = 1001;
        when(customerService.blockCard(eq(customerId))).thenReturn(false);
        boolean result = transactionService.blockCard(customerId, true);
        assertTrue(!result);
        verify(customerService, times(1)).blockCard(eq(customerId));
    }
}
