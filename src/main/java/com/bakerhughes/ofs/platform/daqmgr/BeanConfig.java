package com.bakerhughes.ofs.platform.daqmgr;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.jms.Destination;

import com.bakerhughes.ofs.platform.daqmgr.cache.AssetDevice;
import com.bakerhughes.ofs.platform.daqmgr.cache.AssetTag;
import com.bakerhughes.ofs.platform.daqmgr.cache.DeviceMeta;
import com.bakerhughes.ofs.platform.daqmgr.cache.DeviceTemplate;
import com.bakerhughes.ofs.platform.daqmgr.cache.TagMaster;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class BeanConfig {
	@Autowired 
	private Ignite ignite;
	
	@Value("${application.queue.task}")
	private String taskQueue;

	@Value("${application.topic.change}")
	private String changeTopic;

	@Bean
	public IgniteCache<String, AssetDevice> assetDeviceCache() {
		return ignite.getOrCreateCache(AssetDevice.class.getSimpleName());
	}
	@Bean
	public IgniteCache<String, TagMaster> tagMasterCache() {
		return ignite.getOrCreateCache(TagMaster.class.getSimpleName());
	}
	@Bean
	public IgniteCache<String, AssetTag> assetTagCache() {
		return ignite.getOrCreateCache(AssetTag.class.getSimpleName());
	}
	
	@Bean
	public IgniteCache<String, DeviceTemplate> modbusTemplateCache() {
		return ignite.getOrCreateCache(DeviceTemplate.class.getSimpleName());
	}
	
	@Bean
	public IgniteCache<String, DeviceMeta> deviceMetaCache() {
		return ignite.getOrCreateCache(DeviceMeta.class.getSimpleName());
	}
	
	@Bean
	public ObjectMapper jsonMapper() {
		return new ObjectMapper();
	}
	@Bean
	public Destination taskQueue() {
		return new ActiveMQQueue(taskQueue);
	}

	@Bean
	public Destination changeTopic() {
		return new ActiveMQTopic(changeTopic);
	}
}
