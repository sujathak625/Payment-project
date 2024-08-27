package com.cgi.fsdc.service;

import com.cgi.fsdc.model.DeviceAuthResponse;

public interface DeviceService {

	DeviceAuthResponse deviceAuthentication(int customerId, String deviceId);
}



