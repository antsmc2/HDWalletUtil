package com.antsmc2.wallet;

import com.antsmc2.common.CryptoWallet;
import org.bitcoinj.core.Address;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.params.TestNet3Params;
import org.bitcoinj.script.Script;
import java.math.BigInteger;
import java.util.List;

public class BitcoinWallet implements CryptoWallet {
    private Integer coinType;
    private final List<String> mnemonicPhrase;
    private NetworkParameters networkParameters;
    public enum NetworkType {
        MAIN_NET, TEST_NET
    }

    public BitcoinWallet(NetworkType networkType, List<String> mnemonicPhrase) {
        switch (networkType) {
            case MAIN_NET:
                coinType = 0;  // main net coin type
                networkParameters = MainNetParams.get();
                break;
            case TEST_NET:
                coinType = 1;  // test net coin type
                networkParameters = TestNet3Params.get();
        }
        this.mnemonicPhrase = mnemonicPhrase;
    }

    private ECKey getECKey(Integer addressId) {
        BigInteger privateKey = getDerivedPrivateKey(addressId);
        return ECKey.fromPrivate(privateKey);
    }

    @Override
    public String getNormalizedPrivateKey(Integer addressId) {
        ECKey ecKey = getECKey(addressId);
        return ecKey.getPrivateKeyEncoded(networkParameters).toString();
    }

    @Override
    public Integer getCoinType() {
        return coinType;
    }

    @Override
    public List<String> getMnemonicPhrase() {
        return mnemonicPhrase;
    }

    @Override
    public String getNormalizedPublicAddress(Integer addressId) {
        ECKey ecKey = getECKey(addressId);
        return Address.fromKey(networkParameters, ecKey, Script.ScriptType.P2PKH).toString();
    }
}
