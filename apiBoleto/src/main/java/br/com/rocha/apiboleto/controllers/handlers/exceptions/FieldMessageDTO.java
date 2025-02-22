package br.com.rocha.apiboleto.controllers.handlers.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FieldMessageDTO {

    private String fieldName;
    private String message;
}
