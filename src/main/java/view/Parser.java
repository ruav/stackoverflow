package view;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import model.Answer;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Parser {

    private static String URL = "http://api.stackexchange.com/2.2/search?order=desc&sort=activity&intitle=%s&site=stackoverflow";

    private static Logger LOGGER = LoggerFactory.getLogger(Parser.class);

    public static List<Answer> getAnswers(String query) throws IOException {

        LOGGER.debug("Query: {}", query);

        if(query == null || query.isEmpty()) {
            return null;
        }

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet getRequest = new HttpGet(
                String.format(URL, query));

        LOGGER.debug("URL: {}", String.format(URL, query));

        getRequest.addHeader("accept", "application/json");
        HttpResponse response = httpClient.execute(getRequest);

        StringBuilder builder = new StringBuilder();
        builder.append(EntityUtils.toString(response.getEntity()));

        JsonParser parser = new JsonParser();
        JsonElement jsonElement = parser.parse(builder.toString());
        JsonObject rootObject = jsonElement.getAsJsonObject();
        JsonArray jsonArray = rootObject.getAsJsonArray("items");

        List<Answer> answers = new ArrayList<>();

        for(JsonElement element : jsonArray) {
            Answer answer = new Answer();
            answer.setAuthor(element.getAsJsonObject().get("owner").getAsJsonObject().get("display_name").getAsString());
            answer.setTitle(element.getAsJsonObject().get("title").getAsString());
            answer.setUrl(element.getAsJsonObject().get("link").getAsString());
            answer.setDate(new Date(element.getAsJsonObject().get("creation_date").getAsLong() * 1_000));
            answer.setAnswered(element.getAsJsonObject().get("is_answered").getAsBoolean());
            answers.add(answer);
        }

        return answers;

    }



}
