package com.example.appbancosenai.controller;

import com.example.appbancosenai.model.Account;
import com.example.appbancosenai.model.AccountType;
import com.example.appbancosenai.model.CurrentAccountPF;
import com.example.appbancosenai.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.websocket.server.PathParam;
import java.text.Format;
import java.util.Date;
import java.util.List;

@Service
public class BankController implements CurrentAccount{

    @Autowired
    private BankRepository bankRepository;
    @Autowired
    private Controller controller;
    private Long number = 0L;
    public void delete(String name){                                //-----------------
        bankRepository.delete(this.consultaConta(name));
    }

    public CurrentAccountPF criarConta(String   name, String accountType) throws Exception{
        CurrentAccountPF currentAccountPF = new CurrentAccountPF();
        StringBuilder message = new StringBuilder();
        if(accountType == null) {
            message.append("\nÉ necessário informar o tipo da conta!");
        }
        switch (accountType){
            case "SAVING" :
                currentAccountPF.setAccountType(AccountType.SAVING_ACCOUNT);
                break;
            case "CURRENT" :
                currentAccountPF.setAccountType(AccountType.CURRENT_ACCOUNT);
            default :
                message.append("\nO tipo da conta não é suportado!");
        }

        Person person = controller.findPerson(name);
        if (person != null && currentAccountPF.getError() == null   ){
            number++;
            currentAccountPF.setAccountNumber(number);
            currentAccountPF.setPerson(person);
            bankRepository.save(currentAccountPF);
        } else if (currentAccountPF.getError() == null){
            message.append("\nPessoa ");
            message.append(name).append(" não foi cadastrada!");
        }
        if(!message.isEmpty()){
            currentAccountPF.setError(message.toString());
        }
        return currentAccountPF;
    }

    public CurrentAccountPF consultaConta(String name){

        List<CurrentAccountPF> accounts = (List<CurrentAccountPF>) bankRepository.findAll();

        for (CurrentAccountPF ca : accounts) {
            if (ca.getPerson() != null && ca.getPerson().getName().equals(name)){
                if (ca.getDate().equals(new Date())){
                    return ca;
                } else {
                    ca.setDate(new Date());
                    ca.setSaldo(ca.getSaldo() * 1.001);
                    bankRepository.save(ca);
                }
            }
        }
        return null;
    }

    @Override
    public String depositar(Double quantidade, Long account) {
        String message = "";
        CurrentAccountPF conta = bankRepository.findById(account).get();
        conta.setSaldo(conta.getSaldo() + quantidade);
        bankRepository.save(conta);
        return message = "O depósito de " + quantidade + "foi realizado com sucesso!";
        //Double total = account.getSaldo() + quantidade;
        //account.setSaldo(total);
    }

    @Override
    public String transferir(Long contaOrigem, Long contaDestino, Double quantidade) {
        String message = "";
        Double taxa = 10.0D;
        CurrentAccountPF destino = bankRepository.findById(contaDestino).get();
        CurrentAccountPF origem = bankRepository.findById(contaDestino).get();

        if (origem.getSaldo() >= quantidade){
            destino.setSaldo(destino.getSaldo() + quantidade);
            origem.setSaldo(origem.getSaldo() - quantidade);

            if (origem.getAccountType() != destino.getAccountType()){
                origem.setSaldo(origem.getSaldo() - taxa);
            }

            bankRepository.save(destino);
            bankRepository.save(origem);
            message = message + "A conta do(a)" + destino.getPerson().getName() + "recebeu a transferência no valor de R$" + quantidade;

        } else {
            message = message + "Saldo insuficiente para a operação desejada!";
        }

        return message;
    }

    @Override
    public String sacar(Double quantidade, Long account) {
        String message = "";
        CurrentAccountPF saque = bankRepository.findById(account).get();
        if (saque.getSaldo() >= quantidade){
            saque.setSaldo(saque.getSaldo() - quantidade);
            bankRepository.save(saque);
            message = message + "O saque de R$" + quantidade + "foi realizado com sucesso!";
        } else {
            message = message + "Saldo insuficiente para a operação desejada!";
        }

        return message;
    }

    @Override
    public String consultaSaldo(Long account) {
        String message = "";
        CurrentAccountPF extrato = bankRepository.findById(account).get();
        return message + "O saldo da conta "+ extrato.getAccountNumber() + " de "+ extrato.getPerson().getName() + " é de R$" + extrato.getSaldo() + ".";
    }
}
