package com.antsmc2.wallet;

import com.antsmc2.common.CryptoWallet;
import com.github.ki5fpl.tronj.key.KeyPair;
import java.util.List;

public class TronWallet implements CryptoWallet {
    private Integer coinType;
    private final List<String> mnemonicPhrase;

    @Override
    public Integer getCoinType() {
        return coinType;
    }

    @Override
    public List<String> getMnemonicPhrase() {
        return mnemonicPhrase;
    }

    public TronWallet(List<String> mnemonicPhrase) {
        this.mnemonicPhrase = mnemonicPhrase;
        coinType = 195;  // Tron coin type
    }

    @Override
    public String getNormalizedPrivateKey(Integer addressId) {
        return getDerivedPrivateKey(addressId).toString(16);
    }

    @Override
    public String getNormalizedPublicAddress(Integer addressId) {
        String privateKeyHex = getDerivedPrivateKey(addressId).toString(16);
        return KeyPair.of(privateKeyHex).toBase58CheckAddress();
    }

    public String getNormalizedPublicHexAddress(Integer addressId) {
        String privateKeyHex = getDerivedPrivateKey(addressId).toString(16);
        return KeyPair.of(privateKeyHex).toHexAddress();
    }
}
