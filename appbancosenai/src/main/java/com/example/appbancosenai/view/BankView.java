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


    @PostMapping("/criarconta") //CERTO
    public CurrentAccountPF criarConta(@PathParam("name") String name, @PathParam("type") String type) throws Exception {
        return bankController.criarConta(name, type);
    }

    @GetMapping("/type") //CERTO
    public String listAccountType() {
        String text = AccountType.CURRENT_ACCOUNT + ", " + AccountType.SAVING_ACCOUNT;
        return text;
    }

    @GetMapping("/consultaconta") //CERTO
    public CurrentAccountPF consultaConta(@PathParam("contaDestino") Long contaDestino) {
        return bankController.consultaConta(contaDestino);
    }

    @PutMapping("/transferir") //CERTO
    public String transferir(@PathParam("contaOrigem") Long contaOrigem, @PathParam("contaDestino") Long contaDestino, @PathParam("valor") Double valor) {
        return bankController.transferir(contaOrigem, contaDestino, valor);
    }

    @PutMapping("/depositar") //CERTO ---- valor seria (DEPOSITO)
    public String depositar(@PathParam("contaDestino") Long contaDestino, @PathParam("valor") Double valor) {
        return bankController.depositar(valor, contaDestino);
    }
}

    //@PutMapping("/sacar") //CERTO ----- valor seria referente a SACAR
    //public Double sacar(@PathParam("contaDestino") Long contaD
//
// estino, @PathParam("valor") Double valor);
        //return bankController.sacar(valor, contaDestino);

    //@GetMapping("/extrato")
    //public String consultarSaldo(@PathParam("conta") Long account){
       // **return bankController.consultaSaldo(account);
    //}

    //@DeleteMapping("/banco")                                                        //-------
    //public void delete(@PathParam("name") String name){
        //bankController.delete(name);
   // }

