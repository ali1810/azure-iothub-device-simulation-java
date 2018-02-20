package com.opensource.hrmac;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

import com.microsoft.azure.iothub.DeviceClient;
import com.microsoft.azure.iothub.Message;
import com.microsoft.azure.sdk.iot.service.Device;

/**
 * Message Sender
 * 
 * @author hramc
 *
 */
public class MessageSender implements Runnable {
	public volatile boolean stopThread = false;
	private String deviceId;
	private DeviceClient client;

	/**
	 * constructor
	 * @param deviceId
	 * @param client
	 * 
	 */
	public MessageSender(String deviceID,DeviceClient client) {
		this.deviceId=deviceID;
		this.client = client;
	}

	public void run() {
		try {
			Random rand = new Random();
			while (!stopThread) {
				double currentTemparature = Constants.avgTemp + rand.nextDouble() * Constants.scale;
				double currentHumidity = Constants.avgHumidity + rand.nextDouble() * Constants.scale;
				TelemetryDataPoint telemetryDataPoint = new TelemetryDataPoint();
				telemetryDataPoint.deviceId = deviceId;
				telemetryDataPoint.temparature = currentTemparature;
				telemetryDataPoint.humidity = currentHumidity;
				try {
					telemetryDataPoint.hostIP = InetAddress.getLocalHost().getHostAddress();
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				String msgStr = telemetryDataPoint.serialize();
				Message msg = new Message(msgStr);
				System.out.println("Sending: " + msgStr);

				Object lockobj = new Object();
				EventCallback callback = new EventCallback();
				client.sendEventAsync(msg, callback, lockobj);

				synchronized (lockobj) {
					lockobj.wait();
				}
				Thread.sleep(Constants.interval_seconds*1000);
			}
		} catch (InterruptedException e) {
			System.out.println("Finished.");
		}
	}
}