package com.wallet.personalwalletapp.controller;

import com.wallet.personalwalletapp.model.Transaction;
import com.wallet.personalwalletapp.service.transaction.ITransactionService;
import com.wallet.personalwalletapp.service.validation.IValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/transaction")
@CrossOrigin
public class TransactionController {
    @Autowired
    private ITransactionService transactionService;
    @Autowired
    private IValidationService validationService;

    @GetMapping("/{wallet_id}")
    public ResponseEntity<?> getAll(@PathVariable Long wallet_id){
        return new ResponseEntity<List<Transaction>>(transactionService.getAll(wallet_id), HttpStatus.OK);
    }
    @GetMapping("/{wallet_id}/{transaction_id}")
    public ResponseEntity<?> getById(@PathVariable Long transaction_id){
        if(transactionService.findById(transaction_id).isEmpty()){
            return new ResponseEntity<String>("Transaction not found!",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Transaction>(transactionService.findById(transaction_id).get(), HttpStatus.OK);
    }
    @PostMapping("/{wallet_id}")
    public ResponseEntity<?> createTransaction(@PathVariable Long wallet_id, @Valid @RequestBody Transaction transaction, BindingResult result){
        ResponseEntity error=validationService.validate(result);
        if(error!=null) return error;

        if(transaction.getTransactionDate()==null){
            transaction.setTransactionDate(new Date());
        }
        Transaction savedTransaction=transactionService.create(wallet_id,transaction);
        return new ResponseEntity<Transaction>(savedTransaction,HttpStatus.OK);
    }

    @DeleteMapping("/{wallet_id}/{transaction_Id}")
    public ResponseEntity<?> deleteTransaction(@PathVariable Long transaction_Id){
        if(transactionService.findById(transaction_Id).isEmpty()){
            return new ResponseEntity<String>("Transaction not found",HttpStatus.BAD_REQUEST);
        }
        transactionService.delete(transaction_Id);
        return new ResponseEntity<String>("Transaction is deleted",HttpStatus.OK);
    }
    @DeleteMapping("/revoke/{wallet_id}/{transaction_Id}")
    public ResponseEntity<?> deleteTransactionRevoke(@PathVariable Long transaction_Id){
        if(transactionService.findById(transaction_Id).isEmpty()){
            return new ResponseEntity<String>("Transaction not found",HttpStatus.BAD_REQUEST);
        }
        Transaction trToRevoke=transactionService.findById(transaction_Id).get();
        if(trToRevoke.getType()==1){
            trToRevoke.getWallet().withdraw(trToRevoke.getAmount());
        }else if(trToRevoke.getType()==2){
            trToRevoke.getWallet().deposit(trToRevoke.getAmount());
        }

        transactionService.delete(transaction_Id);
        return new ResponseEntity<String>("Transaction is deleted",HttpStatus.OK);
    }
}
