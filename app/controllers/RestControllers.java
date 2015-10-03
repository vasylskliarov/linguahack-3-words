package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
        //TODO replace with real call here
        System.out.println("Words to be updated in db:" + unknownWordList);
        return getFollowingText();
    }

    private static Result getFollowingText() {
        String text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";
        return ok("{\"content\": " + text + "}");
    }
}
