package backend;

import com.google.common.collect.ImmutableMap;
import models.Text;

import java.util.Map;

/**
 * Created by hlib on 03.10.15.
 */
public class ShownTextsTracker {
    public static Map<Long, Integer> getShownTextsStatisticsForUser() {
        return Text.getTextStatistics();
    }

    public static void trackTextView(Long textId) {
        Text text = Text.findById(textId);
        text.setShowedCount(text.getShowedCount() + 1);
        text.update();
    }
}
