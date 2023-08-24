package com.example.appbancosenai.controller;

import com.example.appbancosenai.model.Account;
import com.example.appbancosenai.model.CurrentAccountPF;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BankRepository extends CrudRepository<CurrentAccountPF, Long > {

}
