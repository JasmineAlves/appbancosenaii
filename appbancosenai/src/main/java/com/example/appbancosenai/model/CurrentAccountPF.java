package com.example.appbancosenai.model;

import com.example.appbancosenai.controller.CurrentAccount;

import javax.persistence.*;
import javax.websocket.ClientEndpoint;
import java.util.Date;

@Entity
@Table(name = "account")
public class CurrentAccountPF{

    @Id
    @Column(name = "account_number")
    private Long accountNumber;
    private Double saldo;

    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @Column(name = "type")
    private AccountType accountType;

    @Transient
    String error;

    @Transient
    private Date date;


    //NÚMERO DA CONTA
    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    //PESSOA
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person pessoa) {
        this.person = person;
    }

    //SALDO
    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    //ACCOUNT TYPE
    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    //ERRO
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
