package com.pos.iduka.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pos.iduka.IdukaTestConfig;
import com.pos.iduka.config.SecurityConfig;
import com.pos.iduka.model.db.Product;
import com.pos.iduka.model.db.UserInfo;
import com.pos.iduka.repository.ProductRepository;
import com.pos.iduka.repository.UserInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;


import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static com.pos.iduka.util.JsonComparator.hasSameStructure;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@WebMvcTest(ProductController.class)
@ContextConfiguration(classes = IdukaTestConfig.class)
@WithMockUser(username = "admin", authorities = {"ROLE_ADMIN", "ROLE_USER"})
public class ProductControllerTest {

    @Autowired
    MockMvc mockMvc;



    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    UserInfoRepository userInfoRepository;

    String token = """
            Bearer eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjp7ImlkIjoxLCJuYW1lIjoiemFkaWtpIiwiZW1haWwiOiJ6YWRpa2lAZ21haWwuY29tNiIsInJvbGVzIjoiUk9MRV9BRE1JTiJ9LCJzdWIiOiJ6YWRpa2lAZ21haWwuY29tNiIsImlhdCI6MTcyMjA3MjA2OSwiZXhwIjoxNzIyMDczODY5fQ.bl6OjEbuX2t9Hcihdj5Rf8gVJldK80-2uL_LhH7cdag
            """;


    @Test
    void shouldCreateNewProduct() throws Exception {
        var product = new Product(0L, "Bread");
//        Mockito.when(productRepository.save(product)).thenReturn(product);

        String res=mockMvc.perform(post("/product/")
                                .content(objectMapper.writeValueAsString(product))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
//                        .header(HttpHeaders.AUTHORIZATION, token)
                ).andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Optional<UserInfo> userInfo = userInfoRepository.findById(1L);
       if(userInfo.isPresent())
            log.info("User_used"+objectMapper.writeValueAsString(userInfo.get()));
        assertTrue(hasSameStructure(res, objectMapper.writeValueAsString(product),objectMapper));

    }
}
