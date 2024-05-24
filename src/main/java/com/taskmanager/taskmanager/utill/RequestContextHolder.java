package com.taskmanager.taskmanager.utill;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.HashMap;
import java.util.Map;

@Component()
@RequestScope
public class RequestContextHolder {
    private ThreadLocal<Map<String, Object>> requestContext;

    public RequestContextHolder() {
        requestContext = new ThreadLocal<>();
    }
    public void set(String key, Object value) {
        Map<String, Object> context = requestContext.get();
        if (context == null) {
            context = new HashMap<>();
            requestContext.set(context);
        }
        context.put(key, value);
    }

    public  Object get(String key) {
        Map<String, Object> context = requestContext.get();
        return context != null ? context.get(key) : null;
    }

    public  void clear() {
        requestContext.remove();
    }
}
