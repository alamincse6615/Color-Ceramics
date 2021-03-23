package com.colorceramics.soft_it_care.Presentation.ui.activities;

import com.colorceramics.soft_it_care.Models.Wallet;

import java.util.List;

public interface WalletView {
    void setWalletBalance(Double balance);
    void setWalletHistory(List<Wallet> walletList);
}
