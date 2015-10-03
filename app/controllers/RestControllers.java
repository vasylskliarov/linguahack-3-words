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

    public static Result getText() {
        return getFollowingText();
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

        return getFollowingText();
    }

    private static Result getFollowingText() {
        Map<String, Double> features = UnknownWordsAnalyzer.getFeaturesForUser();
        Map<Long, Integer> shownTextsStatistics = ShownTextsTracker.getShownTextsStatisticsForUser();
        Long textId = TextFinder.getTextId(features, shownTextsStatistics);
        String plainText = TextFinder.getPlainTextById(textId);

        Map<String, Object> result = new HashMap<>();
        result.put("content", toHtml(plainText));

        return ok(Json.toJson(result));
    }

    private static Object toHtml(String text) {
        String result = "";
        for (String line: text.split("\n")) {
            result += "<p>" + line + "</p>";
        }
        return result;
    }
}
