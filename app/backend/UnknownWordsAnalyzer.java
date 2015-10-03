package backend;

import models.Word;

import java.util.*;

/**
 * Created by hlib on 03.10.15.
 */
public class UnknownWordsAnalyzer {
    public static Map<String, Double> getFeaturesForUser() {
        return Word.getWordStatistics();
    }

    public static void updateWordsStatistics(List<String> encounteredWords, List<String> unknownWords) {
        Set<String> encounteredWordsSet = new HashSet<>(encounteredWords);
        Set<String> unknownWordsSet = new HashSet<>(unknownWords);
        encounteredWordsSet.stream().forEach(w -> {
            Word word = Word.findByValue(w);
            word.setShowedCount(word.getShowedCount() + 1);
            word.update();
        });
        unknownWordsSet.stream().forEach(w -> {
            Word word = Word.findByValue(w);
            word.setUnknownCount(word.getUnknownCount() + 1);
            word.update();
        });
    }
}
