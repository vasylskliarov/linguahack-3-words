package backend;

import com.google.common.collect.ImmutableMap;
import models.Text;
import models.Word;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by hlib on 04.10.15.
 */
public class TextFinderTest {

    private static final Map<String, Double> WORDS_STATISTICS = ImmutableMap.<String, Double>builder()
            .put("man", 1.0)
            .put("why", 0.0)
            .put("what", 0.5)
            .put("when", 0.0)
            .put("yesterday", 0.75)
            .build();

//    @Test
//    public void testGetTextId() throws Exception {
//        Word word1 = new Word();
//        word1.setNormalizedValue("what");
//        Word word2 = new Word();
//        word2.setNormalizedValue("why");
//
//        Text text = mock(Text.class);
//        when(text.getNormalizedWords()).thenReturn(Arrays.asList(word1, word2));
//        Dao dao = mock(Dao.class);
//        when(dao.getAllTexts()).thenReturn(Arrays.asList(text));
//        TextFinder textFinder = new TextFinder();
//        textFinder.setDao(dao);
//        textFinder.getTextId(WORDS_STATISTICS, Collections.emptyMap());
//    }
}