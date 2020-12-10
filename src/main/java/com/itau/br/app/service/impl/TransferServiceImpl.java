package com.itau.br.app.service.impl;

import com.itau.br.app.handler.TransferErrorCode;
import com.itau.br.app.handler.exceptions.BusinessException;
import com.itau.br.app.handler.exceptions.ClientNotFoundException;
import com.itau.br.app.handler.exceptions.InsufficientFundsException;
import com.itau.br.app.handler.exceptions.ValueAboveLimitException;
import com.itau.br.app.model.Client;
import com.itau.br.app.model.Transfer;
import com.itau.br.app.repository.TransferRepository;
import com.itau.br.app.service.TransferService;
import com.itau.br.app.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class TransferServiceImpl implements TransferService {

    @Autowired
    private TransferRepository transferRepository;

    @Autowired
    private ClientServiceImpl service;

    @Autowired
    private Messages messages;

    /**
     * Endpoint to transfer between 2 accounts. The source account must have
     * enough balance to carry out the transfer and the transfer must be
     * maximum of R $ 1000.00 reais;
     *
     * @param transfer
     * @return
     */
    @Override
    @Transactional(dontRollbackOn = {InsufficientFundsException.class, ValueAboveLimitException.class})
    public Transfer generatesTransfer(Transfer transfer) {

        Client sourceAccount = service.getClientByAccountNumber(transfer.getOriginAccount());

        /**
         * Value greater than 1000, throws the exception returning error
         */
        if (transfer.getValue() > 1000) {
            transfer.setSuccessfulTransfer(false);
            transferRepository.save(transfer);
            throw new BusinessException(TransferErrorCode.ERROR_VALUE_ABOVE_LIMIT);
        }

        /**
         * Insufficient balance to carry out the transfer, throws the exception returning error
         */
        if (sourceAccount.getBalance() < transfer.getValue()) {
            transfer.setSuccessfulTransfer(false);
            transferRepository.save(transfer);
            throw new BusinessException(TransferErrorCode.ERROR_INSUFFICIENT_FUNDS + "" + transfer.getValue());
        }

        Client targetAccount = service.getClientByAccountNumber(transfer.getDestinationAccount());

        /**Discounts the transfer amount on the account balance*/
        service.subtractBalance(sourceAccount.getAccount(), transfer.getValue());

        /**Adds the transfer amount to the account balance*/
        service.addBalance(targetAccount.getAccount(), transfer.getValue());

        transfer.setSuccessfulTransfer(true);
        transfer.setTransferDate(new Date());

        return transferRepository.save(transfer);
    }

    /**
     * Endpoint to search for transfers related to an account, in order
     * decreasing date. Remember that unsuccessful transfers also
     * must be stored.
     *
     * @param account
     * @return
     */
    @Override
    public List<Transfer> searchTransfers(String account) {

        List<Transfer> transfers = transferRepository.findTransfersByAccountCustomer(account);

        if (account.isEmpty()) {
            throw new ClientNotFoundException(messages.getNotFoundRegister());
        }

        return transfers;
    }
}
