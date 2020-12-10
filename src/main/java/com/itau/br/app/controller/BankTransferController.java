package com.itau.br.app.controller;

import com.itau.br.app.model.Transfer;
import com.itau.br.app.service.impl.TransferServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/itau/transfer", produces = "application/json")
@Api(value = "Api to perform bank transactions")
public class BankTransferController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private TransferServiceImpl transferService;

    @RequestMapping(value = "/between-accounts", method = RequestMethod.POST)
    @ApiOperation(value = "Endpoint for transferring between 2 accounts.")
    @Cacheable(value = "transfer")
    public ResponseEntity<Transfer> transferAmountsBetweenAccounts(@RequestBody Transfer transfer) {

        LOGGER.info("Transferring between 2 accounts: %s", transfer);

        return ResponseEntity.ok().body(transferService.generatesTransfer(transfer));
    }

    @ApiOperation(value = "Fetch transfers related to an account, in order decreasing date..")
    @RequestMapping(value = "/consult-transfer/{account}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Transfer>> consultTransfers(@PathVariable("account") String account) {

        LOGGER.info("Transfers related to an account, in order decreasing date: %s", account);

        return ResponseEntity.ok().body(transferService.searchTransfers(account));
    }
}
