package com.itau.br.app.util;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Messages {

    @Getter
    @Value("${msg.transfer.confirmed}")
    private String transferConfirmed;

    @Getter
    @Value("${msg.equal.account}")
    private String equalAccount;

    @Getter
    @Value("${msg.max.value.transfer}")
    private String maximumAllowedValue;

    @Getter
    @Value("${msg.invalid.value}")
    private String invalidValue;

    @Getter
    @Value("${msg.insufficient.funds}")
    private String insufficientFunds;

    @Getter
    @Value("${msg.not.found}")
    private String notFoundAccount;

    @Getter
    @Value("${msg.internal.server.error}")
    private String internalServerError;

    @Getter
    @Value("${msg.updated.resources}")
    private String updatedResources;

    @Getter
    @Value("${msg.not.found.register}")
    private String notFoundRegister;

}
