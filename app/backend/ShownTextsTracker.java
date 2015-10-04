package backend;

import com.google.common.collect.ImmutableMap;
import models.Text;

import java.util.Map;

/**
 * Created by hlib on 03.10.15.
 */
public class ShownTextsTracker {
    public static Map<Long, Integer> getShownTextsStatisticsForUser() {
        //TODO replace with call to dao here
        return ImmutableMap.of(12345L, 12);
    }

    public static void trackTextView(Long textId) {
        Text text = Text.findById(textId);
        text.setShowedCount(text.getShowedCount() + 1);
        text.update();
    }
}
