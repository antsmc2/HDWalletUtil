package com.antsmc2.common;

import party.loveit.bip44forjava.utils.Bip44Utils;
import party.loveit.bip44forjava.utils.EnglishWords;
import party.loveit.bip44forjava.utils.MnemonicCode;

import java.util.List;

import static party.loveit.bip44forjava.utils.EnglishWords.*;

public class CommonUtil {

    public static List<String> generateMnemonicsPhrase() throws Exception {
        return Bip44Utils.generateMnemonicWords();
    }

    public static List<String> getAllMnemonicsWords() {
        return MnemonicCode.INSTANCE.getWordList();
    }

    public static byte[] getSeed(List<String> words) {
        return getSeed(words, null);
    }

    public static byte[] getSeed(List<String> words, String paraphrase) {
        if(paraphrase == null)
            paraphrase = "";
        return MnemonicCode.toSeed(words, paraphrase);
    }
}
