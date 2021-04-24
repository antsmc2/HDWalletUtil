package com.antsmc2.wallet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

public class BitcoinWalletTest {
    List<String> words = Arrays.asList("liar", "slogan", "swap", "rail", "over",
            "old", "toe", "action", "foil", "offer", "aspect", "woman");

    @Test
    public void testMainNetCoinTypeReturnsZero() {
        BitcoinWallet wallet = new BitcoinWallet(BitcoinWallet.NetworkType.MAIN_NET, null);
        Assertions.assertEquals(wallet.getCoinType(), 0);
    }

    @Test
    public void testTestNetCoinTypeReturnsOne() {
        BitcoinWallet wallet = new BitcoinWallet(BitcoinWallet.NetworkType.TEST_NET, null);
        Assertions.assertEquals(wallet.getCoinType(), 1);
    }

    @Test
    public void testMnemonicPhraseMatches() {
        BitcoinWallet wallet = new BitcoinWallet(BitcoinWallet.NetworkType.TEST_NET, words);
        Assertions.assertArrayEquals(words.toArray(new String[0]),
                 wallet.getMnemonicPhrase().toArray(new String[0]));
    }

    @Test
    public void testTheCorrectBitcoinPrivateKeyIsReturnedForMainNet() {
        BitcoinWallet wallet = new BitcoinWallet(BitcoinWallet.NetworkType.MAIN_NET, words);
        String derivedKey = wallet.getNormalizedPrivateKey(7);
        String expectedKey = "L5dgRYAx85hVywc9zw9rgyEqzfpMHgFuxN8axze741Ccjtv6bW29";
        Assertions.assertEquals(expectedKey, derivedKey);
    }

    @Test
    public void testTheCorrectBitcoinPrivateKeyIsReturnedForTestNet() {
        BitcoinWallet wallet = new BitcoinWallet(BitcoinWallet.NetworkType.TEST_NET, words);
        String derivedKey = wallet.getNormalizedPrivateKey(7);
        String expectedKey = "cSEwZPVqsBpRwH8knkrFKVSH9nkvHY5nVFY7gR7U1GT9VcixTpoA";
        Assertions.assertEquals(expectedKey, derivedKey);
    }

    @Test
    public void testTheCorrectPublicKeyAddressIsReturnedForMainNet() {
        BitcoinWallet wallet = new BitcoinWallet(BitcoinWallet.NetworkType.MAIN_NET, words);
        String address = wallet.getNormalizedPublicAddress(7);
        String expectedAddress = "1GkiesoDpGCt3q4aHpMfLGRq8P4pRhBfCa";
        Assertions.assertEquals(expectedAddress, address);
    }

    @Test
    public void testTheCorrectPublicKeyAddressIsReturnedForTestNet() {
        BitcoinWallet wallet = new BitcoinWallet(BitcoinWallet.NetworkType.TEST_NET, words);
        String address = wallet.getNormalizedPublicAddress(7);
        String expectedAddress = "mkE4LUTALxUjimvdJVvbZ6j2CGEWCxQ7sN";
        Assertions.assertEquals(expectedAddress, address);
    }
}
