package com.bakerhughes.ofs.platform.daqmgr.cache;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.CacheAtomicityMode;
import org.apache.ignite.cache.CacheMode;
import org.apache.ignite.cache.CacheWriteSynchronizationMode;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.logger.slf4j.Slf4jLogger;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.multicast.TcpDiscoveryMulticastIpFinder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DAQCache  implements Serializable{
	@Value("${application.schema.name}")
	private String SCHEMA_NAME;
	@Value("${application.multicastgroup.ip}")
	private String multicastip;
	@Bean
	public Ignite ignite() {
		IgniteConfiguration igniteConfiguration = new IgniteConfiguration();
		igniteConfiguration.setGridLogger(new Slf4jLogger());
		//igniteConfiguration.setWorkDirectory("C:\\InstalledSoftwares\\IGN_PERSISTENCE");
		//igniteConfiguration.setSqlSchemas("DAQMASTER");
		/*
		DataStorageConfiguration dsCfg = new DataStorageConfiguration();
		DataRegionConfiguration dataRegionCfg = new DataRegionConfiguration();
		dataRegionCfg.setName("DAQCachePersistence");
		dataRegionCfg.setPersistenceEnabled(true);
		dsCfg.setDefaultDataRegionConfiguration(dataRegionCfg);
		igniteConfiguration.setDataStorageConfiguration(dsCfg);
		*/
		TcpDiscoveryMulticastIpFinder ipFinder = new TcpDiscoveryMulticastIpFinder();
		ipFinder.setMulticastGroup(multicastip);
		TcpDiscoverySpi discoverySpi = new TcpDiscoverySpi();
		discoverySpi.setIpFinder(ipFinder);
		igniteConfiguration.setDiscoverySpi(discoverySpi);
				
		igniteConfiguration.setIgniteInstanceName("DAQ-MASTER-CACHE");
		List<CacheConfiguration> cacheConfigsList = DAQCache.addCacheObjects(SCHEMA_NAME);
		CacheConfiguration[] cacheConfigArray = new CacheConfiguration[cacheConfigsList.size()];
		AtomicInteger counter = new AtomicInteger(0);
		cacheConfigsList.forEach(cacheConfig -> {
			cacheConfigArray[counter.getAndIncrement()] = cacheConfig;
		});
		
		igniteConfiguration.setCacheConfiguration(cacheConfigArray);
		
		Ignite ignite = Ignition.start(igniteConfiguration);
		ignite.active(true);
		
		return ignite;
	}
	
	
	public static List<CacheConfiguration> addCacheObjects(String schemaName) {
		List<CacheConfiguration> cacheObjList = new ArrayList<CacheConfiguration>();
		
		
		// ##### WELLDEVICE CACHE
		cacheObjList.add(
				new CacheConfigurationBuilder<String, AssetDevice>(String.class, AssetDevice.class)
					.atomicityMode(CacheAtomicityMode.TRANSACTIONAL)
					.writeSyncMode(CacheWriteSynchronizationMode.FULL_SYNC)
					.sqlSchema(schemaName)
					.build());
		// ##### MODBUSTEMPLATE CACHE
		cacheObjList.add(
				new CacheConfigurationBuilder<String, DeviceTemplate>(String.class, DeviceTemplate.class)
					.atomicityMode(CacheAtomicityMode.TRANSACTIONAL)
					.writeSyncMode(CacheWriteSynchronizationMode.FULL_SYNC)
					.sqlSchema(schemaName)
					.build());
		
		// ##### DEVICE META
		cacheObjList.add(
				new CacheConfigurationBuilder<String, DeviceMeta>(String.class, DeviceMeta.class)
					.atomicityMode(CacheAtomicityMode.TRANSACTIONAL)
					.writeSyncMode(CacheWriteSynchronizationMode.FULL_SYNC)
					.sqlSchema(schemaName)
					.build());
		
		// ##### WELLTAG
		cacheObjList.add(
				new CacheConfigurationBuilder<String, AssetTag>(String.class, AssetTag.class)
					.atomicityMode(CacheAtomicityMode.TRANSACTIONAL)
					.writeSyncMode(CacheWriteSynchronizationMode.FULL_SYNC)
					.sqlSchema(schemaName)
					.build());
		
		
		// ##### TAGMASTER
		cacheObjList.add(
				new CacheConfigurationBuilder<String, TagMaster>(String.class, TagMaster.class)
					.atomicityMode(CacheAtomicityMode.TRANSACTIONAL)
					.writeSyncMode(CacheWriteSynchronizationMode.FULL_SYNC)
					.sqlSchema(schemaName)
					.build());
		return cacheObjList;
		
	}
	 
	
}
