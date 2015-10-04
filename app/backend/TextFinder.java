package backend;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import models.Text;
import models.Word;

public class TextFinder {

    private static double UNKNOWN_WORDS_RATE = 0.1;

    private static final String DUMMY = "Suicide is when a person chooses to kill himself or herself. When someone kills himself, people say that he has \"committed suicide\". When a person thinks about killing themselves, the person is said to be suicidal.\n" +
            "When people start having thoughts about killing themselves it is, or should be, a medical emergency. They should get a suicide risk assessment as soon as possible.\n" +
            "There are many reasons why a person might think about committing suicide. Most people who are suicidal have some type of mental condition or illness. They may have a chronic condition, which means it has been going on for a long time. But it may be an acute condition – which means the first symptoms of mental illness happened rather quickly.\n";

    private Dao dao = new Dao();

    public Long getTextId(Map<String, Double> features, Map<Long, Integer> shownFiles) {
        if (features.isEmpty()) {
            return dao.getRandomTextId();
        }
        List<Text> texts = dao.getAllTexts();

        double minDeviation = Double.MAX_VALUE;
        Long textID = null;

        for (Text text : texts) {
            int counter = 0;
            //if (shownFiles.get(text.getId()) == 0) {
                List<String> normalizedWords = text.getNormalizedWords();
                double cumulativeUnknownness = 0.0;
                for (String key : normalizedWords) {
                    if (features.containsKey(key)) {
                        cumulativeUnknownness += features.get(key);
                        ++counter;
                    }
                }
                double unknownWordsRateDeviation = Math.abs(cumulativeUnknownness / counter - UNKNOWN_WORDS_RATE);
                if (Double.compare(unknownWordsRateDeviation, 0.0) == 0) {
                    return text.getId();
                } else if (unknownWordsRateDeviation < minDeviation) {
                    minDeviation = unknownWordsRateDeviation;
                    textID = text.getId();
                }
            //}
        }
        return textID;
    }

    public static String getPlainTextById(Long id) {
        //return DUMMY;
        return Text.findById(id).getText();
    }

    public List<String> getNormalizedWordsByTextId(Long id) {
        Text foundText = Text.findById(id);
        if (foundText == null) {
            return Collections.emptyList();
        } else {
            return foundText.getNormalizedWords();
        }
    }

    public void setDao(Dao dao) {
        this.dao = dao;
    }
}
