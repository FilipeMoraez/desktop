package br.com.desktop.login.exception;

import br.com.desktop.login.contants.ErrorMesage;

public class BussinessException extends Exception {
    public BussinessException(ErrorMesage errorMesage) {
        super(errorMesage.get());
    }
}
