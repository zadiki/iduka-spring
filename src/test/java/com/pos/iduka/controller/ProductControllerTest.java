package com.pos.iduka.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pos.iduka.IdukaTestConfig;
import com.pos.iduka.config.SecurityConfig;
import com.pos.iduka.model.db.Product;
import com.pos.iduka.repository.ProductRepository;
import com.pos.iduka.repository.UserInfoRepository;
import com.pos.iduka.service.JwtService;
import com.pos.iduka.service.ProductService;
import com.pos.iduka.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

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

    @MockBean
    ProductRepository productRepository;


    @Autowired
    ObjectMapper objectMapper;

    String token = """
            Bearer eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjp7ImlkIjoxLCJuYW1lIjoiemFkaWtpIiwiZW1haWwiOiJ6YWRpa2lAZ21haWwuY29tIiwicGFzc3dvcmQiOiIkMmEkMTAkWW5Mc05aQUQyN3FsaXZpS1ZaSHEwT3dETTRaMzExeUdqLy8ubWFRYzdXRDF3dmE5Sy9PbE8iLCJyb2xlcyI6IlJPTEVfQURNSU4ifSwic3ViIjoiemFkaWtpQGdtYWlsLmNvbSIsImlhdCI6MTcyMTA0OTk2MCwiZXhwIjoxNzIxMDUxNzYwfQ.aho-bwjfcHQeQakwwH819RfrmkDsCPQ_I9i7to50V5Q
            """;


    @Test
    void shouldCreateNewProduct() throws Exception {
        var product = new Product(Long.valueOf(0), "Bread");
        Mockito.when(productRepository.save(product)).thenReturn(product);
        ResultActions result=mockMvc.perform(post("/product/")
                        .content(objectMapper.writeValueAsString(product))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
//                        .header(HttpHeaders.AUTHORIZATION, token)
                ).andDo(print())
                .andExpectAll(status().isOk(), content().json(objectMapper.writeValueAsString(product)));


        String content = result.toString();
        log.info("content"  +content);
    }
}
