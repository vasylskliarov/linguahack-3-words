package backend;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

/**
 * Created by hlib on 03.10.15.
 */
public class ShownTextsTracker {
    public static Map<Long, Integer> getShownTextsStatisticsForUser() {
        //TODO replace with call to dao here
        return ImmutableMap.of(12345L, 12);
    }
}
