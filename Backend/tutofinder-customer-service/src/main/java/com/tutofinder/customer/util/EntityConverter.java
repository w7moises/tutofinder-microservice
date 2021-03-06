package com.tutofinder.customer.util;

import com.tutofinder.customer.dto.FatherDto;
import com.tutofinder.customer.dto.StudentDto;
import com.tutofinder.customer.dto.TeacherDto;
import com.tutofinder.customer.dto.UserDto;
import com.tutofinder.customer.dto.create.CreateFatherDto;
import com.tutofinder.customer.dto.create.CreateStudentDto;
import com.tutofinder.customer.dto.create.CreateTeacherDto;
import com.tutofinder.customer.entities.Father;
import com.tutofinder.customer.entities.Student;
import com.tutofinder.customer.entities.Teacher;
import com.tutofinder.customer.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EntityConverter {
    @Autowired
    private ModelMapper modelMapper;

    public FatherDto convertFatherToDto(Father father) {
        return modelMapper.map(father,FatherDto.class);
    }
    public Father convertFatherToEntity(FatherDto fatherDto) {
        return modelMapper.map(fatherDto, Father.class);
    }
    public Father convertCreateFatherToEntity(CreateFatherDto fatherDto) {
        return modelMapper.map(fatherDto, Father.class);
    }
    public UserDto convertUserToDto(User user) {
        return modelMapper.map(user,UserDto.class);
    }
    public User convertUserToEntity(UserDto userDto) {
        return modelMapper.map(userDto,User.class);
    }


    public StudentDto convertStudentToDto(Student student) {
        return modelMapper.map(student,StudentDto.class);
    }
    public Student convertStudentToEntity(StudentDto studentDto) {
        return modelMapper.map(studentDto, Student.class);
    }
    public Student convertCreateStudentToEntity(CreateStudentDto studentDto) {
        return modelMapper.map(studentDto, Student.class);
    }
    public TeacherDto convertTeacherToDto(Teacher teacher) {
        return modelMapper.map(teacher,TeacherDto.class);
    }
    public Teacher convertTeacherToEntity(TeacherDto teacherDto) {
        return modelMapper.map(teacherDto,Teacher.class);
    }
    public Teacher convertCreateTeacherToEntity(CreateTeacherDto teacherDto) {
        return modelMapper.map(teacherDto, Teacher.class);
    }
    public List<StudentDto> convertStudentToDto(List<Student> students) {
        return students.stream()
                .map(student -> modelMapper.map(student, StudentDto.class))
                .collect(Collectors.toList());
    }
    public List<FatherDto> convertFatherToDto(List<Father> fathers) {
        return fathers.stream()
                .map(father -> modelMapper.map(father, FatherDto.class))
                .collect(Collectors.toList());
    }
    public List<TeacherDto> convertTeacherToDto(List<Teacher> teachers) {
        return teachers.stream()
                .map(teacher -> modelMapper.map(teacher,TeacherDto.class))
                .collect(Collectors.toList());
    }

    public List<UserDto> convertUserToDto(List<User> users) {
        return users.stream()
                .map(user -> modelMapper.map(user,UserDto.class))
                .collect(Collectors.toList());
    }
}
