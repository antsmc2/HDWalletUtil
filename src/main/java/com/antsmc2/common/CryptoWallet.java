package com.antsmc2.common;

import org.web3j.crypto.Bip32ECKeyPair;
import static org.web3j.crypto.Bip32ECKeyPair.HARDENED_BIT;
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
        List<String> words = getMnemonicPhrase();
        final int[] path = {44 | HARDENED_BIT, coinType | HARDENED_BIT, 0 | HARDENED_BIT, 0, addressId};
        byte[] seed = CommonUtil.getSeed(words);
        Bip32ECKeyPair masterKey = Bip32ECKeyPair.generateKeyPair(seed);
        Bip32ECKeyPair keyPair = Bip32ECKeyPair.deriveKeyPair(masterKey, path);
        return keyPair.getPrivateKey();
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
