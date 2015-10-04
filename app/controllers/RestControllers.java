package controllers;

import backend.Dao;
import backend.ShownTextsTracker;
import backend.TextFinder;
import backend.WordsStatisticsService;
import com.fasterxml.jackson.databind.JsonNode;
import models.Text;
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
        Long textId = Long.parseLong(session().get("textId"));
        WordsStatisticsService.updateWordsStatistics(new TextFinder().getNormalizedWordsByTextId(textId), unknownWordList);
        ShownTextsTracker.trackTextView(textId);

        return getFollowingText();
    }

    private static Result getFollowingText() {
        Map<String, Double> features = WordsStatisticsService.getFeaturesForUser();
        //Map<Long, Integer> shownTextsStatistics = ShownTextsTracker.getShownTextsStatisticsForUser();
        Map<Long, Integer> shownTextsStatistics = Collections.<Long, Integer>emptyMap();
        Long textId = new TextFinder().getTextId(features, shownTextsStatistics);
        System.out.println(textId);
        if (Dao.cachedTexts != null) {
            Text textToRemove = new Text();
            textToRemove.setId(textId);
        }
        String plainText = TextFinder.getPlainTextById(textId);
        session().put("textId", Long.toString(textId));

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
