package com.antsmc2.wallet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

public class EthereumWalletTest {
    List<String> words = Arrays.asList("liar", "slogan", "swap", "rail", "over",
            "old", "toe", "action", "foil", "offer", "aspect", "woman");

    @Test
    public void testCoinTypeReturns60() {
        EthereumWallet wallet = new EthereumWallet(null);
        Assertions.assertEquals(wallet.getCoinType(), 60);
    }


    @Test
    public void testMnemonicPhraseMatches() {
        EthereumWallet wallet = new EthereumWallet(words);
        Assertions.assertArrayEquals(words.toArray(new String[0]),
                wallet.getMnemonicPhrase().toArray(new String[0]));
    }

    @Test
    public void testTheCorrectPrivateKeyIsReturned() {
        EthereumWallet wallet = new EthereumWallet(words);
        String derivedKey = wallet.getNormalizedPrivateKey(17);
        String expectedKey = "0x655a2b1a612af58fee2430a845b82889fb96247b835ec8610b20f78cee039f6a";
        Assertions.assertEquals(expectedKey, derivedKey);
    }

    @Test
    public void testTheCorrectPublicKeyAddressIsReturned() {
        EthereumWallet wallet = new EthereumWallet(words);
        String address = wallet.getNormalizedPublicAddress(17);
        String expectedAddress = "0x8B94b6c02F2C3203964EAA52a87761f239f0121F";
        Assertions.assertEquals(expectedAddress, address);
    }
}
