package com.jandadav.zoweapidoc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.zowe.apiml.eurekaservice.client.ApiMediationClient;
import org.zowe.apiml.eurekaservice.client.config.ApiMediationServiceConfig;
import org.zowe.apiml.eurekaservice.client.impl.ApiMediationClientImpl;
import org.zowe.apiml.eurekaservice.client.util.ApiMediationServiceConfigReader;
import org.zowe.apiml.exception.ServiceDefinitionException;

import javax.annotation.PostConstruct;

@Component
public class ApimlAdapter {

    Logger log = LoggerFactory.getLogger(this.getClass());
    private ApiMediationClient apiMediationClient;

    @Value("${service.apidocurl}")
    private String apiDocUrl;

    @PostConstruct
    public void register() throws ServiceDefinitionException {
        String configurationFile = "/service-configuration.yml";
        ApiMediationServiceConfig config = new ApiMediationServiceConfigReader().loadConfiguration(configurationFile);
        config.getApiInfo().get(0).setSwaggerUrl(apiDocUrl);
        try {
            apiMediationClient = new ApiMediationClientImpl();
            apiMediationClient.register(config);
        } catch (ServiceDefinitionException sde) {
            log.error("Service configuration failed. Check log for previous errors: ", sde);
        }
    }
}
