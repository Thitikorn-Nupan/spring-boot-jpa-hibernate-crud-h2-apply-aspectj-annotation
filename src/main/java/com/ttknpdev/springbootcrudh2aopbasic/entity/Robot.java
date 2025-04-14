package com.ttknpdev.springbootcrudh2aopbasic.entity;

import jakarta.persistence.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Entity
@Table(name= "robots")
public class Robot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String status;
    private Double price;
    private String build = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()); // mapped timestamp in mysql

    public Robot(String name, String status, Double price) {
        this.name = name;
        this.status = status;
        this.price = price;
    }

    public Robot() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getBuild() {
        return build;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    @Override
    public String toString() {
        return "Robot{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", price=" + price +
                ", build='" + build + '\'' +
                '}';
    }
}
