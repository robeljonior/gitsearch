package com.example.serchrepo.Controller;

import static org.mockito.Mockito.when;

import com.example.serchrepo.Model.Bank;
import com.example.serchrepo.Model.ProductType;
import com.example.serchrepo.Model.Products;
import com.example.serchrepo.Request.ProductRiquest;
import com.example.serchrepo.Response.ProductDTO;
import com.example.serchrepo.Service.ProdactService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ProductController.class})
@ExtendWith(SpringExtension.class)
class ProductControllerDiffblueTest {
    @MockBean
    private ProdactService prodactService;

    @Autowired
    private ProductController productController;

    /**
     * Method under test: {@link ProductController#Getall()}
     */
    @Test
    void testGetall() throws Exception {
        // Arrange
        when(prodactService.getAlls()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/cteating/link/Getall");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ProductController#Getall()}
     */
    @Test
    void testGetall2() throws Exception {
        // Arrange
        when(prodactService.getAlls()).thenReturn(new ArrayList<>());
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.formLogin();

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link ProductController#rigisterProduct(ProductRiquest)}
     */
    @Test
    void testRigisterProduct() throws Exception {
        // Arrange
        when(prodactService.createProduct(Mockito.<ProductRiquest>any())).thenReturn(new ProductDTO());

        ProductRiquest productRiquest = new ProductRiquest();
        productRiquest.setBank(1L);
        productRiquest.setCicd("Cicd");
        productRiquest.setDescription("The characteristics of someone or something");
        productRiquest.setLatestBranch("janedoe/featurebranch");
        productRiquest.setLink("Link");
        productRiquest.setProductType(1L);
        productRiquest.setReadme(true);
        productRiquest.setReceivedFrom("jane.doe@example.org");
        productRiquest.setRepositoryName("Repository Name");
        productRiquest.setUserRepoToken("ABC123");
        String content = (new ObjectMapper()).writeValueAsString(productRiquest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/cteating/link/create/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":null,\"bank\":null,\"productType\":null,\"latestBranch\":null,\"description\":null,\"link\":null,\"cicd\""
                                        + ":null,\"readme\":false,\"createDate\":null,\"receivedFrom\":null,\"banks\":null}"));
    }

    /**
     * Method under test: {@link ProductController#SearcAll(String)}
     */
    @Test
    void testSearcAll() throws Exception {
        // Arrange
        when(prodactService.searchProduct(Mockito.<String>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/cteating/link/SearchPeodacts")
                .param("query", "foo");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test:
     * {@link ProductController#udateProduct(Long, ProductRiquest)}
     */
    @Test
    void testUdateProduct() throws Exception {
        // Arrange
        Bank bank = new Bank();
        bank.setBankDiscription("Bank Discription");
        bank.setBankName("Bank Name");
        bank.setId(1L);

        ProductType productType = new ProductType();
        productType.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        productType.setId(1L);
        productType.setProductTypeDescription("Product Type Description");
        productType.setProductTypeName("Product Type Name");

        Products products = new Products();
        products.setBank(bank);
        products.setCicd("Cicd");
        products.setDescription("The characteristics of someone or something");
        products.setId(1L);
        products.setLatestBranch("janedoe/featurebranch");
        products.setLink("Link");
        products.setProductType(productType);
        products.setReadme(true);
        products.setReceivedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        products.setReceivedFrom("jane.doe@example.org");
        products.setRepositoryName("Repository Name");
        products.setUserRepoToken("ABC123");
        when(prodactService.update(Mockito.<Long>any(), Mockito.<ProductRiquest>any())).thenReturn(products);

        ProductRiquest productRiquest = new ProductRiquest();
        productRiquest.setBank(1L);
        productRiquest.setCicd("Cicd");
        productRiquest.setDescription("The characteristics of someone or something");
        productRiquest.setLatestBranch("janedoe/featurebranch");
        productRiquest.setLink("Link");
        productRiquest.setProductType(1L);
        productRiquest.setReadme(true);
        productRiquest.setReceivedFrom("jane.doe@example.org");
        productRiquest.setRepositoryName("Repository Name");
        productRiquest.setUserRepoToken("ABC123");
        String content = (new ObjectMapper()).writeValueAsString(productRiquest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/cteating/link/Update/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"bank\":{\"id\":1,\"bankName\":\"Bank Name\",\"bankDiscription\":\"Bank Discription\"},\"productType\":{"
                                        + "\"id\":1,\"productTypeName\":\"Product Type Name\",\"productTypeDescription\":\"Product Type Description\","
                                        + "\"createdDate\":[1970,1,1,0,0]},\"latestBranch\":\"janedoe/featurebranch\",\"description\":\"The characteristics"
                                        + " of someone or something\",\"link\":\"Link\",\"cicd\":\"Cicd\",\"readme\":true,\"receivedDate\":[1970,1,1,0,0],"
                                        + "\"receivedFrom\":\"jane.doe@example.org\",\"repositoryName\":\"Repository Name\",\"userRepoToken\":\"ABC123\"}"));
    }

    /**
     * Method under test:
     * {@link ProductController#udateProduct(Long, ProductRiquest)}
     */
    @Test
    void testUdateProduct2() throws Exception {
        // Arrange
        Bank bank = new Bank();
        bank.setBankDiscription("Bank Discription");
        bank.setBankName("Bank Name");
        bank.setId(1L);

        ProductType productType = new ProductType();
        productType.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        productType.setId(1L);
        productType.setProductTypeDescription("Product Type Description");
        productType.setProductTypeName("Product Type Name");

        Products products = new Products();
        products.setBank(bank);
        products.setCicd("Cicd");
        products.setDescription("The characteristics of someone or something");
        products.setId(1L);
        products.setLatestBranch("janedoe/featurebranch");
        products.setLink("Link");
        products.setProductType(productType);
        products.setReadme(false);
        products.setReceivedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        products.setReceivedFrom("jane.doe@example.org");
        products.setRepositoryName("Repository Name");
        products.setUserRepoToken("ABC123");
        when(prodactService.update(Mockito.<Long>any(), Mockito.<ProductRiquest>any())).thenReturn(products);

        ProductRiquest productRiquest = new ProductRiquest();
        productRiquest.setBank(1L);
        productRiquest.setCicd("Cicd");
        productRiquest.setDescription("The characteristics of someone or something");
        productRiquest.setLatestBranch("janedoe/featurebranch");
        productRiquest.setLink("Link");
        productRiquest.setProductType(1L);
        productRiquest.setReadme(true);
        productRiquest.setReceivedFrom("jane.doe@example.org");
        productRiquest.setRepositoryName("Repository Name");
        productRiquest.setUserRepoToken("ABC123");
        String content = (new ObjectMapper()).writeValueAsString(productRiquest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/cteating/link/Update/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"bank\":{\"id\":1,\"bankName\":\"Bank Name\",\"bankDiscription\":\"Bank Discription\"},\"productType\":{"
                                        + "\"id\":1,\"productTypeName\":\"Product Type Name\",\"productTypeDescription\":\"Product Type Description\","
                                        + "\"createdDate\":[1970,1,1,0,0]},\"latestBranch\":\"janedoe/featurebranch\",\"description\":\"The characteristics"
                                        + " of someone or something\",\"link\":\"Link\",\"cicd\":\"Cicd\",\"readme\":false,\"receivedDate\":[1970,1,1,0,0],"
                                        + "\"receivedFrom\":\"jane.doe@example.org\",\"repositoryName\":\"Repository Name\",\"userRepoToken\":\"ABC123\"}"));
    }

    /**
     * Method under test: {@link ProductController#Vewproduct(Long)}
     */
    @Test
    void testVewproduct() throws Exception {
        // Arrange
        when(prodactService.vewprodacts(Mockito.<Long>any())).thenReturn(new ProductDTO());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/cteating/link/vewprodacts");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("id", String.valueOf(1L));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":null,\"bank\":null,\"productType\":null,\"latestBranch\":null,\"description\":null,\"link\":null,\"cicd\""
                                        + ":null,\"readme\":false,\"createDate\":null,\"receivedFrom\":null,\"banks\":null}"));
    }
}
