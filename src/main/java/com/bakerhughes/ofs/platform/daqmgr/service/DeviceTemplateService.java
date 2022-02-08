package com.bakerhughes.ofs.platform.daqmgr.service;

import org.apache.ignite.IgniteCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bakerhughes.ofs.platform.daqmgr.cache.DeviceTemplate;

@Service
public class DeviceTemplateService {
	@Autowired IgniteCache<String, DeviceTemplate> modbusTemplateCache;
	public boolean createTemplate(DeviceTemplate template) {
		modbusTemplateCache.put(template.getTemplateId(), template);
		return true;
	}

	public DeviceTemplate getTemplate(String templateId) {
		
		return modbusTemplateCache.get(templateId);
	}

	
}
