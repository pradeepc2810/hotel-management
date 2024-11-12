package com.software.hotelmanagement.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;

@Data
public class NoHotelsFoundException extends RuntimeException {
    public NoHotelsFoundException() {
        super("No Hotels Found.");
    }
}
