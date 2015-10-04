package backend;

import models.Text;
import models.Word;

import java.util.List;
import java.util.Map;

/**
 * Created by hlib on 03.10.15.
 */
public class Dao {
    public List<Text> getAllTexts() {
        return Text.findAll();
    }

    public Long getRandomTextId() {
        // call Text method to query random text id
        return 42L;
    }
}
