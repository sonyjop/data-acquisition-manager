package com.bakerhughes.ofs.platform.daqmgr.service;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ignite.IgniteCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bakerhughes.ofs.platform.daqmgr.cache.TagMaster;
@Service
public class TagMasterService {
	@Autowired
	IgniteCache<String, TagMaster> tagMasterCache;
	

}
