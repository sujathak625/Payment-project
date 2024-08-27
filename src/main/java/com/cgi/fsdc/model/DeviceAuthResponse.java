package com.cgi.fsdc.model;

import com.cgi.fsdc.utilities.YesNo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceAuthResponse {
    private YesNo status;
    private String message;
}
