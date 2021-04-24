package com.antsmc2.common;

import org.web3j.crypto.MnemonicUtils;
import java.util.Arrays;
import java.util.List;


public class CommonUtil {

    public static List<String> generateMnemonicsPhrase() throws Exception {
        byte[] initialEntropy = new byte[16];
        SecureRandomUtils.secureRandom().nextBytes(initialEntropy);
        String mnemonic = MnemonicUtils.generateMnemonic(initialEntropy);
        return Arrays.asList(mnemonic.split(" "));
    }

    public static List<String> getAllMnemonicsWords() {
        return MnemonicUtils.getWords();
    }

    public static byte[] getSeed(List<String> words) {
        return getSeed(words, null);
    }

    public static byte[] getSeed(List<String> words, String paraphrase) {
        return MnemonicUtils.generateSeed(String.join(" ", words), paraphrase);
    }
}
