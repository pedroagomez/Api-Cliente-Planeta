package com.planeta.Planeta.Mapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Mapper {

@Bean  // al utilizar esto puedo ejecutar el metodop para crar una instacioa del objeto
    public ModelMapper modelMapper()
    {
        return new ModelMapper();
    }
}
