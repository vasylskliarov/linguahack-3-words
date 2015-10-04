package controllers;

import backend.TextFinder;
import models.Text;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Application extends Controller {
    public static Result index() {
        try {
            backend.Tmp.main(new String[] {});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ok(index.render());
    }

    public static Result texts() {
        List<Text> result = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            result.add(new Text(i + 1, "Dummy Text"));
        }

        return ok(texts.render(result));
    }
}
