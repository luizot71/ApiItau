package com.itau.br.app.service.impl;

import com.itau.br.app.handler.TransferErrorCode;
import com.itau.br.app.handler.exceptions.BusinessException;
import com.itau.br.app.model.Client;
import com.itau.br.app.repository.ClientRepository;
import com.itau.br.app.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    /**
     * Endpoint to register customers
     *
     * @param request
     * @return Client
     */
    @Override
    public Client registerCustomer(Client request) {

        if (request.getAccount().length() > 9) {
            throw new BusinessException(TransferErrorCode.ERROR_DIGITS_ABOVE_ALLOWED);
        }

        if (request.getAccount() == null || "".equals(request.getAccount())) {
            throw new BusinessException(TransferErrorCode.ERROR_ACCOUNT_NOT_INFORMED);
        }

        Optional<Client> entity = clientRepository.findCustomerByAccount(request.getAccount());

        if (entity.isPresent()) {
            throw new BusinessException(TransferErrorCode.ERROR_ACCOUNT_ALREADY_REGISTERED);
        }

        return clientRepository.save(request);
    }

    /**
     * Endpoint to consult all customers
     *
     * @return clientRepository
     */
    @Override
    public List<Client> getAllClients() {
        Sort sort = Sort.by("name");
        return clientRepository.findAll(sort);
    }

    /**
     * Endpoint to search for transfers related to an account, in order
     * decreasing date. Remember that unsuccessful transfers also
     * must be stored.
     *
     * @param account
     * @return client
     */
    @Override
    public Client getClientByAccountNumber(String account) {

        Client client = clientRepository.findCustomerByAccount(account)
                .orElseThrow(() -> new BusinessException(TransferErrorCode.ERROR_ACCOUNT_NOT_FOUND));

        client.setId(client.getId());
        client.setName(client.getName());
        client.setAccount(client.getAccount());
        client.setBalance(client.getBalance());

        return client;
    }

    /**
     * Adds the transfer amount to the account balance
     *
     * @param account
     * @param value
     */
    public void addBalance(String account, float value) {

        clientRepository.updateAddBalance(account, value);
    }

    /**
     * Discounts the transfer amount on the account balance
     *
     * @param account
     * @param value
     */
    public void subtractBalance(String account, float value) {

        clientRepository.updateSubtractBalance(account, value);
    }
}
