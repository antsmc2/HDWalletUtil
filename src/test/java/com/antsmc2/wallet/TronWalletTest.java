package com.antsmc2.wallet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

public class TronWalletTest {
    List<String> words = Arrays.asList("liar", "slogan", "swap", "rail", "over",
            "old", "toe", "action", "foil", "offer", "aspect", "woman");

    @Test
    public void testCoinTypeReturns195() {
        TronWallet wallet = new TronWallet(null);
        Assertions.assertEquals(wallet.getCoinType(), 195);
    }


    @Test
    public void testMnemonicPhraseMatches() {
        TronWallet wallet = new TronWallet(words);
        Assertions.assertArrayEquals(words.toArray(new String[0]),
                wallet.getMnemonicPhrase().toArray(new String[0]));
    }

    @Test
    public void testTheCorrectPrivateKeyIsReturned() {
        TronWallet wallet = new TronWallet(words);
        String derivedKey = wallet.getNormalizedPrivateKey(11);
        String expectedKey = "6af53e2f3cf8087847055c8a88963d090e9205628781d277027eb6f3c21feb8d";
        Assertions.assertEquals(expectedKey, derivedKey);
    }

    @Test
    public void testTheCorrectPublicKeyAddressIsReturned() {
        TronWallet wallet = new TronWallet(words);
        String address = wallet.getNormalizedPublicAddress(11);
        String expectedAddress = "TMR3JHukarGwQBpgV74BCtd7g2Ey8USZQa";
        Assertions.assertEquals(expectedAddress, address);
    }

    @Test
    public void testTheCorrectPublicKeyHexAddressIsReturned() {
        TronWallet wallet = new TronWallet(words);
        String address = wallet.getNormalizedPublicHexAddress(11);
        String expectedAddress = "417d8c195d24b6c07fbfd1643e288e12c99f6eb336";
        Assertions.assertEquals(expectedAddress, address);
    }
}
