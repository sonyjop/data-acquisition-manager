package com.bakerhughes.ofs.platform.daqmgr;

import java.io.IOException;
import java.util.Timer;

import com.bakerhughes.ofs.platform.daqmgr.service.LoaderService;
import com.bakerhughes.ofs.platform.daqmgr.service.PollManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class AppListener implements ApplicationListener {
    private static Logger logger = LoggerFactory.getLogger(AppListener.class);
    @Autowired
    PollManager pollManager;
    @Autowired
    LoaderService loaderService;

    @Value("${application.taskinit.delay.sec}")
    private int taskInitiDelay;
    @Value("${application.task.interval.sec}")
    private int taskInterval;

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationReadyEvent) {
            logger.info("Application Ready and started loading metadata ... ");
            try {
                loaderService.loadAssetDeviceData();

                loaderService.loadDeviceMeta();
                loaderService.loadDeviceTemplateData();
                loaderService.loadTagMasterData();
            } catch (IOException e) {
                logger.error("Issue loading metadata {}", e.getMessage());
                logger.debug("Issue loading metadata {}", e.getStackTrace().toString());
            }
            logger.info("Application Ready and finished loading metadata ... ");
            final Timer timer = new Timer(true);
            logger.info("Configuring task with interval {} seconds ... ", taskInterval);
            timer.scheduleAtFixedRate(pollManager, (taskInitiDelay * 1000), (taskInterval * 1000));
        }
    }

}