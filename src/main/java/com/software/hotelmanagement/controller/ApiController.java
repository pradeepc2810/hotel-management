package com.software.hotelmanagement.controller;

import com.software.hotelmanagement.Exception.NoHotelsFoundException;
import com.software.hotelmanagement.beans.ErrorResponse;
import com.software.hotelmanagement.beans.Hotel;
import com.software.hotelmanagement.services.HotelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/hotels")
public class ApiController {

    private HotelService hotelService;

    public ApiController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> HotelExceptionHandler(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> HotelExceptionHandler(NoHotelsFoundException e) {;
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
    }

    @GetMapping("/cheapestHotel")
    public ResponseEntity<Hotel> findCheapestHotel() throws NoHotelsFoundException {
        log.info("API - cheapestHotel, Body: {}");
        return ResponseEntity.ok(hotelService.findCheapestHotel());
    }
}
