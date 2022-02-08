package com.bakerhughes.ofs.platform.daqmgr.cache;

import org.apache.ignite.cache.CacheAtomicityMode;
import org.apache.ignite.cache.CacheWriteSynchronizationMode;
import org.apache.ignite.configuration.CacheConfiguration;

public class CacheConfigurationBuilder<K,V> {
	private CacheAtomicityMode mode;
	private CacheWriteSynchronizationMode writeSyncMode;
	private String sqlSchema;
	//private K k;
	//private V v;
	Class keyClass;
	Class valClass;
	
	
	public CacheConfigurationBuilder(Class key, Class val) {
		this.keyClass = key;
		this.valClass = val;
	}
	public CacheConfigurationBuilder atomicityMode(CacheAtomicityMode mode) {
		this.mode = mode;
		return this;
	}
	public CacheConfigurationBuilder writeSyncMode(CacheWriteSynchronizationMode writeSyncMode) {
		this.writeSyncMode = writeSyncMode;
		return this;
	}
	public CacheConfigurationBuilder sqlSchema(String sqlSchema) {
		this.sqlSchema = sqlSchema;
		return this;
	}
	
	public CacheConfiguration<K, V> build() {
		CacheConfiguration<K, V> cacheConfig = new CacheConfiguration<K, V>(this.valClass.getSimpleName());
		cacheConfig.setAtomicityMode(this.mode);
		cacheConfig.setWriteSynchronizationMode(this.writeSyncMode);
		cacheConfig.setIndexedTypes(this.keyClass,this.valClass);
		cacheConfig.setSqlSchema(this.sqlSchema);
		
		return cacheConfig;
	}
	
}
