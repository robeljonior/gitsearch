package com.example.serchrepo.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.serchrepo.Model.Bank;
import com.example.serchrepo.Repository.BankRepository;
import com.example.serchrepo.Request.BankRequest;
import com.example.serchrepo.Response.BankResponce;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {BankService.class})
@ExtendWith(SpringExtension.class)
class BankServiceDiffblueTest {
    @MockBean
    private BankRepository bankRepository;

    @Autowired
    private BankService bankService;

    /**
     * Method under test: {@link BankService#createBank(BankRequest)}
     */
    @Test
    void testCreateBank() {
        // Arrange
        Bank bank = new Bank();
        bank.setBankDiscription("Bank Discription");
        bank.setBankName("Bank Name");
        bank.setId(1L);
        when(bankRepository.findByBankName(Mockito.<String>any())).thenReturn(bank);

        // Act
        BankResponce actualCreateBankResult = bankService.createBank(new BankRequest());

        // Assert
        verify(bankRepository).findByBankName(isNull());
        assertNull(actualCreateBankResult);
    }

    /**
     * Method under test: {@link BankService#updateBank(BankRequest)}
     */
    @Test
    void testUpdateBank() {
        // Arrange
        Bank bank = new Bank();
        bank.setBankDiscription("Bank Discription");
        bank.setBankName("Bank Name");
        bank.setId(1L);

        Bank bank2 = new Bank();
        bank2.setBankDiscription("Bank Discription");
        bank2.setBankName("Bank Name");
        bank2.setId(1L);
        when(bankRepository.save(Mockito.<Bank>any())).thenReturn(bank2);
        when(bankRepository.findByBankName(Mockito.<String>any())).thenReturn(bank);

        // Act
        BankResponce actualUpdateBankResult = bankService.updateBank(new BankRequest());

        // Assert
        verify(bankRepository).findByBankName(isNull());
        verify(bankRepository).save(isA(Bank.class));
        assertEquals("Bank Discription", actualUpdateBankResult.getBankDiscription());
        assertEquals("Bank Name", actualUpdateBankResult.getBankName());
        assertEquals(1L, actualUpdateBankResult.getId().longValue());
    }

    /**
     * Method under test: {@link BankService#updateBank(BankRequest)}
     */
    @Test
    void testUpdateBank2() {
        // Arrange
        Bank bank = new Bank();
        bank.setBankDiscription("Bank Discription");
        bank.setBankName("Bank Name");
        bank.setId(1L);
        Bank bank2 = mock(Bank.class);
        BankResponce bankResponce = new BankResponce();
        when(bank2.bankResponce()).thenReturn(bankResponce);
        doNothing().when(bank2).setBankDiscription(Mockito.<String>any());
        doNothing().when(bank2).setBankName(Mockito.<String>any());
        doNothing().when(bank2).setId(Mockito.<Long>any());
        bank2.setBankDiscription("Bank Discription");
        bank2.setBankName("Bank Name");
        bank2.setId(1L);
        when(bankRepository.save(Mockito.<Bank>any())).thenReturn(bank2);
        when(bankRepository.findByBankName(Mockito.<String>any())).thenReturn(bank);

        // Act
        BankResponce actualUpdateBankResult = bankService.updateBank(new BankRequest());

        // Assert
        verify(bank2).bankResponce();
        verify(bank2).setBankDiscription(eq("Bank Discription"));
        verify(bank2).setBankName(eq("Bank Name"));
        verify(bank2).setId(eq(1L));
        verify(bankRepository).findByBankName(isNull());
        verify(bankRepository).save(isA(Bank.class));
        assertSame(bankResponce, actualUpdateBankResult);
    }

