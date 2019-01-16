package com.milind.order_processing.annotation;


import com.milind.order_processing.Utils.DiscoveryComponent;
import com.milind.order_processing.Utils.RestComponent;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Aspect to process the annotation
 */
@Aspect
@Component
public class OverideAspect {

    @Autowired
    DiscoveryComponent discoveryComponent;

    @Autowired
    RestComponent restComponent;

    static Map<String,Object> configMap = new HashMap<>();

    /**
     * This needs to be loaded at Runtime
     */
    static{

        configMap.put("orderprocess.enabled",true);
        configMap.put("orderprocess.serviceName","customorderprocessor");
        configMap.put("orderprocess.requestPath","/customorderprocess");
        configMap.put("orderprocess.responseType",String.class);
    }

    @Around("@annotation(OverideAnnotation)")
    public Object aroundAdvice(ProceedingJoinPoint point) throws Throwable {

        // Returning object
        Object object = null;
        // Getting the annotataion
        OverideAnnotation overideAnnotation = MethodSignature.class.cast(point.getSignature())
                .getMethod().getAnnotation(OverideAnnotation.class);
        if((boolean)configMap.get(overideAnnotation.enabled())){
            String url = discoveryComponent.instanceUrl((String) configMap.get(overideAnnotation.serviceName()))+"/"
                    +configMap.get(overideAnnotation.requestPath());
            Object obj[] = point.getArgs();
            object = restComponent.executeRequest(url,overideAnnotation.requestType(),
                    restComponent.getHTTPEntity(obj[0].toString(),null),
                    (Class) configMap.get(overideAnnotation.responseType()));
        }else {
            object = point.proceed();
        }
        return  object;
    }

}
