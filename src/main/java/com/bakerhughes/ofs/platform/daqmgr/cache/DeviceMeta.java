package com.bakerhughes.ofs.platform.daqmgr.cache;

import java.io.Serializable;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeviceMeta implements Serializable {
	@QuerySqlField
	@JsonProperty("deviceType")
	private String deviceType;
	
	@QuerySqlField
	@JsonProperty("deviceName")
	private String deviceName;

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	@Override
	public String toString() {
		return "DeviceMeta [deviceType=" + deviceType + ", deviceName=" + deviceName + "]";
	}
	
	
	
	
}
