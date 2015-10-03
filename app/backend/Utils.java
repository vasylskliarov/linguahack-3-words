package backend;

import edu.stanford.nlp.ling.Word;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.process.WordTokenFactory;

import java.io.StringReader;
import java.util.*;

public class Utils {
    private static final String[] punctuation = {
            "", ",", ";", ".", ":", "_", "{", "}", "[", "]", "+", "*", "¡", "¿", "?", "=", ")", "(", "/", "&", "%", "$", "·",
    };
    private static final Set<String> punctuationSet = new HashSet<>(Arrays.asList(punctuation));
    private static final Porter porter = new Porter();

    public static String normalizedWord(String word) {
        return porter.stripAffixes(word);
    }

    public static String normalizedText(String text) {
        final StringJoiner joiner = new StringJoiner(" ");

        words(text).forEach(joiner::add);

        return joiner.toString();
    }

    public static List<String> words(String text) {
        final List<String> words = new ArrayList<>();

        for (String s : tokenize(text)) {
            final String lower = s.toLowerCase();
            final String stripped = normalizedWord(lower);

            if (!punctuationSet.contains(lower)) {
                words.add(stripped);
            }
        }

        return words;
    }

    private static List<String> tokenize(String text) {
        final List<String> result = new ArrayList<>();
        final StringReader sr = new StringReader(text);
        final PTBTokenizer tkzr = new PTBTokenizer<>(sr, new WordTokenFactory(), "untokenizable=noneKeep,americanize=true");

        for (Object o : tkzr.tokenize()) {
            result.add(((Word) o).word());
        }

        return result;
    }
}
