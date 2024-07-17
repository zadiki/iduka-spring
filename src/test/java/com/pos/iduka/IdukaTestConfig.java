package com.pos.iduka;

import com.pos.iduka.config.SecurityConfig;
import com.pos.iduka.repository.UserInfoRepository;
import com.pos.iduka.service.JwtService;
import com.pos.iduka.service.UserInfoService;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;

@Configuration
@ComponentScan(basePackages = "com.pos.iduka")
@Import(SecurityConfig.class)
public class IdukaTestConfig {
    @MockBean
    UserInfoRepository userInfoRepository;


}
