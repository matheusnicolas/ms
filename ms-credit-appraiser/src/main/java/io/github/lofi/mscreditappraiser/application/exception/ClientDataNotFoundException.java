package io.github.lofi.mscreditappraiser.application.exception;

public class ClientDataNotFoundException extends Exception {

    public ClientDataNotFoundException() {
       super("Dados do cliente relacionado ao CPF informado não encontrado!");
    }
}
