package com.azienda.sweetter.model.entity.dto;

public class LoginDTO {
    protected String username, password;

    public LoginDTO() {}

    public LoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public LoginDTO(LoginDTO loginDTO) {
        this.username = loginDTO.getUsername();
        this.password = loginDTO.getPassword();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
