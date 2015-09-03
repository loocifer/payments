package org.mst.payment.business.checkout.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
    @NamedQuery(name = Txn.FIND_BY_CARD, query = "SELECT t FROM Txn t WHERE t.creditCard = :cardNo"),
    @NamedQuery(name = Txn.FIND_ALL, query = "SELECT t FROM Txn t")})
public class Txn implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final String PREFIX = "org.mst.payment.checkout.entity.Txn.";
    public static final String FIND_BY_CARD = PREFIX + "findByCard";
    public static final String FIND_ALL = PREFIX + "findAll";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Min(1)
    private Long amount;

    @NotNull
    @Pattern(regexp = "\\d{4,16}")
    private String creditCard;

    private String authId;

    @NotNull
    @Pattern(regexp = "(0[1-9]|1[0-2])\\/?([0-9]{4}|[0-9]{2})")
    private String expiry;

    @NotNull
    @Pattern(regexp = "(EUR)|(USD)|(GBP)")
    private String currency;

    public Txn() {
    }

    public Txn(Long amount, String creditCard, String expiry, String currency) {
        this.amount = amount;
        this.creditCard = creditCard;
        this.expiry = expiry;
        this.currency = currency;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public Long getAmount() {
        return amount;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public String getExpiry() {
        return expiry;
    }

    public String getCurrency() {
        return currency;
    }

    public void setAuthId(String authId) {
        this.authId = authId;
    }

    public String getAuthId() {
        return authId;
    }

}
