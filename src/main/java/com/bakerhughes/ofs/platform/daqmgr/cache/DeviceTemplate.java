package com.bakerhughes.ofs.platform.daqmgr.cache;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

public class DeviceTemplate  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@QuerySqlField
	private String templateId;
	
	@QuerySqlField
	private String templateName;
	
	@QuerySqlField
	private String deviceName;
	
	@QuerySqlField
	private String deviceType;
	
	private Map<String, Mapping> mapping;

	@Override
	public String toString() {
		return "DeviceTemplate [templateId=" + templateId + ", templateName=" + templateName + ", deviceName="
				+ deviceName + ", deviceType=" + deviceType + ", mapping=" + mapping + "]";
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public Map<String, Mapping> getMapping() {
		return this.mapping;
	}

	public void setMapping(Map<String, Mapping> mapping) {
		this.mapping = mapping;
	}
	public void addToMapping(String tag, Mapping mapping) {
		if(null == this.mapping ) {
			this.mapping = new HashMap<String, Mapping>();
		}
		this.mapping.put(tag,mapping);
	}

	
	
}
