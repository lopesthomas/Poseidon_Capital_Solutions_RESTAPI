package com.nnk.springboot.service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.services.TradeService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TradeServiceTest {

    @Mock
    private TradeRepository tradeRepository;

    @InjectMocks
    private TradeService tradeService;

    private Trade trade1;
    private Trade trade2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        trade1 = new Trade();
        trade1.setAccount("TradeAccount1");
        trade1.setType("Buy");
        trade1.setBuyQuantity(100d);

        trade2 = new Trade();
        trade2.setAccount("TradeAccount2");
        trade2.setType("Sell");
        trade2.setBuyQuantity(200d);
    }

    @Test
    void testFindAll() {
        when(tradeRepository.findAll()).thenReturn(Arrays.asList(trade1, trade2));

        List<Trade> result = tradeService.findAll();

        assertEquals(2, result.size());
        verify(tradeRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        trade1.setId(1);
        when(tradeRepository.findById(1)).thenReturn(Optional.of(trade1));

        Trade result = tradeService.findById(1);

        assertNotNull(result);
        assertEquals("TradeAccount1", result.getAccount());
        verify(tradeRepository, times(1)).findById(1);
    }

    @Test
    void testSave() {
        trade1.setBuyQuantity(150d);
        when(tradeRepository.save(trade1)).thenReturn(trade1);

        tradeService.save(trade1);

        verify(tradeRepository, times(1)).save(trade1);
        assertEquals(150d, trade1.getBuyQuantity());
    }

    @Test
    void testDelete() {
        tradeService.delete(1);
        verify(tradeRepository, times(1)).deleteById(1);
    }
}
