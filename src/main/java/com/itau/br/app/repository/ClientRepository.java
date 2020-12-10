package com.itau.br.app.repository;

import com.itau.br.app.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

     @Query(value = "SELECT * FROM client WHERE account = :account", nativeQuery = true)
     Optional<Client> findCustomerByAccount(@Param("account") String account);

     @Query(value = "UPDATE client SET balance = balance + :value WHERE account = :account", nativeQuery = true)
     @Modifying
     void updateAddBalance(@Param("account") String account, @Param("value") float value);

     @Query(value = "UPDATE client SET balance = balance - :value WHERE account = :account", nativeQuery = true)
     @Modifying
     void updateSubtractBalance(@Param("account") String account, @Param("value") float value);
}
