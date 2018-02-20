# azure-iothub-device-simulation-java
Project to create "N" number of devices, send data from "N" number devices to an IOT hub.

Steps to follow

1. Open the "Constants.Java" file and fill up below values
	a. Connection String - It is IOT Hub connection String you have send the simulated values.
	b. no of Device - Total number of devices you have to create for your simulation
	c. interval_Seconds - Time interval the simulation has to send data into IOTHub.
	d. deviceIdPrefix - It the prefix we will used to create unique device Id's. Numbers will be appended into devicePrefix to generate unique id.
	e. avgTemp - Avg temperature to use to mock the data.
	f. avgHumidity - Avg Humidity to use to mock the data.
	g. scale- Scale needs to use to generate mock data.

2. Run CreateDevice.java file to create the devices. It will print the device id and primary key, if it created successfully.
   The code is fail safe. It don't create duplicate device. if you re-run again.
	
3. By default, we are sending auto simulated temperature and humidity information to IOT Hub.
   if you like to add more information, change the POJO "MessageSender.java".

4. Run "App.java" to start sending the values

We did it.
All the best.
Please feel free to contribute. 