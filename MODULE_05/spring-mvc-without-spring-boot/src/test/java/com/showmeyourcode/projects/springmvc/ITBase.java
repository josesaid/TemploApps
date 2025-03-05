package com.showmeyourcode.projects.springmvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Base64;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/servlet-context.xml",
        "file:src/main/webapp/WEB-INF/security-context.xml",
})
@WebAppConfiguration
public abstract class ITBase {

    @Autowired
    protected FilterChainProxy springSecurityFilterChain;
    @Autowired
    protected WebApplicationContext webApplicationContext;
    protected ObjectMapper objectMapper;
    protected MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext)
                .addFilter(springSecurityFilterChain)
                .build();
        this.objectMapper = new ObjectMapper();
    }

    protected String getInvalidCredentialsForRandomUserAsHeader() {
        return "Basic " + Base64.getEncoder().encodeToString("username-not-found:wrong-credentials".getBytes());
    }

    protected String getInvalidCredentialsForUserAsHeader() {
        return "Basic " + Base64.getEncoder().encodeToString("user:wrong-credentials".getBytes());
    }

    protected String getValidCredentialsForAdminAsHeader() {
        return "Basic " + Base64.getEncoder().encodeToString("admin:admin".getBytes());
    }

    protected String getValidCredentialsForUserAsHeader() {
        return "Basic " + Base64.getEncoder().encodeToString("user:user".getBytes());
    }
}
