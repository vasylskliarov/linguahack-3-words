package controllers;

import backend.ShownTextsTracker;
import backend.TextFinder;
import backend.UnknownWordsAnalyzer;
import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.util.*;

/**
 * Created by hlib on 03.10.15.
 */
public class RestControllers extends Controller {
    private static final String DUMMY = "Suicide is when a person chooses to kill himself or herself. When someone kills himself, people say that he has \"committed suicide\". When a person thinks about killing themselves, the person is said to be suicidal.\n" +
            "When people start having thoughts about killing themselves it is, or should be, a medical emergency. They should get a suicide risk assessment as soon as possible.\n" +
            "There are many reasons why a person might think about committing suicide. Most people who are suicidal have some type of mental condition or illness. They may have a chronic condition, which means it has been going on for a long time. But it may be an acute condition – which means the first symptoms of mental illness happened rather quickly.\n";

    public static Result getText() {
        Map<String, Object> result = new HashMap<>();
        result.put("content", DUMMY);
        return getFollowingText(result);
    }

    @BodyParser.Of(BodyParser.Json.class)
    public static Result getNextText() {
        //TODO improve json processing
        Http.RequestBody body = request().body();
        JsonNode jsonNode = body.asJson().get("unknownWords");
        System.out.println(jsonNode.asText());
        Iterator<JsonNode> it = jsonNode.elements();
        List<String> unknownWordList =  new ArrayList<String>();
        while (it.hasNext()) {
            unknownWordList.add(it.next().asText());
        }
        //TODO get text id from ui
        UnknownWordsAnalyzer.updateWordsStatistics(TextFinder.getNormalizedWordsByTextId(123L), unknownWordList);

        Map<String, Object> result = new HashMap<>();
        result.put("content", DUMMY);
        return getFollowingText(result);
    }

    private static Result getFollowingText(Map<String, Object> text) {
        if (text.containsKey("content")) {
            text.put("content", toHtml((String) text.get("content")));
        }
        Map<String, Double> features = UnknownWordsAnalyzer.getFeaturesForUser();
        Map<Long, Integer> shownTextsStatistics = ShownTextsTracker.getShownTextsStatisticsForUser();
        TextFinder.getTextId(features, shownTextsStatistics);
        return ok(Json.toJson(text));
    }

    private static Object toHtml(String text) {
        String result = "";
        for (String line: text.split("\n")) {
            result += "<p>" + line + "</p>";
        }
        return result;
    }
}
