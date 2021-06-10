package com.tutofinder.customer.service;

import com.tutofinder.customer.entities.Father;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FatherService {
    Father getFatherById(Long fatherId);
    List<Father> getFathers();
    Father createFather(Father createFather, MultipartFile file) throws IOException;
    Father updateFather(Father updateFather, Long fatherId, MultipartFile file) throws IOException;
    String deleteFather(Long fatherId);
    Father registerFavorite(Long fatherId,Long teacherId);
    String deleteFavorite(Long fatherId,Long teacherId);
}
