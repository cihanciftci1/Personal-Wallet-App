package com.wallet.personalwalletapp.service.wallet;

import com.wallet.personalwalletapp.model.Wallet;

import java.util.List;
import java.util.Optional;

public interface IWalletService {
    public Wallet createOrUpdate(Wallet wallet);
    public void delete(Long id);
    public Optional<Wallet> findById(Long id);
    public List<Wallet> getAll();
}
