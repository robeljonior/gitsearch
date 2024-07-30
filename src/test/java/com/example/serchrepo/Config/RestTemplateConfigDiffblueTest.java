package com.example.serchrepo.Config;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.util.DefaultUriBuilderFactory;

@ContextConfiguration(classes = {RestTemplateConfig.class})
@ExtendWith(SpringExtension.class)
class RestTemplateConfigDiffblueTest {
    @Autowired
    private RestTemplateConfig restTemplateConfig;

    /**
     * Method under test: {@link RestTemplateConfig#restTemplate()}
     */
    @Test
    void testRestTemplate() {
        // Arrange, Act and Assert
        assertTrue(restTemplateConfig.restTemplate().getUriTemplateHandler() instanceof DefaultUriBuilderFactory);
    }
}
