package com.bakerhughes.ofs.platform.daqmgr.cache;

import java.io.Serializable;
import java.util.List;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

import com.fasterxml.jackson.annotation.JsonInclude;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AssetTag implements Serializable{
	@QuerySqlField
	private String assetDeviceId;
	
	@QuerySqlField
	private String assetId;
	
	@QuerySqlField
	private int intervalSec;
	
	@QuerySqlField
	private long nextRunTime;
	
	@QuerySqlField
	private String status;
	
	private List<String> tagList;

	
	public String getAssetDeviceId() {
		return assetDeviceId;
	}

	public void setAssetDeviceId(String assetDeviceId) {
		this.assetDeviceId = assetDeviceId;
	}

	public String getAssetId() {
		return assetId;
	}

	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}

	public int getIntervalSec() {
		return intervalSec;
	}

	public void setIntervalSec(int intervalSec) {
		this.intervalSec = intervalSec;
	}

	public long getNextRunTime() {
		return nextRunTime;
	}

	public void setNextRunTime(long nextRunTime) {
		this.nextRunTime = nextRunTime;
	}

	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<String> getTagList() {
		return tagList;
	}

	public void setTagList(List<String> tagList) {
		this.tagList = tagList;
	}

	@Override
	public String toString() {
		return "AssetTag [assetDeviceId=" + assetDeviceId + ", assetId=" + assetId + ", intervalSec=" + intervalSec
				+ ", nextRunTime=" + nextRunTime + ", status=" + status + ", tagList=" + tagList + "]";
	}

	
	
}
