package com.tutofinder.customer.service;

import com.tutofinder.customer.dto.FatherDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FatherService {
    FatherDto getFatherById(Long fatherId);
    List<FatherDto> getFathers();
    FatherDto createPadre(FatherDto createFatherDto, MultipartFile file) throws IOException;
    FatherDto updatePadre(FatherDto updateFatherDto, Long fatherId, MultipartFile file) throws IOException;
    String deleteFather(Long fatherId);
}
