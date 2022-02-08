package com.bakerhughes.ofs.platform.daqmgr.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.query.FieldsQueryCursor;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bakerhughes.ofs.platform.daqmgr.cache.DeviceMeta;
import com.bakerhughes.ofs.platform.daqmgr.util.Queries;
@Service
public class DeviceMetaService {
	@Autowired
	IgniteCache<String, DeviceMeta> deviceMetaCache;
	
	
	
	public List<DeviceMeta> getAllDeviceMeta() {
		SqlFieldsQuery sql = new SqlFieldsQuery(Queries.GET_ALL_DEVICE_META);
		FieldsQueryCursor<List<?>> fdc = deviceMetaCache.query(sql);
		List<DeviceMeta> returnList = new ArrayList<DeviceMeta>();
		
		fdc.forEach(item -> {
			item.forEach(deviceMeta -> {
				returnList.add((DeviceMeta) deviceMeta);
			});
		});
		return returnList;
	}
}
