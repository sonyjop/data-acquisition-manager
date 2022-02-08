package com.bakerhughes.ofs.platform.daqmgr.service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bakerhughes.ofs.platform.daqmgr.cache.AssetTag;
@Service
public class PollManager extends TimerTask{
	private static Logger logger = LoggerFactory.getLogger(PollManager.class);
	private @Autowired AssetTagService assetTagService;
	private @Autowired JobDelegator jobDelegator;
	@Override
	public void run() {
		Long currTime = Calendar.getInstance().getTimeInMillis();
		List<AssetTag> assetTags = assetTagService.getAssetsForNextPoll(currTime);
		
		assetTags.forEach(assetTag -> {
			if(assetTag.getNextRunTime() == 0) {
				assetTag.setNextRunTime(currTime);
			}
			jobDelegator.publish(assetTag);
			
			long newNextRunTime = assetTag.getNextRunTime() + assetTag.getIntervalSec() * 1000;
			/*java.util.Date newRun = new Date(newNextRunTime);
			SimpleDateFormat sdf = new SimpleDateFormat();
			String newTime = sdf.format(newRun);
			logger.info("Asset {} Interval {} NextRun {}",assetTag.getAssetId(), assetTag.getIntervalSec(), newTime);
			*/
			assetTagService.updateAssetNextPoll(assetTag.getAssetId(), assetTag.getIntervalSec(), newNextRunTime );
		});
		
	}

}
