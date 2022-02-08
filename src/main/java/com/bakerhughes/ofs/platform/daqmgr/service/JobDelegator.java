package com.bakerhughes.ofs.platform.daqmgr.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

import com.bakerhughes.ofs.platform.daqmgr.cache.AssetTag;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class JobDelegator {
	private static Logger logger = LoggerFactory.getLogger(JobDelegator.class);
	@Autowired
	ObjectMapper objMapper;

	@Value("${application.needbrokerpush}")
	private boolean needBrokerPush;

	@Autowired
	private JmsTemplate jmsTemplate;

	@Autowired
	private Destination taskQueue;

	public void publish(final AssetTag assetTag) {
		String textMsg = null;
		try {
			textMsg = objMapper.writeValueAsString(assetTag);
		} catch (final JsonProcessingException e1) {
			e1.printStackTrace();
		}

		if (needBrokerPush) {
			jmsTemplate.convertAndSend(taskQueue, textMsg);
		}
		logger.info("Task for Asset {} and Run {} raised", assetTag.getAssetId(), assetTag.getIntervalSec());

	}
}
