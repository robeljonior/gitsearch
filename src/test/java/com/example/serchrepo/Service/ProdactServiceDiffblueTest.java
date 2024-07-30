package com.example.serchrepo.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.serchrepo.Model.Bank;
import com.example.serchrepo.Model.ProductType;
import com.example.serchrepo.Model.Products;
import com.example.serchrepo.Repository.BankRepository;
import com.example.serchrepo.Repository.ProdactTypeRepository;
import com.example.serchrepo.Repository.ProductRepository;
import com.example.serchrepo.Response.ProductDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

@ContextConfiguration(classes = {ProdactService.class})
@ExtendWith(SpringExtension.class)
class ProdactServiceDiffblueTest {
    @MockBean
    private BankRepository bankRepository;

    @MockBean
    private GitHubApiClient gitHubApiClient;

    @Autowired
    private ProdactService prodactService;

    @MockBean
    private ProdactTypeRepository prodactTypeRepository;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private RestTemplate restTemplate;

    /**
     * Method under test: {@link ProdactService#vewprodacts(Long)}
     */
    @Test
    void testVewprodacts() {
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
        Optional<Products> ofResult = Optional.of(products);
        when(productRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        ProductDTO actualVewprodactsResult = prodactService.vewprodacts(1L);

        // Assert
        verify(productRepository).findById(eq(1L));
        assertEquals("1970-01-01", actualVewprodactsResult.getCreateDate().toLocalDate().toString());
        assertEquals("Bank Discription", actualVewprodactsResult.getBank().getBankDiscription());
        assertEquals("Cicd", actualVewprodactsResult.getCicd());
        assertEquals("Link", actualVewprodactsResult.getLink());
        assertEquals("The characteristics of someone or something", actualVewprodactsResult.getDescription());
        assertEquals("jane.doe@example.org", actualVewprodactsResult.getReceivedFrom());
        assertEquals("janedoe/featurebranch", actualVewprodactsResult.getLatestBranch());
        assertEquals(1L, actualVewprodactsResult.getProductType().getId().longValue());
        assertEquals(1L, actualVewprodactsResult.getId().longValue());
        assertTrue(actualVewprodactsResult.isReadme());
    }

    /**
     * Method under test: {@link ProdactService#vewprodacts(Long)}
     */
    @Test
    void testVewprodacts2() {
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
        Products products = mock(Products.class);
        ProductDTO productDTO = new ProductDTO();
        when(products.productDTO()).thenReturn(productDTO);
        doNothing().when(products).setBank(Mockito.<Bank>any());
        doNothing().when(products).setCicd(Mockito.<String>any());
        doNothing().when(products).setDescription(Mockito.<String>any());
        doNothing().when(products).setId(Mockito.<Long>any());
        doNothing().when(products).setLatestBranch(Mockito.<String>any());
        doNothing().when(products).setLink(Mockito.<String>any());
        doNothing().when(products).setProductType(Mockito.<ProductType>any());
        doNothing().when(products).setReadme(anyBoolean());
        doNothing().when(products).setReceivedDate(Mockito.<LocalDateTime>any());
        doNothing().when(products).setReceivedFrom(Mockito.<String>any());
        doNothing().when(products).setRepositoryName(Mockito.<String>any());
        doNothing().when(products).setUserRepoToken(Mockito.<String>any());
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
        Optional<Products> ofResult = Optional.of(products);
        when(productRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        ProductDTO actualVewprodactsResult = prodactService.vewprodacts(1L);

        // Assert
        verify(products).productDTO();
        verify(products).setBank(isA(Bank.class));
        verify(products).setCicd(eq("Cicd"));
        verify(products).setDescription(eq("The characteristics of someone or something"));
        verify(products).setId(eq(1L));
        verify(products).setLatestBranch(eq("janedoe/featurebranch"));
        verify(products).setLink(eq("Link"));
        verify(products).setProductType(isA(ProductType.class));
        verify(products).setReadme(eq(true));
        verify(products).setReceivedDate(isA(LocalDateTime.class));
        verify(products).setReceivedFrom(eq("jane.doe@example.org"));
        verify(products).setRepositoryName(eq("Repository Name"));
        verify(products).setUserRepoToken(eq("ABC123"));
        verify(productRepository).findById(eq(1L));
        assertSame(productDTO, actualVewprodactsResult);
    }
}
