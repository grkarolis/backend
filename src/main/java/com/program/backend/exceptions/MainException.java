package com.program.backend.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class MainException extends RuntimeException {

    protected String messageCode;

    protected HttpStatus statusCode;
}
