package com.bakerhughes.ofs.platform.daqmgr.service;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.bakerhughes.ofs.platform.daqmgr.cache.AssetDevice;
import com.bakerhughes.ofs.platform.daqmgr.cache.AssetTag;
import com.bakerhughes.ofs.platform.daqmgr.cache.DeviceMeta;
import com.bakerhughes.ofs.platform.daqmgr.cache.DeviceTemplate;
import com.bakerhughes.ofs.platform.daqmgr.cache.TagMaster;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.ignite.IgniteCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LoaderService {
  @Autowired
  AssetDeviceService assetDeviceService;
  @Autowired
  AssetTagService assetTagService;
  @Autowired
  DeviceTemplateService deviceTemplateService;
  @Autowired
  IgniteCache<String, TagMaster> tagMasterCache;
  @Autowired
  IgniteCache<String, DeviceMeta> deviceMetaCache;

  @Value("${application.loader.sourceFolder}")
  String loaderSourceFolder;

  public void loadDeviceMeta() throws JsonParseException, JsonMappingException, IOException {
    ObjectMapper objMapper = new ObjectMapper();
    // InputStream is =
    // this.getClass().getClassLoader().getResourceAsStream(loaderSourceFolder +
    // "/device-meta.json");
    InputStream is = new FileInputStream(loaderSourceFolder + "/device-meta.json");
    DeviceMeta[] metaList = objMapper.readValue(is, DeviceMeta[].class);
    for (DeviceMeta deviceMeta : metaList) {
      deviceMetaCache.put(deviceMeta.getDeviceType(), deviceMeta);
    }
  }

  public void loadAssetDeviceData() throws JsonParseException, JsonMappingException, IOException {
    ObjectMapper objMapper = new ObjectMapper();
    InputStream is = new FileInputStream(loaderSourceFolder + "/asset-device.json");
    AssetDevice[] assetDeviceArray = objMapper.readValue(is, AssetDevice[].class);

    InputStream assetTagStream = new FileInputStream(loaderSourceFolder + "/asset-tag.json");
    AssetTag[] assetTagArray = objMapper.readValue(assetTagStream, AssetTag[].class);

    for (int i = 0; i < assetDeviceArray.length; i++) {
      String assetId = assetDeviceService.createAssetDevice(assetDeviceArray[i]);
      assetDeviceArray[i].setAssetId(assetId);
      for (int j = 0; j < assetTagArray.length; j++) {
        assetTagArray[j].setAssetDeviceId(assetDeviceArray[i].getId());
        assetTagArray[j].setAssetId(assetDeviceArray[i].getAssetId());
        assetTagService.createAssetTags(assetTagArray[j]);
      }

    }
  }

  public void loadDeviceTemplateData() throws JsonParseException, JsonMappingException, IOException {
    ObjectMapper objMapper = new ObjectMapper();
    InputStream is = new FileInputStream(loaderSourceFolder + "/device-template.json");
    DeviceTemplate[] metaList = objMapper.readValue(is, DeviceTemplate[].class);
    for (DeviceTemplate template : metaList) {
      deviceTemplateService.createTemplate(template);
    }
  }

  public void loadTagMasterData() throws JsonParseException, JsonMappingException, IOException {
    ObjectMapper objMapper = new ObjectMapper();
    InputStream is = new FileInputStream(loaderSourceFolder + "/tagmaster.json");
    TagMaster[] tagMasterList = objMapper.readValue(is, TagMaster[].class);
    for (TagMaster tagMaster : tagMasterList) {
      tagMasterCache.put(tagMaster.getTagName(), tagMaster);
    }
  }
}