package com.wallet.personalwalletapp.repo;

import com.wallet.personalwalletapp.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IWalletRepo extends JpaRepository<Wallet,Long> {
    public List<Wallet> findAllByOrderByPriority();
}
