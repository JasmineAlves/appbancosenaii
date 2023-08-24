package com.example.appbancosenai.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Person {

    @Id
    private Integer id;
    private String name;
    private String sexo;
    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private CurrentAccountPF currentAccountPF;


    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CurrentAccountPF getCurrentAccountPF() {
        return currentAccountPF;
    }

    public void setCurrentAccountPF(CurrentAccountPF currentAccountPF) {
        this.currentAccountPF = currentAccountPF;
    }
}