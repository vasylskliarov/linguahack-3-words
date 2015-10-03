package backend;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

/**
 * Created by hlib on 03.10.15.
 */
public class UnknownWordsAnalyzer {
    public static Map<String, Double> getFeaturesForUser() {
        //TODO replace with call to dao here
        return ImmutableMap.of("cat", 1.0, "dog", 1.0);
    }
}
