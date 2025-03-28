package com.car.controllers;

import com.car.models.Car;
import com.car.models.CarLogBook;
import com.car.models.User;
import com.car.payload.ApiResponse;
import com.car.payload.LogBookDTO;
import com.car.services.CarLogBookServices;
import com.car.services.CarServices;
import com.car.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.util.*;

@RestController
@RequestMapping("/api/car/logbook")
public class CarLogBookController {
    private static final Logger log = LoggerFactory.getLogger(CarLogBookController.class);
    private final CarLogBookServices carLogBookServices;
    private final CarServices carServices;
    private final UserService userService;

    public CarLogBookController(CarLogBookServices carLogBookServices, CarServices carServices, UserService userService) {
        this.carLogBookServices = carLogBookServices;
        this.carServices = carServices;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<LogBookDTO>>> getAllLogBooks() {
        List<CarLogBook> logBooks = carLogBookServices.getAllBooks();
        List<LogBookDTO> logBookDTOS = new ArrayList<>();

        logBooks.forEach(logBook -> {
            logBookDTOS.add(new LogBookDTO(logBook));
        });

        return ResponseEntity.ok(ApiResponse.success("Got the car logs", logBookDTOS));
    }

    @PostMapping("/upload/{id}")
    public ResponseEntity<ApiResponse<?>> uploadCarLogBook(@PathVariable int id, @RequestBody MultipartFile file) throws Exception {


        try{
            Optional<Car> car = carServices.getById(id);


            CarLogBook logBook = new CarLogBook();
            logBook.setFileContent(file.getBytes());
            logBook.setFileName(file.getOriginalFilename());
            logBook.setFileType(file.getContentType());
            logBook.setFileSize(file.getSize());
            logBook.setCar(car.get());

            CarLogBook savedBook = carLogBookServices.uploadCarLogBook(id, logBook);

            LogBookDTO logBookDTO = new LogBookDTO(savedBook);

            return ResponseEntity.ok(ApiResponse.success("Log Book added", logBookDTO));
        }catch ( DataIntegrityViolationException e){
            Map<String, String> errors = new HashMap<>();
            errors.put("message", e.getMessage());
            errors.put("error_code", "404");
            return ResponseEntity.ok(ApiResponse.error(e.getMessage(), errors));
        } catch (Exception e) {
            Map<String, String> errors = new HashMap<>();
            errors.put("error", e.getMessage()+"for car id: "+id);
            return ResponseEntity.ok(ApiResponse.error(e.getMessage(), errors));
        }
    }

    @GetMapping("/download/{user_id}/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long id){
        CarLogBook pdfFile = carLogBookServices.getLogBookById(id);
        return ResponseEntity.ok().header("Content-Type", "application/pdf").body(pdfFile.getFileContent());
    }
}
