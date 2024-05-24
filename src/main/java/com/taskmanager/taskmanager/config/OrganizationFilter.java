package com.taskmanager.taskmanager.config;

import com.taskmanager.taskmanager.entity.OrganizationEntity;
import com.taskmanager.taskmanager.services.OrganizationService;
import com.taskmanager.taskmanager.utill.RequestContextHolder;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class OrganizationFilter extends OncePerRequestFilter {

    private final RequestContextHolder contextHolder;
    private final OrganizationService organizationService;

    public OrganizationFilter(RequestContextHolder contextHolder, OrganizationService organizationRepository) {
        this.contextHolder = contextHolder;
        this.organizationService = organizationRepository;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        Optional<String> header = Optional.ofNullable(request.getHeader("x-organization"));
        if (header.isPresent()) {
            String  organizationKey = header.get();
            Optional<OrganizationEntity> organizationEntity = this.organizationService.checkIfOrganizationExist(organizationKey);
            organizationEntity.ifPresent(organization -> contextHolder.set("organization", organization));
        }
        filterChain.doFilter(request, response);
    }
}
