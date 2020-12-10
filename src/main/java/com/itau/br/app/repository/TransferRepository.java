package com.itau.br.app.repository;

import com.itau.br.app.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, String> {

    @Query(value = "SELECT * FROM transfer WHERE origin_account = :account OR destination_account = :account ORDER BY transfer_date desc", nativeQuery = true)
    List<Transfer> findTransfersByAccountCustomer(@Param("account") String account);
}
