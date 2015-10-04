package backend;

import models.Text;
import models.Word;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by hlib on 03.10.15.
 */
public class Dao {

    private final static Long[] STUB_TEXT_IDS = {-592328L, -1049090L, -1618176L};
    final Random random = new Random();

    public List<Text> getAllTexts() {
        return Text.findAll();
    }

    public Long getRandomTextId() {
        // call Text method to query random text id
        return getStubTextId();
    }

    private Long getStubTextId() {
        int index = random.nextInt(STUB_TEXT_IDS.length);
        return STUB_TEXT_IDS[index];
    }
}
