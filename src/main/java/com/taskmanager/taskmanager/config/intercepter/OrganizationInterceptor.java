package com.taskmanager.taskmanager.config.intercepter;

import com.taskmanager.taskmanager.annotation.RequireOrganization;
import com.taskmanager.taskmanager.annotation.RequireOrganizationMethod;
import com.taskmanager.taskmanager.entity.OrganizationEntity;
import com.taskmanager.taskmanager.utill.RequestContextHolder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.naming.AuthenticationException;
import java.lang.reflect.Method;

@Component
@RequiredArgsConstructor
public class OrganizationInterceptor implements HandlerInterceptor {

    private final RequestContextHolder contextHolder;

    private boolean handleClassAnnotation() throws AuthenticationException {
        OrganizationEntity organization = (OrganizationEntity) contextHolder.get("organization");
        if (organization == null) {
            throw new AuthenticationException("Please provide organization name");
        }
        return true;
    }

    private boolean handleMethodAnnotation(Object handler) throws AuthenticationException {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Class<?> controllerClass = handlerMethod.getBeanType();
        Method[] methods = controllerClass.getMethods();
        for(var method: methods) {
            RequireOrganizationMethod methodAnnotation = method.getAnnotation(RequireOrganizationMethod.class);
            if (methodAnnotation != null) {
                OrganizationEntity organization = (OrganizationEntity) contextHolder.get("organization");
                if (organization == null) {
                    throw new AuthenticationException("Please provide organization name");
                }
            }
        }
        return true;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Class<?> controllerClass = handlerMethod.getBeanType();
            RequireOrganization annotation = controllerClass.getAnnotation(RequireOrganization.class);
            if (annotation != null) {
              return handleClassAnnotation();
            } else {
             return handleMethodAnnotation(handler);
            }
        }
        return  true;
    }
}
