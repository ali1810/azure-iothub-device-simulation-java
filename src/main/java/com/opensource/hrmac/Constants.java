package com.opensource.hrmac;

import com.microsoft.azure.iothub.IotHubClientProtocol;

/**
 * 
 * @author hramc
 * 
 * Constants
 *
 */
public interface Constants {
	
		// Connection string of the IOTHub
		String connectionString = "{your IOTHub Connection String}";
		
		// IOT client protocal used to connect IOT 
		IotHubClientProtocol protocol = IotHubClientProtocol.MQTT;

		//No of simulated devices (make sure you have already created devices using createDevice 
		int noOfDevice = 5;
		
		// Time Interval to send simulated information
		int interval_seconds = 10;
		
		// application will add the number at the end to create unique devices
		String deviceIdPrefix = "{deviceIdPrefix}";
		
		// Avg temperature to used to create mock data
		double avgTemp = 70;
		// Avg humidity to use to create mock data
		double avgHumidity=10;
		// scale
		int scale = 10;
		

}
