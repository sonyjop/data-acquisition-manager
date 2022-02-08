package com.bakerhughes.ofs.platform.daqmgr.cache;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Mapping  implements Serializable{
	
	private int registry;
	
	private String tag;
	
	private String dataType;

	private String uom;

	public int getRegistry() {
		return registry;
	}

	public void setRegistry(int registry) {
		this.registry = registry;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}
	
	
}