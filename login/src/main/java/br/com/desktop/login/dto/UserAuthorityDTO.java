package br.com.desktop.login.dto;

import lombok.Getter;
import lombok.Setter;

public class UserAuthorityDTO {
    @Getter
    @Setter
    private Long user;
    @Getter
    @Setter
    private Long authority;
}
