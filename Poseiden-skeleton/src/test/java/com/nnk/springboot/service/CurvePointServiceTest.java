package com.nnk.springboot.service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.services.CurvePointService;

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

class CurvePointServiceTest {

    @Mock
    private CurvePointRepository curvePointRepository;

    @InjectMocks
    private CurvePointService curvePointService;

    private CurvePoint curve1;
    private CurvePoint curve2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        curve1 = new CurvePoint();
        curve1.setCurveId(100);
        curve1.setTerm(10d);
        curve1.setValue(20d);

        curve2 = new CurvePoint();
        curve2.setCurveId(200);
        curve2.setTerm(30d);
        curve2.setValue(40d);
    }

    @Test
    void testFindAll() {
        when(curvePointRepository.findAll()).thenReturn(Arrays.asList(curve1, curve2));

        List<CurvePoint> result = curvePointService.findAll();

        assertEquals(2, result.size());
        verify(curvePointRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        curve1.setId(1);
        when(curvePointRepository.findById(1)).thenReturn(Optional.of(curve1));

        CurvePoint result = curvePointService.findById(1);

        assertNotNull(result);
        assertEquals(100, result.getCurveId());
        verify(curvePointRepository, times(1)).findById(1);
    }

    @Test
    void testSave() {
        curve1.setValue(50d);
        when(curvePointRepository.save(curve1)).thenReturn(curve1);

        curvePointService.save(curve1);

        verify(curvePointRepository, times(1)).save(curve1);
        assertEquals(50d, curve1.getValue());
    }

    @Test
    void testDelete() {
        curvePointService.delete(1);
        verify(curvePointRepository, times(1)).deleteById(1);
    }
}
