package com.opensource.hrmac;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.gson.JsonSyntaxException;
import com.microsoft.azure.iothub.DeviceClient;
import com.microsoft.azure.iothub.IotHubClientProtocol;
import com.microsoft.azure.sdk.iot.service.Device;
import com.microsoft.azure.sdk.iot.service.RegistryManager;
import com.microsoft.azure.sdk.iot.service.exceptions.IotHubException;

/**
 * Simulator App
 * @author hramc
 * 
 * Code the send simulated device messages to IOT hub.
 *
 */
public class App {
		
	/**
	 * Code to send simulated device information to IOT 
	 * @param args
	 * @throws IOException
	 */
	public static void main( String[] args ) throws IOException
    {
        System.out.println( "Temparature Simulator" );

        ArrayList<Device> device = new ArrayList<Device>();
   	 	DeviceClient[] client = new DeviceClient[Constants.noOfDevice];
   	 	MessageSender[] sender = new MessageSender[Constants.noOfDevice];
   	 	ExecutorService[] executor = new ExecutorService[Constants.noOfDevice];
   	 	
		RegistryManager registryManager = RegistryManager.createFromConnectionString(Constants.connectionString);
		try {
			device = registryManager.getDevices(Constants.noOfDevice);
			for(int i=0;i<Constants.noOfDevice;i++) {
				Device d = device.get(i);
				client[i] = new DeviceClient(registryManager.getDeviceConnectionString(d), Constants.protocol);
				client[i].open();
				sender[i] = new MessageSender(d.getDeviceId(),client[i]);
				executor[i] = Executors.newFixedThreadPool(1);
		        executor[i].execute(sender[i]);
			}
	
	        System.out.println("Press ENTER to exit.");
	        System.in.read();
	        for(int i=0;i<Constants.noOfDevice;i++) {
		        executor[i].shutdownNow();
		        client[i].close();
	        }
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IotHubException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
