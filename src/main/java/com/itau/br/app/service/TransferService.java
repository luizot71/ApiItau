package com.itau.br.app.service;

import com.itau.br.app.model.Transfer;

import java.util.List;

public interface TransferService {

    Transfer generatesTransfer(Transfer transfer);

    List<Transfer> searchTransfers(String account);
}
