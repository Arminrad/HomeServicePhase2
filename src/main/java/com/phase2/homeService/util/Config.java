package com.phase2.homeService.util;

import org.dozer.DozerBeanMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public DozerBeanMapper dozerBeanMapper(){
        return new DozerBeanMapper();
    }

    @Bean
    public ModelMapper modelMapper() {return new ModelMapper();}
}
