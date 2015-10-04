package controllers;

import backend.Dao;
import backend.TextFinder;
import backend.WordsStatisticsService;
import models.Text;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

import java.util.*;


public class Application extends Controller {
    public static Result index() {
        return ok(index.render());
    }

    public static Result texts() {
        List<Text> result = new ArrayList<>();

        Map<String, Double> features = WordsStatisticsService.getFeaturesForUser();
        List<Long> textIds = new TextFinder().getTextIds(features);
        for (Long textId: textIds) {
            Text text = Text.findById(textId);
            result.add(text);
        }

        return ok(texts.render(result));
    }
}
