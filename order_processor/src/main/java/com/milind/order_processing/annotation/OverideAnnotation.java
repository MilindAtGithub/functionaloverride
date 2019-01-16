package com.milind.order_processing.annotation;

import org.springframework.http.HttpMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

/**
 * By default all values are in string
 * But while processing it will get casted to particular type
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = { ElementType.METHOD })
public @interface OverideAnnotation {

    //By Default it will be disabled
    String enabled() default "false";
    String serviceName();
    String requestPath();
    //By Default it will be POST
    HttpMethod requestType() default HttpMethod.POST;
    // Default content type is JSON
    String contentType() default "application/json";
    //Format for param mapping param1::param2, param2::paramx
    // By default it will be blank means same as the class one
    String paramMapping() default "";
    String responseType() default "Map.class";
}
