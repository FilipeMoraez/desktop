package br.com.desktop.login.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class ResponseErrorDTO {
    @Getter
    @Setter
    private String message;
}
