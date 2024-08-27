package com.cgi.fsdc.model;

import com.cgi.fsdc.utilities.enums.Currency;
import lombok.Data;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class TransactionRequest {

    @NotNull(message = "{transactionId.notNull}")
    @Min(value = 1, message = "{transactionId.positive}")
    private Integer transactionId;

    @NotNull(message = "{amount.notNull}")
    @DecimalMin(value = "0.0", inclusive = false, message = "{amount.positive}")
    @Digits(integer = 10, fraction = 2, message = "{amount.format}")
    private BigDecimal amount;

    @NotNull(message = "{currency.notNull}")
    private Currency currency;

    @NotNull(message = "{customerName.notNull}")
    private Integer customerId;

    @NotNull(message = "{deviceId.notNull}")
    private String deviceId;

    @NotNull(message = "{status.notNull}")
    private String status;

    @NotNull(message = "{cardNumber.notNull}")
    @Pattern(regexp = "^[0-9]{16}$", message = "{cardNumber.invalid}")
    private String cardNumber;

    @NotNull(message = "{expiryDate.notNull}")
    private LocalDate expiryDate;
}
