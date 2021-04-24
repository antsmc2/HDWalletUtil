package com.antsmc2.wallet;

import com.antsmc2.common.CryptoWallet;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.util.List;

public class EthereumWallet implements CryptoWallet {
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

    public EthereumWallet(List<String> mnemonicPhrase) {
        this.mnemonicPhrase = mnemonicPhrase;
        coinType = 60;  // ETH coin type
    }

    private ECKeyPair getECKeyPair(Integer addressId) {
        BigInteger privateKey = getDerivedPrivateKey(addressId);
        return ECKeyPair.create(privateKey);
    }

    @Override
    public String getNormalizedPrivateKey(Integer addressId) {
        ECKeyPair ecKeyPair = getECKeyPair(addressId);
        return Numeric.toHexStringWithPrefix(ecKeyPair.getPrivateKey());
    }

    @Override
    public String getNormalizedPublicAddress(Integer addressId) {
        ECKeyPair ecKeyPair = getECKeyPair(addressId);
        return Keys.toChecksumAddress(Keys.getAddress(ecKeyPair));
    }
}
