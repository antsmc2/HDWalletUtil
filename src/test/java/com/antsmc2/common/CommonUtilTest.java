package com.antsmc2.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

public class CommonUtilTest {
    @Test
    public void testMnemonicPhraseHas12Words() throws Exception {
        List<String> words = CommonUtil.generateMnemonicsPhrase();
        Assertions.assertEquals(words.size(), 12);
    }

    @Test
    public void testAllMnemonicWordsAreInWordList() throws Exception {
        List<String> words = CommonUtil.generateMnemonicsPhrase();
        List<String> wordDictionary = CommonUtil.getAllMnemonicsWords();
        boolean allWordsMatch = words.stream().allMatch(word->wordDictionary.contains(word));
        Assertions.assertTrue(allWordsMatch);
    }

    @Test
    public void testCorrectSeedIsDerivedFromMnemonicPhrase() {
        List<String> words = Arrays.asList("liar", "slogan", "swap", "rail", "over",
                "old", "toe", "action", "foil", "offer", "aspect", "woman");
        String expectedSeedHex = "cb06f56d56dc837ed38b6f7b78e16e3a2c0a3e1c3b9c50c4021f238c4954af87a69481fac773b79085a36cb9c682b3f9ae99f77ec0de787ed5356f663ccd3121";
        byte[] seed = CommonUtil.getSeed(words);
        String seedHex = new BigInteger(1, seed).toString(16);
        Assertions.assertEquals(seedHex, expectedSeedHex);
    }
}
