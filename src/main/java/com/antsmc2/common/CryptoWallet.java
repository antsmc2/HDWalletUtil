package com.antsmc2.common;

import party.loveit.bip44forjava.utils.Bip44Utils;
import java.math.BigInteger;
import java.util.List;

public interface CryptoWallet {
    //coin types are defined here: https://github.com/satoshilabs/slips/blob/master/slip-0044.md
    Integer getCoinType();
    List<String> getMnemonicPhrase();

    /**
     * Returns the derived private key as an biginteger which can be converted to the right format for a coin type
     * @param addressId
     * @return
     */
    default BigInteger getDerivedPrivateKey(Integer addressId) {
        Integer coinType = this.getCoinType();
        String keyPath = String.format("m/44'/%s'/0'/0/%s", coinType, addressId);
        List<String> words = getMnemonicPhrase();
        return Bip44Utils.getPathPrivateKey(words, keyPath);
    }

    /**
     * Returns the private key according to the format used for the chosen cointype.
     * This class provides the basic implementation to be overridden by implementing classes
     * @param addressId
     * @return
     */
    String getNormalizedPrivateKey(Integer addressId);

    /**
     * Returns the public key address according to the format used for the chosen cointype.
     * This class provides the basic implementation to be overridden by implementing classes
     * @param addressId
     * @return
     */
    String getNormalizedPublicAddress(Integer addressId);
}
