package com.bakerhughes.ofs.platform.daqmgr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bakerhughes.ofs.platform.daqmgr.cache.AssetDevice;
import com.bakerhughes.ofs.platform.daqmgr.service.AssetDeviceService;

@RestController
public class AssetDeviceCtrl {
	@Autowired
	private AssetDeviceService assetDeviceService;
	
	@GetMapping("/all-asset-device")
	public List<AssetDevice> getAllAssetDevices() {
		return assetDeviceService.getAllAssetDevice();
	}
	@GetMapping("/asset-device/{id}")
	public AssetDevice getAssetDeviceById(@PathVariable("id") String id) {
  		System.out.println("Call recd for " + id);
		return assetDeviceService.getAssetDevice(id);
	}
}
