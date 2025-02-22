package br.com.rocha.apiboleto.controllers.handlers.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
public class CustomErrorDTO {

    private Instant timestamp;
    private Integer status;
    private String error;
    private String path;
}
