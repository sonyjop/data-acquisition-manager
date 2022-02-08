package com.bakerhughes.ofs.platform.daqmgr.cache;

import java.io.Serializable;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AssetDevice implements Serializable{
	@QuerySqlField
	private String id;
	
	@QuerySqlField(index = true)
	private String assetId;

	@QuerySqlField
	private String assetName;

	@QuerySqlField
	private String devId;
	
	private int slaveId;

	@QuerySqlField
	private String templateId;

	@QuerySqlField
	private String templateName;

	private String srcURL;

	private Integer srcPort;

	private String srcProtocol;

	private String status;

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAssetId() {
		return assetId;
	}

	public void setAssetId(String wellId) {
		this.assetId = wellId;
	}

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String wellName) {
		this.assetName = wellName;
	}

	public String getDevId() {
		return devId;
	}

	public void setDevId(String devId) {
		this.devId = devId;
	}

	
	public int getSlaveId() {
		return slaveId;
	}

	public void setSlaveId(int slaveId) {
		this.slaveId = slaveId;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String devTemplateId) {
		this.templateId = devTemplateId;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String devTemplateName) {
		this.templateName = devTemplateName;
	}

	public String getSrcURL() {
		return srcURL;
	}

	public void setSrcURL(String srcURL) {
		this.srcURL = srcURL;
	}

	public Integer getSrcPort() {
		return srcPort;
	}

	public void setSrcPort(Integer srcPort) {
		this.srcPort = srcPort;
	}

	public String getSrcProtocol() {
		return srcProtocol;
	}

	public void setSrcProtocol(String srcProtocol) {
		this.srcProtocol = srcProtocol;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "AssetDevice [id=" + id + ", assetId=" + assetId + ", assetName=" + assetName + ", devId=" + devId
				+ ", slaveId=" + slaveId + ", templateId=" + templateId + ", templateName=" + templateName + ", srcURL="
				+ srcURL + ", srcPort=" + srcPort + ", srcProtocol=" + srcProtocol + ", status=" + status + "]";
	}


}
