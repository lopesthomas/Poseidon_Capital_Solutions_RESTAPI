package com.nnk.springboot.service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.services.RatingService;

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

class RatingServiceTest {

    @Mock
    private RatingRepository ratingRepository;

    @InjectMocks
    private RatingService ratingService;

    private Rating rating1;
    private Rating rating2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        rating1 = new Rating();
        rating1.setMoodysRating("Moodys A");
        rating1.setSandPRating("S&P A");
        rating1.setFitchRating("Fitch A");
        rating1.setOrder(1);;

        rating2 = new Rating();
        rating2.setMoodysRating("Moodys B");
        rating2.setSandPRating("S&P B");
        rating2.setFitchRating("Fitch B");
        rating2.setOrder(2);
    }

    @Test
    void testFindAll() {
        when(ratingRepository.findAll()).thenReturn(Arrays.asList(rating1, rating2));

        List<Rating> result = ratingService.findAll();

        assertEquals(2, result.size());
        verify(ratingRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        rating1.setId(1);
        when(ratingRepository.findById(1)).thenReturn(Optional.of(rating1));

        Rating result = ratingService.findById(1);

        assertNotNull(result);
        assertEquals("Moodys A", result.getMoodysRating());
        verify(ratingRepository, times(1)).findById(1);
    }

    @Test
    void testSave() {
        rating1.setOrder(3);
        when(ratingRepository.save(rating1)).thenReturn(rating1);

        ratingService.save(rating1);

        verify(ratingRepository, times(1)).save(rating1);
        assertEquals(3, rating1.getOrder());
    }

    @Test
    void testDelete() {
        ratingService.delete(1);
        verify(ratingRepository, times(1)).deleteById(1);
    }
}
