package com.car.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Entity
public class CarLogBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    @Lob
    private byte[] fileContent;
    private String fileType;
    private long fileSize;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    @OneToOne
    @JoinColumn(name = "car_id")
    @JsonBackReference
    private Car car;

    public CarLogBook() {}

    public CarLogBook(String fileName, byte[] fileContent, String fileType, long fileSize) {
        this.fileName = fileName;
        this.fileContent = fileContent;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.created_at = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
    }

    @PrePersist
    protected void onCreate() {
        created_at = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public byte[] getFileContent() {
        return fileContent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getFileContent(byte[] bytes) {
        return fileContent;
    }

    public void setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
