package com.example.appbancosenai.controller;

import com.example.appbancosenai.model.Account;
import com.example.appbancosenai.model.CurrentAccountPF;

public interface CurrentAccount {

    String sacar(Double quantidade, Long account);

    String depositar(Double quantidade, Long account);

    String transferir(Long contaOrigem, Long contaDestino, Double valor);

    String consultaSaldo(Long account);
}
