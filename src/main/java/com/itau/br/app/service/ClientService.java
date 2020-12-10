package com.itau.br.app.service;

import com.itau.br.app.model.Client;

import java.util.List;

public interface ClientService {

    Client registerCustomer(Client request);

    List<Client> getAllClients();

    Client getClientByAccountNumber(String account);

}
