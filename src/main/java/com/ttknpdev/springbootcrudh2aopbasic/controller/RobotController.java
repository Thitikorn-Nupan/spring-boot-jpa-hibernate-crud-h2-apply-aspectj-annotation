package com.ttknpdev.springbootcrudh2aopbasic.controller;

import com.ttknpdev.springbootcrudh2aopbasic.entity.Robot;
import com.ttknpdev.springbootcrudh2aopbasic.service.RobotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api")
public class RobotController {

    private final RobotService<Robot> service;

    @Autowired
    public RobotController(RobotService<Robot> service) {
        this.service = service;
    }

    // create?name=RX-66&status=enable&price=750000
    @GetMapping(value = "/create1")
    private ResponseEntity<Robot> create(@RequestParam("name") String name ,@RequestParam("status") String status ,@RequestParam("price") Double price){
        Robot robot = new Robot(name,status,price);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(service.create(robot));
    }

    @PostMapping(value = "/create2")
    private ResponseEntity<Robot> create(@RequestBody Robot robot){
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(service.create(robot));
    }

    @GetMapping(value = "/reads")
    private ResponseEntity<List<Robot>> reads(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.reads());
    }

    @GetMapping(value = "/read/{id}")
    private ResponseEntity<Robot> read(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.read(id));
    }

    @PutMapping(value = "/update/{id}")
    private ResponseEntity<Robot> update(@PathVariable Long id , @RequestBody Robot robot){
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(service.update(robot,id));
    }

    @DeleteMapping(value = "/delete/{id}")
    private ResponseEntity<Map<String,Robot>> delete(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(service.delete(id));
    }

}
