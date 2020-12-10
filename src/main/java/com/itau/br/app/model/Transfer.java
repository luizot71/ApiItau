package com.itau.br.app.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "transfer")
@EntityListeners(AuditingEntityListener.class)
@SequenceGenerator(name = "SEQ_TRANSFER", sequenceName = "SEQ_TRANSFER", allocationSize = 1)
public class Transfer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SEQ_TRANSFER")
    @Column(name = "id", insertable = false, nullable = false, updatable = false, unique = true)
    private int id;

    @Column(name = "originAccount", nullable = false, length = 9)
    private String originAccount;

    @Column(name = "destinationAccount", nullable = false, length = 9)
    private String destinationAccount;

    @Column(name = "value", nullable = false)
    private float value;

    @Column(name = "successfulTransfer")
    private boolean successfulTransfer;

    @Column(name = "transfer_date")
    private Date transferDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginAccount() {
        return originAccount;
    }

    public void setOriginAccount(String originAccount) {
        this.originAccount = originAccount;
    }

    public String getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(String destinationAccount) {
        this.destinationAccount = destinationAccount;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public boolean isSuccessfulTransfer() {
        return successfulTransfer;
    }

    public void setSuccessfulTransfer(boolean successfulTransfer) {
        this.successfulTransfer = successfulTransfer;
    }

    public Date getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(Date transferDate) {
        this.transferDate = transferDate;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((destinationAccount == null) ? 0 : destinationAccount.hashCode());
        result = prime * result + ((originAccount == null) ? 0 : originAccount.hashCode());
        result = prime * result + ((transferDate == null) ? 0 : transferDate.hashCode());
        result = prime * result + id;
        result = prime * result + (successfulTransfer ? 1231 : 1237);
        result = prime * result + Float.floatToIntBits(value);
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
        Transfer other = (Transfer) obj;
        if (destinationAccount == null) {
            if (other.destinationAccount != null)
                return false;
        } else if (!destinationAccount.equals(other.destinationAccount))
            return false;
        if (originAccount == null) {
            if (other.originAccount != null)
                return false;
        } else if (!originAccount.equals(other.originAccount))
            return false;
        if (transferDate == null) {
            if (other.transferDate != null)
                return false;
        } else if (!transferDate.equals(other.transferDate))
            return false;
        if (id != other.id)
            return false;
        if (successfulTransfer != other.successfulTransfer)
            return false;
        if (Float.floatToIntBits(value) != Float.floatToIntBits(other.value))
            return false;
        return true;
    }
}
