/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.util;

import java.util.Map;
import javax.servlet.ServletContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author Jedai
 */
public class SpringContextUtil {
    /**
     * Get service.
     *
     * @param serviceClass is service class.
     * @return service beans.
     */
    public static Object getService(Class serviceClass, ServletContext sc) {
        WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
        Map validBeans = ctx.getBeansOfType(serviceClass);
        if (validBeans.isEmpty()) {
            throw new RuntimeException("No service beans found with class " + serviceClass.getName());
        } else if (validBeans.size() > 1) {
            throw new RuntimeException("More than one matching bean found for class " + serviceClass.getName());
        }
        return validBeans.values().iterator().next();
    }
}
