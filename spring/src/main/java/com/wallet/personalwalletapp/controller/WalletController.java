package com.wallet.personalwalletapp.controller;

import com.wallet.personalwalletapp.model.Wallet;
import com.wallet.personalwalletapp.service.validation.IValidationService;
import com.wallet.personalwalletapp.service.wallet.IWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/wallet")
@CrossOrigin
public class WalletController {
    @Autowired
    private IWalletService walletService;
    @Autowired
    private IValidationService validationService;

    @PostMapping
    public ResponseEntity<?> createWallet(@Valid @RequestBody Wallet wallet, BindingResult result){
        ResponseEntity errors = validationService.validate(result);
        if(errors!=null) return errors;
        Wallet savedWallet=walletService.createOrUpdate(wallet);
        return new ResponseEntity<Wallet>(savedWallet,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateWallet(@PathVariable Long id, @Valid @RequestBody Wallet wallet, BindingResult result){
        ResponseEntity errors = validationService.validate(result);
        if(errors!=null) return errors;

        wallet.setId(id);
        Wallet savedWallet=walletService.createOrUpdate(wallet);
        return new ResponseEntity<Wallet>(savedWallet,HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<List<Wallet>>(walletService.getAll(),HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        if(walletService.findById(id).isPresent()){
            return new ResponseEntity<Wallet>(walletService.findById(id).get(),HttpStatus.OK);
        }
        return new ResponseEntity<String>("Wallet not found!",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        if(walletService.findById(id).isPresent()){
            walletService.delete(id);
            return new ResponseEntity<String>("Wallet is deleted!",HttpStatus.OK);
        }
        return new ResponseEntity<String>("Wallet not found!",HttpStatus.BAD_REQUEST);
    }
}
