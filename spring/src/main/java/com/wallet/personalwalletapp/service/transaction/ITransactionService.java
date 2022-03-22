package com.wallet.personalwalletapp.service.transaction;

import com.wallet.personalwalletapp.model.Transaction;

import java.util.List;
import java.util.Optional;

public interface ITransactionService {
    public Transaction create(Long walletId,Transaction transaction);
    public List<Transaction> getAll(Long walletId);
    public void delete(Long id);
    public Optional<Transaction> findById(Long id);
}
