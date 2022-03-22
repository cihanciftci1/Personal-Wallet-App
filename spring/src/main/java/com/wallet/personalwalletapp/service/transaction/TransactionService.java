package com.wallet.personalwalletapp.service.transaction;

import com.wallet.personalwalletapp.model.Transaction;
import com.wallet.personalwalletapp.model.Wallet;
import com.wallet.personalwalletapp.repo.ITransactionRepo;
import com.wallet.personalwalletapp.repo.IWalletRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService implements ITransactionService {
    @Autowired
    private ITransactionRepo transactionRepo;
    @Autowired
    private IWalletRepo walletRepo;

    @Override
    public Transaction create(Long walletId,Transaction transaction) {
        Optional<Wallet> wallet=walletRepo.findById(walletId);
        if(wallet.isPresent()){
            transaction.setWallet(wallet.get());

            if(transaction.getType()==1){
                transaction.getWallet().deposit(transaction.getAmount());
            }else if(transaction.getType()==2){
                transaction.getWallet().withdraw(transaction.getAmount());
            }

            transactionRepo.save(transaction);
            return transaction;
        }
        return null;
    }

    @Override
    public List<Transaction> getAll(Long walletId) {
        Optional<Wallet> wallet=walletRepo.findById(walletId);
        if(wallet.isPresent()) return transactionRepo.findByWallet(wallet.get());

        return null;

    }

    @Override
    public void delete(Long id) {
        transactionRepo.deleteById(id);
    }

    @Override
    public Optional<Transaction> findById(Long id) {
        return transactionRepo.findById(id);
    }


}
