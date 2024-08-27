package com.cgi.fsdc.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceAuthRequest {

    @NotNull(message = "{customerId.notNull}")
    @Min(value = 1, message = "{customerId.min}")
    private Integer customerId;

    @NotNull(message = "{deviceId.notNull}")
    @NotEmpty(message = "{deviceId.notEmpty}")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "{deviceId.pattern}")
    private String deviceId;
}
