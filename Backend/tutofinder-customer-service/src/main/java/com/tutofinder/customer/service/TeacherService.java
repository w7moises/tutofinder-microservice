package com.tutofinder.customer.service;

import com.tutofinder.customer.entities.Teacher;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface TeacherService {
    Teacher getTeacherById(Long teacherId);
    Teacher getTeacherByEmail(String email);
    List<Teacher> getTeachers();
    Teacher createTeacher(Teacher createTeacher,String username) throws IOException;
    Teacher updateTeacher(Teacher updateTeacher, Long teacherId, MultipartFile file) throws IOException;
    String deleteTeacher(Long teacherId);
}
