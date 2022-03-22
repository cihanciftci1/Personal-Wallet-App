package com.wallet.personalwalletapp.service.wallet;

import com.wallet.personalwalletapp.model.Wallet;
import com.wallet.personalwalletapp.repo.IWalletRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WalletService implements IWalletService {
    @Autowired
    private IWalletRepo walletRepo;

    @Override
    public Wallet createOrUpdate(Wallet wallet) {
        if(wallet.getPriority()==null){
            wallet.setPriority(3);
        }
        if(wallet.getCurrentBalance()==null){
            wallet.setCurrentBalance(0.0);
        }
        walletRepo.save(wallet);
        return wallet;
    }


    @Override
    public void delete(Long id) {
        walletRepo.deleteById(id);
    }

    @Override
    public Optional<Wallet> findById(Long id) {
        return walletRepo.findById(id);
    }

    @Override
    public List<Wallet> getAll() {
        return walletRepo.findAllByOrderByPriority();
    }
}