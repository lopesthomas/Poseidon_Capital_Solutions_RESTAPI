package com.nnk.springboot.service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.services.RuleNameService;

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

class RuleNameServiceTest {

    @Mock
    private RuleNameRepository ruleNameRepository;

    @InjectMocks
    private RuleNameService ruleNameService;

    private RuleName rule1;
    private RuleName rule2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        rule1 = new RuleName();
        rule1.setName("Rule A");
        rule1.setDescription("Description A");
        rule1.setJson("jsonA");
        rule1.setSqlPart("sqlA");
        rule1.setSql("select * from A");
        rule1.setTemplate("templateA");

        rule2 = new RuleName();
        rule2.setName("Rule B");
        rule2.setDescription("Description B");
        rule2.setJson("jsonB");
        rule2.setSqlPart("sqlB");
        rule2.setSql("select * from B");
        rule2.setTemplate("templateB");
    }

    @Test
    void testFindAll() {
        when(ruleNameRepository.findAll()).thenReturn(Arrays.asList(rule1, rule2));

        List<RuleName> result = ruleNameService.findAll();

        assertEquals(2, result.size());
        verify(ruleNameRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        rule1.setId(1);
        when(ruleNameRepository.findById(1)).thenReturn(Optional.of(rule1));

        RuleName result = ruleNameService.findById(1);

        assertNotNull(result);
        assertEquals("Rule A", result.getName());
        verify(ruleNameRepository, times(1)).findById(1);
    }

    @Test
    void testSave() {
        rule1.setDescription("Updated Desc");
        when(ruleNameRepository.save(rule1)).thenReturn(rule1);

        ruleNameService.save(rule1);

        verify(ruleNameRepository, times(1)).save(rule1);
        assertEquals("Updated Desc", rule1.getDescription());
    }

    @Test
    void testDelete() {
        ruleNameService.delete(1);
        verify(ruleNameRepository, times(1)).deleteById(1);
    }
}
