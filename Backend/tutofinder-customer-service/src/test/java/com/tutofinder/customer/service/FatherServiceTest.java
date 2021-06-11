package com.tutofinder.customer.service;

import com.tutofinder.customer.repositories.FatherRepository;
import com.tutofinder.customer.util.TestDataUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FatherServiceTest {
    @InjectMocks
    private FatherService fatherService;

    @Mock
    private FatherRepository fatherRepository;

    @Before
    public  void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void aa(){
        Mockito.when(fatherRepository.findAll()).thenReturn(TestDataUtils.getMockFathers());
        assertEquals(true,fatherService.getFathers() != null);
        verify(fatherRepository).findAll();
    }

}
