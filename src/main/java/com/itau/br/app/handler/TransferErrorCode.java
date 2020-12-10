package com.itau.br.app.handler;

import com.itau.br.app.handler.exceptions.ErrorCode;
import com.itau.br.app.handler.exceptions.HttpCode;

public enum TransferErrorCode implements ErrorCode {

    ERROR_ACCOUNT_NOT_FOUND(1001, HttpCode.NOT_FOUND, "Nenhum cliente encontrado para Conta informada!"),
    ERROR_VALUE_ABOVE_LIMIT(1002, HttpCode.BAD_REQUEST, "Valor da transferência acima do permitido!"),
    ERROR_INSUFFICIENT_FUNDS(1003, HttpCode.BAD_REQUEST, "Saldo Insuficiente para realizar a transferência!"),
    ERROR_ACCOUNT_NOT_INFORMED(1004, HttpCode.NOT_FOUND, "Número da conta não foi informado!"),
    ERROR_DIGITS_ABOVE_ALLOWED(1005, HttpCode.BAD_REQUEST, "Ops! A conta contêm mais de 9 digitos, favor verificar!"),
    ERROR_ACCOUNT_ALREADY_REGISTERED(1006, HttpCode.INTERNAL_SERVER_ERROR, "Conta informa, já está cadastrada!"),
    ERROR_INTERNAL_SERVER_ERROR(1007, HttpCode.INTERNAL_SERVER_ERROR, "Ops! Internal Server Error");

    private final Properties properties;

    TransferErrorCode(int errorCode, HttpCode httpCode, String message) {
        this.properties = Properties.of(errorCode, httpCode, this.name(), message);
    }

    public static TransferErrorCode getByString(String code) {
        code = code.replace("_", "");
        switch (code) {
            case "1001":
                return ERROR_ACCOUNT_NOT_FOUND;
            case "1002":
                return ERROR_VALUE_ABOVE_LIMIT;
            case "1003":
                return ERROR_INSUFFICIENT_FUNDS;
            case "1004":
                return ERROR_ACCOUNT_NOT_INFORMED;
            case "1005":
                return ERROR_DIGITS_ABOVE_ALLOWED;
            case "1006":
                return ERROR_ACCOUNT_ALREADY_REGISTERED;
            default:
                return ERROR_INTERNAL_SERVER_ERROR;
        }
    }

    @Override
    public Properties getProperties() {
        return properties;
    }
}
