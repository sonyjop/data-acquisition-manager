package com.bakerhughes.ofs.platform.daqmgr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bakerhughes.ofs.platform.daqmgr.cache.DeviceTemplate;
import com.bakerhughes.ofs.platform.daqmgr.service.DeviceTemplateService;

@RestController
public class DeviceTemplateRest {
	@Autowired
	private DeviceTemplateService deviceTemplateService;
	
	@GetMapping("/device-template/{id}")
	public DeviceTemplate getAssetDeviceById(@PathVariable("id") String id) {
		System.out.println("The requested Template Id : " + id);
  		return deviceTemplateService.getTemplate(id);
	}
}
