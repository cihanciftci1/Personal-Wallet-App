package com.wallet.personalwalletapp.repo;

import com.wallet.personalwalletapp.model.Transaction;
import com.wallet.personalwalletapp.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITransactionRepo extends JpaRepository<Transaction,Long> {
    public List<Transaction> findByWallet(Wallet wallet);
}
