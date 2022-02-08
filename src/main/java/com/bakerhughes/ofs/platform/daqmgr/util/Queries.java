package com.bakerhughes.ofs.platform.daqmgr.util;

import com.bakerhughes.ofs.platform.daqmgr.cache.AssetDevice;
import com.bakerhughes.ofs.platform.daqmgr.cache.AssetTag;
import com.bakerhughes.ofs.platform.daqmgr.cache.DeviceMeta;

public class Queries {
	public final static String ASSETDEVICE_BY_NAME = "SELECT _VAL FROM DAQMASTER."
			+ AssetDevice.class.getSimpleName().toUpperCase() + " WHERE WELLNAME LIKE ?";
	
	public final static String GET_ALL_ASSETDEVICE = "SELECT _VAL FROM DAQMASTER."
			+ AssetDevice.class.getSimpleName().toUpperCase();
	
	public final static String ASSETTAG_BY_ID = "SELECT _VAL FROM DAQMASTER."
			+ AssetTag.class.getSimpleName().toUpperCase() + " WHERE WELLID IS ?";
	
	public final static String GET_ALL_ACTIVE_ASSETS = "SELECT _VAL FROM DAQMASTER."
			+ AssetTag.class.getSimpleName().toUpperCase() + " WHERE STATUS IS 'ACTIVE'";
	
	public final static String GET_ASSETS_FOR_NXT_POLL = "SELECT _VAL FROM DAQMASTER."
			+ AssetTag.class.getSimpleName().toUpperCase() + " WHERE STATUS IS 'ACTIVE'"
			+ " AND NEXTRUNTIME <= ?" ;
	
	public final static String UPD_ASSET_NXT_POLL = "UPDATE DAQMASTER."
			+ AssetTag.class.getSimpleName().toUpperCase() + " SET NEXTRUNTIME=? WHERE ASSETID IS ? AND INTERVALSEC=?";
	
	public final static String GET_ALL_DEVICE_META = "SELECT _VAL FROM DAQMASTER."
			+ DeviceMeta.class.getSimpleName().toUpperCase();
}
