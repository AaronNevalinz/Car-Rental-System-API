
package com.car.payload;

import com.car.models.Car;
import com.car.models.CarLogBook;

public class LogBookDTO {
    private Long id;
    private String fileName;
    private String fileType;
    private long fileSize;
    private Long carId;


    public LogBookDTO(CarLogBook logBook) {
        this.id = logBook.getId();
        this.fileName = logBook.getFileName();
        this.fileType = logBook.getFileType();
        this.fileSize = logBook.getFileSize();
        this.carId = logBook.getCar().getId();
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
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


    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}
