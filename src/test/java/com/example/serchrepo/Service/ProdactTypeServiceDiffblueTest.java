package com.example.serchrepo.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.serchrepo.Model.ProductType;
import com.example.serchrepo.Repository.ProdactTypeRepository;
import com.example.serchrepo.Request.ProduactTypeRiquest;
import com.example.serchrepo.Response.ProduactTypeResponce;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ProdactTypeService.class})
@ExtendWith(SpringExtension.class)
class ProdactTypeServiceDiffblueTest {
  @MockBean
  private ProdactTypeRepository prodactTypeRepository;

  @Autowired
  private ProdactTypeService prodactTypeService;

  /**
   * Method under test: {@link ProdactTypeService#createPT(ProduactTypeRiquest)}
   */
  @Test
  void testCreatePT() {
    // Arrange
    ProductType productType = new ProductType();
    productType.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
    productType.setId(1L);
    productType.setProductTypeDescription("Product Type Description");
    productType.setProductTypeName("Product Type Name");
    when(prodactTypeRepository.findByProductTypeName(Mockito.<String>any())).thenReturn(productType);

    // Act
    ProduactTypeResponce actualCreatePTResult = prodactTypeService.createPT(new ProduactTypeRiquest());

    // Assert
    verify(prodactTypeRepository).findByProductTypeName(isNull());
    assertNull(actualCreatePTResult);
  }

  /**
   * Method under test: {@link ProdactTypeService#createPT(ProduactTypeRiquest)}
   */
  @Test
  void testCreatePT2() {
    // Arrange
    when(prodactTypeRepository.findByProductTypeName(Mockito.<String>any())).thenThrow(new RuntimeException("foo"));

    // Act and Assert
    assertThrows(RuntimeException.class, () -> prodactTypeService.createPT(new ProduactTypeRiquest()));
    verify(prodactTypeRepository).findByProductTypeName(isNull());
  }

