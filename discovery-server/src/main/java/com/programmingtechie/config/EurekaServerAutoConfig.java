package com.programmingtechie.config;


import com.netflix.appinfo.ApplicationInfoManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.server.EurekaController;
import org.springframework.cloud.netflix.eureka.server.EurekaProperties;
import org.springframework.context.annotation.Bean;

//@Configuration
public class EurekaServerAutoConfig{
    @Autowired
    private ApplicationInfoManager applicationInfoManager;

    @Bean
//    @ConditionalOnProperty(prefix = "eureka.dashboard", name = "enabled", matchIfMissing = true)
    public EurekaController eurekaController(EurekaProperties eurekaProperties) {
        return new EurekaController(this.applicationInfoManager, eurekaProperties);
    }
}

