package com.bakerhughes.ofs.platform.daqmgr;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import com.bakerhughes.ofs.platform.daqmgr.cache.DeviceTemplate;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestMain {

	public static void main(String[] args) throws Exception {
		new TestMain().loadDeviceTemplateData();
	}
	public void loadDeviceTemplateData() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objMapper = new ObjectMapper();
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("metadata/device-template.json");
		DeviceTemplate[] metaList = objMapper.readValue(is, DeviceTemplate[].class);
		for (DeviceTemplate template : metaList) {
			System.out.println("template : " + template);
		}
    }
	/*public void broker() throws Exception {
		BrokerService broker = new BrokerService();
		//broker.stop();
		
		TransportConnector connector = new TransportConnector();
		connector.setUri(new URI("tcp://localhost:61616"));
		broker.addConnector(connector);
		broker.start();
		System.out.println("Broker Service started");
		Object lock = new Object();
        synchronized (lock) {
            lock.wait();
        }
	}*/
	
	
}
