package com.example.appbancosenai.view;

import com.example.appbancosenai.controller.BankController;
import com.example.appbancosenai.model.AccountType;
import com.example.appbancosenai.model.CurrentAccountPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
public class BankView {

    @Autowired
    private BankController bankController;


    @PostMapping("/criarconta")
    public CurrentAccountPF criarConta(@PathParam("name") String name, @PathParam("type") String type) throws Exception{
        return bankController.criarConta(name, type);
    }

    @GetMapping("/type")
    public String listAccountType(){
        String text = AccountType.CURRENT_ACCOUNT + ", " + AccountType.SAVING_ACCOUNT;
        return text;
    }

    @GetMapping("/consultaconta")
    public CurrentAccountPF consultaConta(@PathParam("name") String name){
        return bankController.consultaConta(name);
    }

    @PutMapping("/transferir")
    public String transferir(@PathParam("contaOrigem") Long contaOrigem, @PathParam("contaDestino") Long contaDestino, @PathParam("valor") Double valor){
        return bankController.transferir(contaOrigem, contaDestino, valor);
    }
    @PutMapping("/depositar")
    public String depositar(@PathParam("conta") Long account, @PathParam("valor") Double valor){
        return bankController.depositar(valor, account);
   }

    @PutMapping("/sacar")
    public String sacar(@PathParam("conta") Long account, @PathParam("valor") Double valor){
        return bankController.sacar(valor, account);
    }

    @GetMapping("/extrato")
    public String consultarSaldo(@PathParam("conta") Long account){
        return bankController.consultaSaldo(account);
    }

    @DeleteMapping("/banco")                                                        //-------
    public void delete(@PathParam("name") String name){
        bankController.delete(name);
    }
}
