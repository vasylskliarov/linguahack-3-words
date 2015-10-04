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
    private static Dao dao = new Dao();

    public static void updateWordsStatistics(List<String> encounteredWords, List<String> unknownWords) {
        Set<String> encounteredWordsSet = new HashSet<>(encounteredWords);
        Set<String> unknownWordsSet = new HashSet<>(unknownWords);
        dao.updateWordsShown(encounteredWordsSet);
        dao.updateWordsUnknown(unknownWordsSet);
    }
}
