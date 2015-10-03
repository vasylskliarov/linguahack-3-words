package backend;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import play.Play;

public class TextFinder {

    public static Long getTextId(Map<String, Double> features, Map<Long, Integer> shownFiles) {
        return 42L;
    }

    public String getPlainTextById(Long id){
        return "test";
    }

    public List<String> getNormalizedTextById(Long id){
        return Collections.emptyList();
    }
}
