package io.github.lofi.mscreditappraiser.application.exception;

import lombok.Getter;

public class CommunicationErrorMicroservicesException extends Exception {

    @Getter
    private Integer status;

    public CommunicationErrorMicroservicesException(String message, Integer status) {
        super(message);
        this.status = status;
    }
}
