package com.milind.order_processing.Utils;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DiscoveryComponent {

    @Autowired
    EurekaClient eurekaClient;

    public String instanceUrl(String serviceName) {

        InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka(serviceName,false);
        return "http://"+instanceInfo.getIPAddr()+":"+instanceInfo.getPort();
    }
}
