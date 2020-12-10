package com.itau.br.app.controller;

import com.itau.br.app.model.Client;
import com.itau.br.app.service.impl.ClientServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/itau", produces = "application/json")
@Api(value = "Api to perform bank transactions")
public class ClientController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientServiceImpl transferService;

    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Endpoint to register a customer, with the following information: " +
            "id (unique), name, account number (unique) and account balance.")
    @Cacheable(value = "created")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Customer successfully registered"),
            @ApiResponse(code = 400, message = "Bad Request: Oops!")
    })
    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> registerCustomer(@RequestBody @Valid Client client) {

        LOGGER.info("Successfully customer record created %s", client);

        return ResponseEntity.ok().body(transferService.registerCustomer(client));
    }

    @GetMapping
    @ApiOperation(value = "Endpoint to retrieve all clients")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @RequestMapping(value = "/get-clients", method = RequestMethod.GET, produces = "application/json")
    @Cacheable(value = "clients")
    public List<Client> getAllClients() {

        LOGGER.info("Searching all clients");

        return transferService.getAllClients();
    }

    @ApiOperation(value = "Endpoint to search a customer by account number")
    @RequestMapping(value = "/consult-customer/{account}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Cacheable(value = "account")
    public ResponseEntity<Client> consultCustomer(@PathVariable("account") String account) {

        LOGGER.info("Searching a customer by account number: %s", account);

        return ResponseEntity.ok().body(transferService.getClientByAccountNumber(account));
    }
}
