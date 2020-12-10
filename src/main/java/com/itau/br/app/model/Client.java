package com.itau.br.app.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import javax.persistence.GeneratedValue;
import java.io.Serializable;

@Entity
@Table(name = "client")
@EntityListeners(AuditingEntityListener.class)
@SequenceGenerator(name = "SEQ_CLIENT", sequenceName = "SEQ_CLIENT", allocationSize = 1)
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CLIENT")
    private int id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "account", nullable = false, updatable = false, unique = true, length = 9)
    private String account;

    @Column(name = "balance", nullable = false)
    private float balance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((account == null) ? 0 : account.hashCode());
        result = prime * result + Float.floatToIntBits(balance);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Client other = (Client) obj;
        if (id != other.id)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (account == null) {
            if (other.account != null)
                return false;
        } else if (!account.equals(other.account))
            return false;
        if (Float.floatToIntBits(balance) != Float.floatToIntBits(other.balance))
            return false;
        return true;
    }
}
