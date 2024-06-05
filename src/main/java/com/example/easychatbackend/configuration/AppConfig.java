package com.example.easychatbackend.configuration;


import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {

    @Value("${ws.port}")
    private Integer wsPort;

    @Value("${admin.email}")
    private String adminEmail;

    @Value("${project.folder}")
    private String projectFolder;

    public void setWsPort(Integer wsPort) {
        this.wsPort = wsPort;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public void setProjectFolder(String projectFolder) {
        this.projectFolder = projectFolder;
    }

    public Integer getWsPort() {
        return wsPort;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public String getProjectFolder() {
        if (StringUtils.isEmpty(projectFolder) && !projectFolder.endsWith("/"))
            projectFolder += "/";

        return projectFolder;
    }
}
