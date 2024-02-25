package com.programmingtechie.config;

import com.netflix.appinfo.ApplicationInfoManager;
import com.netflix.discovery.AbstractDiscoveryClientOptionalArgs;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.cloud.netflix.eureka.CloudEurekaClient;
import org.springframework.cloud.netflix.eureka.EurekaClientConfigBean;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
import org.springframework.cloud.netflix.eureka.metadata.ManagementMetadataProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;


@Configuration
public class EurekaClientConfig {

    @Autowired
    private ConfigurableEnvironment env;
    @Autowired
    private ApplicationContext context;
    @Autowired
    private AbstractDiscoveryClientOptionalArgs<?> optionalArgs;

    @Bean(destroyMethod = "shutdown")
    //@ConditionalOnMissingBean(value = EurekaClient.class, search = SearchStrategy.CURRENT)
    public EurekaClient eurekaClient(ApplicationInfoManager manager, com.netflix.discovery.EurekaClientConfig config) {
        CloudEurekaClient cloudEurekaClient = new CloudEurekaClient(manager, config, this.optionalArgs, this.context);
        return cloudEurekaClient;
    }


    @Bean
   // @ConditionalOnMissingBean(value = com.netflix.discovery.EurekaClientConfig.class, search = SearchStrategy.CURRENT)
    public EurekaClientConfigBean eurekaClientConfigBean(ConfigurableEnvironment env) {
        return new EurekaClientConfigBean();
    }

    @Bean
    //@ConditionalOnMissingBean(value = EurekaInstanceConfig.class, search = SearchStrategy.CURRENT)
    public EurekaInstanceConfigBean eurekaInstanceConfigBean(InetUtils inetUtils,
                                                             ManagementMetadataProvider managementMetadataProvider) {
        EurekaInstanceConfigBean instance = new EurekaInstanceConfigBean(inetUtils);
        //AmazonInfo info = AmazonInfo.Builder.newBuilder().autoBuild("eureka");
        //instance.setDataCenterInfo(info);

        return instance;
    }
    private String getProperty(String property) {
        return this.env.containsProperty(property) ? this.env.getProperty(property) : "";
    }
}
