package com.dhruv.tourBookingApplication.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ErrorResponseDto {
    private Integer status;
    private String message;
    private LocalDateTime timeStamp;
}
