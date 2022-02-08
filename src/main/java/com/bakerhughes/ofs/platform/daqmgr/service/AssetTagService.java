package com.bakerhughes.ofs.platform.daqmgr.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.query.FieldsQueryCursor;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bakerhughes.ofs.platform.daqmgr.cache.AssetTag;
import com.bakerhughes.ofs.platform.daqmgr.util.Queries;

@Service
public class AssetTagService {
	@Autowired IgniteCache<String, AssetTag> assetTagCache;
	
	public void createAssetTags(AssetTag assetTag) {
		assetTagCache.put(assetTag.getAssetId() + "_" + assetTag.getIntervalSec(), assetTag);
	}
	public List<AssetTag> findAssetTagsByAssetId(String wellId) {
		
		SqlFieldsQuery sql = new SqlFieldsQuery(Queries.ASSETTAG_BY_ID);
		FieldsQueryCursor<List<?>> fdc = assetTagCache.query(sql.setArgs(wellId));
		List<AssetTag> returnList = new ArrayList<AssetTag>();
		fdc.forEach(item -> {
			item.forEach(wellTag -> {
				returnList.add((AssetTag) wellTag);
			});
		});
		
		return returnList;
	}
	public List<AssetTag> getAssetsForNextPoll(Long currTime) {
		SqlFieldsQuery sql = new SqlFieldsQuery(Queries.GET_ASSETS_FOR_NXT_POLL);
		FieldsQueryCursor<List<?>> fdc = assetTagCache.query(sql.setArgs(currTime));
		List<AssetTag> returnList = new ArrayList<AssetTag>();
		fdc.forEach(item -> {
			item.forEach(assetTag -> {
				returnList.add((AssetTag) assetTag);
			});
		});
		
		return returnList;
	}
	
	public Boolean updateAssetNextPoll(String assetId, int intervalSec, Long nextTime) {
		SqlFieldsQuery sql = new SqlFieldsQuery(Queries.UPD_ASSET_NXT_POLL);
		FieldsQueryCursor<List<?>> fdc = assetTagCache.query(sql.setArgs(nextTime, assetId, intervalSec));
		return true;
	}
}
