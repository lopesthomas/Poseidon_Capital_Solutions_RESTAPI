package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.BidListService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BidListServiceTest {

    @Mock
    private BidListRepository bidListRepository;

    @InjectMocks
    private BidListService bidListService;

    private BidList bid1;
    private BidList bid2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        bid1 = new BidList();
        bid1.setAccount("Account1");
        bid1.setType("Type1");
        bid1.setBidQuantity(10d);

        bid2 = new BidList();
        bid2.setAccount("Account2");
        bid2.setType("Type2");
        bid2.setBidQuantity(20d);
    }
    
    @Test
    void testFindAll() {

        when(bidListRepository.findAll()).thenReturn(Arrays.asList(bid1, bid2));

        List<BidList> result = bidListService.findAll();

        assertEquals(2, result.size());
        verify(bidListRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        bid1.setBidListId(1);
        when(bidListRepository.findById(1)).thenReturn(Optional.of(bid1));

        BidList result = bidListService.findById(1);

        assertNotNull(result);
        assertEquals("Account1", result.getAccount());
        verify(bidListRepository, times(1)).findById(1);
    }

    @Test
    void testSave() {
        bid1.setBidQuantity(30d);

        when(bidListRepository.save(bid1)).thenReturn(bid1);

        bidListService.save(bid1);
        verify(bidListRepository, times(1)).save(bid1);
        assertEquals(30d, bid1.getBidQuantity());
    }

    @Test
    void testDeleteById() {
        bidListService.deleteById(1);
        verify(bidListRepository, times(1)).deleteById(1);
    }
}

