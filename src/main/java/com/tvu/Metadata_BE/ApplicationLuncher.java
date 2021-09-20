/*
 * Copyright (C)2019-2020 TVUNetworks, All Rights Reserved.
 * This source code and any compilation or derivative thereof is the proprietary
 * information of TVUNetworks and is confidential in nature.
 * Under no circumstances is this software to be exposed to or placed
 * under an Open Source License of any type without the expressed written
 * permission of TVUNetworks.
 */
package com.tvu.Metadata_BE;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan("com.tvu.*")
@SpringBootApplication
@PropertySources({@PropertySource("classpath:build.properties")})
@EnableScheduling
public class ApplicationLuncher {
    static Logger logger = Logger.getLogger(ApplicationLuncher.class);

    public static void main(String[] args) {

        // Log in log file
        logger.info("Application Launcher started successfully !!");
        SpringApplication.run(ApplicationLuncher.class, args);
    }
}
