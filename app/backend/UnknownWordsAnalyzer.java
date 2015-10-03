package backend;

import models.Word;

import java.util.Map;

/**
 * Created by hlib on 03.10.15.
 */
public class UnknownWordsAnalyzer {
    public static Map<String, Double> getFeaturesForUser() {
        return Word.getWordStatistics();
    }
}
