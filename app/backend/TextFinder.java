package backend;

import java.util.List;
import java.util.Map;

import models.Text;
import models.Word;

public class TextFinder {

    public static Long getTextId(Map<String, Double> features, Map<Long, Integer> shownFiles) {
        return 42L;
    }

    public static String getPlainTextById(Long id){
        return "test";
    }

    public static List<Word> getNormalizedWordsByTextId(Long id){
        return Text.findById(id).getNormalizedWords();
    }
}
