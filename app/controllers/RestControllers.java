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
    private static final String DUMMY = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";

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
        //TODO replace with real call here
        System.out.println("Words to be updated in db:" + unknownWordList);

        Map<String, Object> result = new HashMap<>();
        result.put("content", DUMMY);
        return getFollowingText(result);
    }

    private static Result getFollowingText(Map<String, Object> text) {
        Map<String, Double> features = UnknownWordsAnalyzer.getFeaturesForUser();
        Map<Long, Integer> shownTextsStatistics = ShownTextsTracker.getShownTextsStatisticsForUser();
        TextFinder.getTextId(features, shownTextsStatistics);
        return ok(Json.toJson(text));
    }
}
