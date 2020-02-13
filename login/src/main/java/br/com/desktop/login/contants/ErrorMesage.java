package br.com.desktop.login.contants;

public enum ErrorMesage {

    // User Errors
    USER_PAS("A senha não pode ser vazia ou ter menos que 5 caracteres!"),
    USER_LOG("O usuário não pode ser vazio ou ter menos que 5 caracteres!"),
    USER_EMAIL_EMPTY("O email não pode ser vazio!"),
    USER_EMAIL_NOT_VALID("Email digitado inválido!"),
    USER_BIRTHDAY("Data de nascimento inválida!"),
    USER_NOT_FOUND("Usuário não encontrado!"),

    // Role Errors
    ROLE_NOT_FOUND("Role não encontrada"),
    ROLE_NAME("O nome da role não pode ser vazio ou nullo!"),


    // Role Errors
    AUTHORITY_NOT_FOUND("Authority não encontrada"),
    AUTHORITY_NAME("O nome da authority não pode ser vazio ou nulo!");


    ErrorMesage(String msg){
        this.message = msg;
    }
    private String message;
    
    public String get(){
        return message;
    }
    
}
