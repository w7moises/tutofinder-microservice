package com.tutofinder.customer.util;

import com.tutofinder.customer.dto.FatherDto;
import com.tutofinder.customer.entities.Father;
import org.modelmapper.Conditions;
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

    public List<FatherDto> convertEntityToDto(List<Father> fathers) {
        return fathers.stream()
                .map(father -> modelMapper.map(father, FatherDto.class))
                .collect(Collectors.toList());
    }
}