    /**
     * Method under test: {@link BankService#updateBank(BankRequest)}
     */
    @Test
    void testUpdateBank3() {
        // Arrange
        Bank bank = mock(Bank.class);
        doNothing().when(bank).setBankDiscription(Mockito.<String>any());
        doNothing().when(bank).setBankName(Mockito.<String>any());
        doNothing().when(bank).setId(Mockito.<Long>any());
        bank.setBankDiscription("Bank Discription");
        bank.setBankName("Bank Name");
        bank.setId(1L);
        Bank bank2 = mock(Bank.class);
        BankResponce bankResponce = new BankResponce();
        when(bank2.bankResponce()).thenReturn(bankResponce);
        doNothing().when(bank2).setBankDiscription(Mockito.<String>any());
        doNothing().when(bank2).setBankName(Mockito.<String>any());
        doNothing().when(bank2).setId(Mockito.<Long>any());
        bank2.setBankDiscription("Bank Discription");
        bank2.setBankName("Bank Name");
        bank2.setId(1L);
        when(bankRepository.save(Mockito.<Bank>any())).thenReturn(bank2);
        when(bankRepository.findByBankName(Mockito.<String>any())).thenReturn(bank);

        // Act
        BankResponce actualUpdateBankResult = bankService.updateBank(new BankRequest());

        // Assert
        verify(bank2).bankResponce();
        verify(bank, atLeast(1)).setBankDiscription(Mockito.<String>any());
        verify(bank2).setBankDiscription(eq("Bank Discription"));
        verify(bank, atLeast(1)).setBankName(Mockito.<String>any());
        verify(bank2).setBankName(eq("Bank Name"));
        verify(bank2).setId(eq(1L));
        verify(bank).setId(eq(1L));
        verify(bankRepository).findByBankName(isNull());
        verify(bankRepository).save(isA(Bank.class));
        assertSame(bankResponce, actualUpdateBankResult);
    }

    /**
     * Method under test: {@link BankService#getall()}
     */
    @Test
    void testGetall() {
        // Arrange
        when(bankRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<BankResponce> actualGetallResult = bankService.getall();

        // Assert
        verify(bankRepository).findAll();
        assertTrue(actualGetallResult.isEmpty());
    }

    /**
     * Method under test: {@link BankService#getall()}
     */
    @Test
    void testGetall2() {
        // Arrange
        Bank bank = new Bank();
        bank.setBankDiscription("Bank Discription");
        bank.setBankName("Bank Name");
        bank.setId(1L);

        ArrayList<Bank> bankList = new ArrayList<>();
        bankList.add(bank);
        when(bankRepository.findAll()).thenReturn(bankList);

        // Act
        List<BankResponce> actualGetallResult = bankService.getall();

        // Assert
        verify(bankRepository).findAll();
        assertEquals(1, actualGetallResult.size());
        BankResponce getResult = actualGetallResult.get(0);
        assertEquals("Bank Discription", getResult.getBankDiscription());
        assertEquals("Bank Name", getResult.getBankName());
        assertEquals(1L, getResult.getId().longValue());
    }

    /**
     * Method under test: {@link BankService#getall()}
     */
    @Test
    void testGetall3() {
        // Arrange
        Bank bank = new Bank();
        bank.setBankDiscription("Bank Discription");
        bank.setBankName("Bank Name");
        bank.setId(1L);

        Bank bank2 = new Bank();
        bank2.setBankDiscription("com.example.serchrepo.Model.Bank");
        bank2.setBankName("com.example.serchrepo.Model.Bank");
        bank2.setId(2L);

        ArrayList<Bank> bankList = new ArrayList<>();
        bankList.add(bank2);
        bankList.add(bank);
        when(bankRepository.findAll()).thenReturn(bankList);

        // Act
        List<BankResponce> actualGetallResult = bankService.getall();

        // Assert
        verify(bankRepository).findAll();
        assertEquals(2, actualGetallResult.size());
        BankResponce getResult = actualGetallResult.get(1);
        assertEquals("Bank Discription", getResult.getBankDiscription());
        assertEquals("Bank Name", getResult.getBankName());
        BankResponce getResult2 = actualGetallResult.get(0);
        assertEquals("com.example.serchrepo.Model.Bank", getResult2.getBankDiscription());
        assertEquals("com.example.serchrepo.Model.Bank", getResult2.getBankName());
        assertEquals(1L, getResult.getId().longValue());
        assertEquals(2L, getResult2.getId().longValue());
    }
}
