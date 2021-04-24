package com.antsmc2.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

public class CryptoWalletTest {
    CryptoWallet wallet = new CryptoWallet() {
        @Override
        public Integer getCoinType() {
            return 60;  // ETH
        }

        @Override
        public List<String> getMnemonicPhrase() {
            return Arrays.asList("liar", "slogan", "swap", "rail", "over",
                    "old", "toe", "action", "foil", "offer", "aspect", "woman");
        }

        @Override
        public String getNormalizedPrivateKey(Integer addressId) {
            return null;
        }

        @Override
        public String getNormalizedPublicAddress(Integer addressId) {
            return null;
        }
    };

    @Test
    public void testGetDerivedPrivateKeyReturnsTheCorrectPrivateKey() {
        String expectedPrivateKeyString = "c9d04840d0a3af81b3367a29718422ae13127e12a601750df7e1089a886d42ae";
        BigInteger expectedPrivateKey = new BigInteger(expectedPrivateKeyString, 16);
        Assertions.assertEquals(wallet.getDerivedPrivateKey(13), expectedPrivateKey);
    }
}
