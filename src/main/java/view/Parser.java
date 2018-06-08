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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Parser {

    private static String URL = "http://api.stackexchange.com/2.2/search?order=desc&sort=activity&intitle=%s&site=stackoverflow";

/*
    public static void main(String[] args) throws IOException {

        String strURL = "http://api.stackexchange.com/2.2/search?order=desc&sort=activity&intitle=java&site=stackoverflow";

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet getRequest = new HttpGet(
                strURL);
        getRequest.addHeader("accept", "application/json");
        HttpResponse response = httpClient.execute(getRequest);

//        System.out.println(EntityUtils.toString(response.getEntity(), "UTF-8"));
//        System.out.println(builder.toString());

        StringBuilder builder = new StringBuilder();
        builder.append(EntityUtils.toString(response.getEntity()));

        JsonParser parser = new JsonParser();
        JsonElement jsonElement = parser.parse(builder.toString());
//        JsonElement jsonElement = parser.parse(jsonReader);
        JsonObject rootObject = jsonElement.getAsJsonObject(); // чтение главного объекта
        JsonArray jsonArray = rootObject.getAsJsonArray("items");

        System.out.println(rootObject);
        System.out.println(String.format("%s, %n%s, %n%s, %n%s",
                jsonArray.size(),
                jsonArray.get(0).toString(),
                jsonArray.get(1).toString(),
                jsonArray.get(2).toString()
                ));

        List<Answer> answers = new ArrayList<>();

        for(JsonElement element : jsonArray) {

            Answer answer = new Answer();
            answer.setAuthor(element.getAsJsonObject().get("owner").getAsJsonObject().get("display_name").getAsString());
            answer.setTitle(element.getAsJsonObject().get("title").getAsString());
            answer.setUrl(element.getAsJsonObject().get("link").getAsString());
            answer.setDate(new Date(element.getAsJsonObject().get("creation_date").getAsLong()));
            answer.setAnswered(element.getAsJsonObject().get("is_answered").getAsBoolean());

            System.out.println(answer);

        }



        File file = new File("json.json");
        FileWriter writer = new FileWriter(file);
        writer.write(builder.toString());

        writer.flush();

    }
*/

    public static List<Answer> getAnswers(String query) throws IOException {

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet getRequest = new HttpGet(
                String.format(URL, query));
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
            answer.setDate(new Date(element.getAsJsonObject().get("creation_date").getAsLong()));
            answer.setAnswered(element.getAsJsonObject().get("is_answered").getAsBoolean());

        }

        return answers;


    }



}
