package com.company;

import com.google.gson.*;

import java.net.URLEncoder;
import java.io.UnsupportedEncodingException;
import com.mashape.unirest.http.*;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class APICalls {

    public long durationWithoutTraffic() throws UnsupportedEncodingException, UnirestException {
        // Host url
        String host = "https://maps.googleapis.com/maps/api/directions/json";
        String charset = "UTF-8";
        // Headers for a request
        String Host = "maps.googleapis.com";
        String key = "";//Type here your key
        // Params
        String origin = ""; //Type starting address here
        String destination = ""; //Type destination address here
        String departure_time = "now";
        String travel_mode = "driving";
        String traffic_model = "pessimistic";
        // Format query for preventing encoding problems
        String query = String.format("origin=%s&destination=%s&departure_time=%s&&travel_mode=%s&traffic_model=%s&key=%s",
                URLEncoder.encode(origin, charset),
                URLEncoder.encode(destination, charset),
                URLEncoder.encode(departure_time, charset),
                URLEncoder.encode(travel_mode, charset),
                URLEncoder.encode(traffic_model, charset),
                URLEncoder.encode(key, charset));

        HttpResponse <JsonNode> response = Unirest.get(host + "?" + query)
                .header("Host", Host)
                .asJson();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(response.getBody().toString());

        String prettyJsonString = gson.toJson(je);
        //System.out.println(prettyJsonString); // Prints full body call

        JsonObject jsonObject = je.getAsJsonObject();
        JsonArray jsonArray = jsonObject.getAsJsonArray("routes");
        JsonElement jsonElement = jsonArray.get(0);
        JsonObject jsonObject1 = jsonElement.getAsJsonObject();
        JsonArray jsonArray1 = jsonObject1.getAsJsonArray("legs");
        JsonElement jsonElement1 = jsonArray1.get(0);
        JsonObject jsonObject2 = jsonElement1.getAsJsonObject().getAsJsonObject("duration");
        JsonObject jsonObject3 = jsonElement1.getAsJsonObject().getAsJsonObject("duration_in_traffic");

        JsonElement duration = jsonObject2.get("text");
        JsonElement durationInTraffic = jsonObject3.get("text");
        //System.out.println("Duration without traffic: " + duration);
        //System.out.println("Duration with traffic: " + durationInTraffic);

        String durationString = duration.toString().replaceAll("[^0-9]", "");

        long durationToLong = Long.parseLong(durationString);

        //System.out.println(durationToLong);

        return durationToLong;
    }

    public long durationWithTraffic() throws UnsupportedEncodingException, UnirestException {
        // Host url
        String host = "https://maps.googleapis.com/maps/api/directions/json";
        String charset = "UTF-8";
        // Headers for a request
        String Host = "maps.googleapis.com";
        String key = "";//Type here your key
        // Params
        String origin = "2755 I-20 Frontage Rd, Grand Prairie, TX 75052";
        String destination = "8613 Trinity Vista Trl";
        String departure_time = "now";
        String travel_mode = "driving";
        String traffic_model = "pessimistic";
        // Format query for preventing encoding problems
        String query = String.format("origin=%s&destination=%s&departure_time=%s&&travel_mode=%s&traffic_model=%s&key=%s",
                URLEncoder.encode(origin, charset),
                URLEncoder.encode(destination, charset),
                URLEncoder.encode(departure_time, charset),
                URLEncoder.encode(travel_mode, charset),
                URLEncoder.encode(traffic_model, charset),
                URLEncoder.encode(key, charset));

        HttpResponse <JsonNode> response = Unirest.get(host + "?" + query)
                .header("Host", Host)
                .asJson();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(response.getBody().toString());

        String prettyJsonString = gson.toJson(je);
        //System.out.println(prettyJsonString); // Prints full body call

        JsonObject jsonObject = je.getAsJsonObject();
        JsonArray jsonArray = jsonObject.getAsJsonArray("routes");
        JsonElement jsonElement = jsonArray.get(0);
        JsonObject jsonObject1 = jsonElement.getAsJsonObject();
        JsonArray jsonArray1 = jsonObject1.getAsJsonArray("legs");
        JsonElement jsonElement1 = jsonArray1.get(0);
        JsonObject jsonObject2 = jsonElement1.getAsJsonObject().getAsJsonObject("duration");
        JsonObject jsonObject3 = jsonElement1.getAsJsonObject().getAsJsonObject("duration_in_traffic");

        JsonElement duration = jsonObject2.get("text");
        JsonElement durationInTraffic = jsonObject3.get("text");
        //System.out.println("Duration without traffic: " + duration);
        //System.out.println("Duration with traffic: " + durationInTraffic);

        String durationInTrafficString = durationInTraffic.toString().replaceAll("[^0-9]", "");

        long durationInTrafficToLong = Long.parseLong(durationInTrafficString);

        System.out.println(durationInTrafficToLong);

        return durationInTrafficToLong;
    }
}