  /**
   * Method under test:
   * {@link ProdactTypeService#update(Long, ProduactTypeRiquest)}
   */
  @Test
  void testUpdate() {
    // Arrange
    ProductType productType = new ProductType();
    productType.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
    productType.setId(1L);
    productType.setProductTypeDescription("Product Type Description");
    productType.setProductTypeName("Product Type Name");
    Optional<ProductType> ofResult = Optional.of(productType);

    ProductType productType2 = new ProductType();
    productType2.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
    productType2.setId(1L);
    productType2.setProductTypeDescription("Product Type Description");
    productType2.setProductTypeName("Product Type Name");
    when(prodactTypeRepository.save(Mockito.<ProductType>any())).thenReturn(productType2);
    when(prodactTypeRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

    // Act
    ProduactTypeResponce actualUpdateResult = prodactTypeService.update(1L, new ProduactTypeRiquest());

    // Assert
    verify(prodactTypeRepository).findById(eq(1L));
    verify(prodactTypeRepository).save(isA(ProductType.class));
    assertEquals("1970-01-01", actualUpdateResult.getCreatedDate().toLocalDate().toString());
    assertEquals("Product Type Description", actualUpdateResult.getProductTypeDescription());
    assertEquals("Product Type Name", actualUpdateResult.getProductTypeName());
    assertEquals(1L, actualUpdateResult.getId().longValue());
  }

  /**
   * Method under test:
   * {@link ProdactTypeService#update(Long, ProduactTypeRiquest)}
   */
  @Test
  void testUpdate2() {
    // Arrange
    ProductType productType = new ProductType();
    productType.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
    productType.setId(1L);
    productType.setProductTypeDescription("Product Type Description");
    productType.setProductTypeName("Product Type Name");
    Optional<ProductType> ofResult = Optional.of(productType);
    when(prodactTypeRepository.save(Mockito.<ProductType>any())).thenThrow(new RuntimeException("foo"));
    when(prodactTypeRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

    // Act and Assert
    assertThrows(RuntimeException.class, () -> prodactTypeService.update(1L, new ProduactTypeRiquest()));
    verify(prodactTypeRepository).findById(eq(1L));
    verify(prodactTypeRepository).save(isA(ProductType.class));
  }

  /**
   * Method under test:
   * {@link ProdactTypeService#update(Long, ProduactTypeRiquest)}
   */
  @Test
  void testUpdate3() {
    // Arrange
    ProductType productType = new ProductType();
    productType.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
    productType.setId(1L);
    productType.setProductTypeDescription("Product Type Description");
    productType.setProductTypeName("Product Type Name");
    Optional<ProductType> ofResult = Optional.of(productType);
    ProductType productType2 = mock(ProductType.class);
    ProduactTypeResponce produactTypeResponce = new ProduactTypeResponce();
    when(productType2.produactTypeResponce()).thenReturn(produactTypeResponce);
    doNothing().when(productType2).setCreatedDate(Mockito.<LocalDateTime>any());
    doNothing().when(productType2).setId(Mockito.<Long>any());
    doNothing().when(productType2).setProductTypeDescription(Mockito.<String>any());
    doNothing().when(productType2).setProductTypeName(Mockito.<String>any());
    productType2.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
    productType2.setId(1L);
    productType2.setProductTypeDescription("Product Type Description");
    productType2.setProductTypeName("Product Type Name");
    when(prodactTypeRepository.save(Mockito.<ProductType>any())).thenReturn(productType2);
    when(prodactTypeRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

    // Act
    ProduactTypeResponce actualUpdateResult = prodactTypeService.update(1L, new ProduactTypeRiquest());

    // Assert
    verify(productType2).produactTypeResponce();
    verify(productType2).setCreatedDate(isA(LocalDateTime.class));
    verify(productType2).setId(eq(1L));
    verify(productType2).setProductTypeDescription(eq("Product Type Description"));
    verify(productType2).setProductTypeName(eq("Product Type Name"));
    verify(prodactTypeRepository).findById(eq(1L));
    verify(prodactTypeRepository).save(isA(ProductType.class));
    assertSame(produactTypeResponce, actualUpdateResult);
  }

  /**
   * Method under test:
   * {@link ProdactTypeService#update(Long, ProduactTypeRiquest)}
   */
  @Test
  void testUpdate4() {
    // Arrange
    ProductType productType = mock(ProductType.class);
    doNothing().when(productType).setCreatedDate(Mockito.<LocalDateTime>any());
    doNothing().when(productType).setId(Mockito.<Long>any());
    doNothing().when(productType).setProductTypeDescription(Mockito.<String>any());
    doNothing().when(productType).setProductTypeName(Mockito.<String>any());
    productType.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
    productType.setId(1L);
    productType.setProductTypeDescription("Product Type Description");
    productType.setProductTypeName("Product Type Name");
    Optional<ProductType> ofResult = Optional.of(productType);
    ProductType productType2 = mock(ProductType.class);
    ProduactTypeResponce produactTypeResponce = new ProduactTypeResponce();
    when(productType2.produactTypeResponce()).thenReturn(produactTypeResponce);
    doNothing().when(productType2).setCreatedDate(Mockito.<LocalDateTime>any());
    doNothing().when(productType2).setId(Mockito.<Long>any());
    doNothing().when(productType2).setProductTypeDescription(Mockito.<String>any());
    doNothing().when(productType2).setProductTypeName(Mockito.<String>any());
    productType2.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
    productType2.setId(1L);
    productType2.setProductTypeDescription("Product Type Description");
    productType2.setProductTypeName("Product Type Name");
    when(prodactTypeRepository.save(Mockito.<ProductType>any())).thenReturn(productType2);
    when(prodactTypeRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

    // Act
    ProduactTypeResponce actualUpdateResult = prodactTypeService.update(1L, new ProduactTypeRiquest());

    // Assert
    verify(productType2).produactTypeResponce();
    verify(productType2).setCreatedDate(isA(LocalDateTime.class));
    verify(productType).setCreatedDate(isA(LocalDateTime.class));
    verify(productType2).setId(eq(1L));
    verify(productType).setId(eq(1L));
    verify(productType, atLeast(1)).setProductTypeDescription(Mockito.<String>any());
    verify(productType2).setProductTypeDescription(eq("Product Type Description"));
    verify(productType, atLeast(1)).setProductTypeName(Mockito.<String>any());
    verify(productType2).setProductTypeName(eq("Product Type Name"));
    verify(prodactTypeRepository).findById(eq(1L));
    verify(prodactTypeRepository).save(isA(ProductType.class));
    assertSame(produactTypeResponce, actualUpdateResult);
  }

  /**
   * Method under test:
   * {@link ProdactTypeService#update(Long, ProduactTypeRiquest)}
   */
  @Test
  void testUpdate5() {
    // Arrange
    Optional<ProductType> emptyResult = Optional.empty();
    when(prodactTypeRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);
    ProductType productType = mock(ProductType.class);
    doNothing().when(productType).setCreatedDate(Mockito.<LocalDateTime>any());
    doNothing().when(productType).setId(Mockito.<Long>any());
    doNothing().when(productType).setProductTypeDescription(Mockito.<String>any());
    doNothing().when(productType).setProductTypeName(Mockito.<String>any());
    productType.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
    productType.setId(1L);
    productType.setProductTypeDescription("Product Type Description");
    productType.setProductTypeName("Product Type Name");

    // Act and Assert
    assertThrows(RuntimeException.class, () -> prodactTypeService.update(1L, new ProduactTypeRiquest()));
    verify(productType).setCreatedDate(isA(LocalDateTime.class));
    verify(productType).setId(eq(1L));
    verify(productType).setProductTypeDescription(eq("Product Type Description"));
    verify(productType).setProductTypeName(eq("Product Type Name"));
    verify(prodactTypeRepository).findById(eq(1L));
  }

  /**
   * Method under test: {@link ProdactTypeService#getAll()}
   */
  @Test
  void testGetAll() {
    // Arrange
    when(prodactTypeRepository.findAll()).thenReturn(new ArrayList<>());

    // Act
    List<ProduactTypeResponce> actualAll = prodactTypeService.getAll();

    // Assert
    verify(prodactTypeRepository).findAll();
    assertTrue(actualAll.isEmpty());
  }

  /**
   * Method under test: {@link ProdactTypeService#getAll()}
   */
  @Test
  void testGetAll2() {
    // Arrange
    ProductType productType = new ProductType();
    productType.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
    productType.setId(1L);
    productType.setProductTypeDescription("Product Type Description");
    productType.setProductTypeName("Product Type Name");

    ArrayList<ProductType> productTypeList = new ArrayList<>();
    productTypeList.add(productType);
    when(prodactTypeRepository.findAll()).thenReturn(productTypeList);

    // Act
    List<ProduactTypeResponce> actualAll = prodactTypeService.getAll();

    // Assert
    verify(prodactTypeRepository).findAll();
    assertEquals(1, actualAll.size());
    ProduactTypeResponce getResult = actualAll.get(0);
    assertEquals("1970-01-01", getResult.getCreatedDate().toLocalDate().toString());
    assertEquals("Product Type Description", getResult.getProductTypeDescription());
    assertEquals("Product Type Name", getResult.getProductTypeName());
    assertEquals(1L, getResult.getId().longValue());
  }

  /**
   * Method under test: {@link ProdactTypeService#getAll()}
   */
  @Test
  void testGetAll3() {
    // Arrange
    ProductType productType = new ProductType();
    productType.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
    productType.setId(1L);
    productType.setProductTypeDescription("Product Type Description");
    productType.setProductTypeName("Product Type Name");

    ProductType productType2 = new ProductType();
    productType2.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
    productType2.setId(2L);
    productType2.setProductTypeDescription("com.example.serchrepo.Model.ProductType");
    productType2.setProductTypeName("com.example.serchrepo.Model.ProductType");

    ArrayList<ProductType> productTypeList = new ArrayList<>();
    productTypeList.add(productType2);
    productTypeList.add(productType);
    when(prodactTypeRepository.findAll()).thenReturn(productTypeList);

    // Act
    List<ProduactTypeResponce> actualAll = prodactTypeService.getAll();

    // Assert
    verify(prodactTypeRepository).findAll();
    assertEquals(2, actualAll.size());
    ProduactTypeResponce getResult = actualAll.get(0);
    assertEquals("1970-01-01", getResult.getCreatedDate().toLocalDate().toString());
    ProduactTypeResponce getResult2 = actualAll.get(1);
    assertEquals("1970-01-01", getResult2.getCreatedDate().toLocalDate().toString());
    assertEquals("Product Type Description", getResult2.getProductTypeDescription());
    assertEquals("Product Type Name", getResult2.getProductTypeName());
    assertEquals("com.example.serchrepo.Model.ProductType", getResult.getProductTypeDescription());
    assertEquals("com.example.serchrepo.Model.ProductType", getResult.getProductTypeName());
    assertEquals(1L, getResult2.getId().longValue());
    assertEquals(2L, getResult.getId().longValue());
  }

  /**
   * Method under test: {@link ProdactTypeService#getAll()}
   */
  @Test
  void testGetAll4() {
    // Arrange
    when(prodactTypeRepository.findAll()).thenThrow(new RuntimeException("foo"));

    // Act and Assert
    assertThrows(RuntimeException.class, () -> prodactTypeService.getAll());
    verify(prodactTypeRepository).findAll();
  }
}
