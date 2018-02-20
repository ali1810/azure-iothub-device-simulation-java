package com.opensource.hrmac;

import com.google.gson.Gson;

/**
 * Telemetry Data Point
 * @author hramc
 *
 */
public class TelemetryDataPoint {
	   public String deviceId;
	   public double temparature;
	   public double humidity;
	   public String hostIP;

	   public String serialize() {
	     Gson gson = new Gson();
	     return gson.toJson(this);
	   }

}
