package backend;

import java.util.List;
import java.util.Map;

import models.Text;
import models.Word;

public class TextFinder {

    private static final String DUMMY = "Suicide is when a person chooses to kill himself or herself. When someone kills himself, people say that he has \"committed suicide\". When a person thinks about killing themselves, the person is said to be suicidal.\n" +
            "When people start having thoughts about killing themselves it is, or should be, a medical emergency. They should get a suicide risk assessment as soon as possible.\n" +
            "There are many reasons why a person might think about committing suicide. Most people who are suicidal have some type of mental condition or illness. They may have a chronic condition, which means it has been going on for a long time. But it may be an acute condition – which means the first symptoms of mental illness happened rather quickly.\n";

    public static Long getTextId(Map<String, Double> features, Map<Long, Integer> shownFiles) {
        return 42L;
    }

    public static String getPlainTextById(Long id){
        return DUMMY;
    }

    public static List<Word> getNormalizedWordsByTextId(Long id){
        return Text.findById(id).getNormalizedWords();
    }
}
