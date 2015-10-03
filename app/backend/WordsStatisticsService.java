package backend;

import models.Word;

import java.util.*;

/**
 * Created by hlib on 03.10.15.
 */
public class WordsStatisticsService {
    public static Map<String, Double> getFeaturesForUser() {
        return Word.getWordStatistics();
    }

    public static void updateWordsStatistics(List<Word> encounteredWords, List<String> unknownWords) {
        Set<Word> encounteredWordsSet = new HashSet<>(encounteredWords);
        Set<String> unknownWordsSet = new HashSet<>(unknownWords);
        encounteredWordsSet.stream().forEach(word -> {
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
