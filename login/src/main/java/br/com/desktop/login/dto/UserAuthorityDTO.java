package br.com.desktop.login.dto;

public class UserAuthorityDTO {
    private Long user;
    private Long authority;

    public Long getAuthority() {
        return authority;
    }

    public void setAuthority(Long authority) {
        this.authority = authority;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }
}
