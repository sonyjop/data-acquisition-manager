package com.bakerhughes.ofs.platform.daqmgr.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.query.FieldsQueryCursor;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bakerhughes.ofs.platform.daqmgr.cache.AssetDevice;
import com.bakerhughes.ofs.platform.daqmgr.util.Queries;

@Service
public class AssetDeviceService {
	@Autowired IgniteCache<String, AssetDevice> assetDeviceCache;
	
	public String createAssetDevice(AssetDevice assetDevice) {
		UUID uuid = UUID.randomUUID();
		assetDevice.setId(uuid.toString());
		assetDeviceCache.put(assetDevice.getId(), assetDevice);		
		return assetDevice.getId();
	}
	public AssetDevice getAssetDevice(String key) {
		return assetDeviceCache.get(key);
	}
	public Boolean updateAssetDevice(AssetDevice assetDevice) {
		assetDeviceCache.put(assetDevice.getAssetId() + assetDevice.getDevId(), assetDevice);
		return true;
	}
	public Boolean deleteAssetDevice(String key) {
		
		return assetDeviceCache.remove(key);
	}
	public List<AssetDevice> getAllAssetDevice() {
		SqlFieldsQuery sql = new SqlFieldsQuery(Queries.GET_ALL_ASSETDEVICE);
		FieldsQueryCursor<List<?>> fdc = assetDeviceCache.query(sql);
		List<AssetDevice> returnList = new ArrayList<AssetDevice>();
		fdc.forEach(item -> {
			item.forEach(assetDevice -> {
				returnList.add((AssetDevice) assetDevice);
			});
		});
		
		return returnList;
	}
	public List<AssetDevice> findAssetDeviceByName(String assetName) {
		SqlFieldsQuery sql = new SqlFieldsQuery(Queries.ASSETDEVICE_BY_NAME);
		FieldsQueryCursor<List<?>> fdc = assetDeviceCache.query(sql.setArgs("%" + assetName + "%"));
		List<AssetDevice> returnList = new ArrayList<AssetDevice>();
		fdc.forEach(item -> {
			item.forEach(assetDevice -> {
				returnList.add((AssetDevice) assetDevice);
			});
		});
		
		return returnList;
	}

	
}
