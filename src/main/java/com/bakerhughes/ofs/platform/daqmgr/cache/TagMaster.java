package com.bakerhughes.ofs.platform.daqmgr.cache;

import java.io.Serializable;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

public class TagMaster implements Serializable{
	@QuerySqlField
	private String tagName;
	
	private String tagAlias;
	
	private String tagDescription;

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getTagAlias() {
		return tagAlias;
	}

	public void setTagAlias(String tagAlias) {
		this.tagAlias = tagAlias;
	}

	public String getTagDescription() {
		return tagDescription;
	}

	public void setTagDescription(String tagDescription) {
		this.tagDescription = tagDescription;
	}

	@Override
	public String toString() {
		return "TagMaster [tagName=" + tagName + ", tagAlias=" + tagAlias + ", tagDescription=" + tagDescription + "]";
	}
	
	
}
