package com.ttknpdev.springbootcrudh2aopbasic.service;

import com.ttknpdev.springbootcrudh2aopbasic.exception.handler.ResourceNotFound;

import java.util.List;
import java.util.Map;

public interface RobotService <T>{
    T create(T obj);
    T read(Long id) throws ResourceNotFound; // should have throw exception
    T update(T obj,Long id) throws ResourceNotFound;
    List<T> reads();
    Map<String,T> delete(Long id) throws ResourceNotFound;
}
