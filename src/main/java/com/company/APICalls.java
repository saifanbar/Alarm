package com.company;

import com.google.gson.*;

import java.net.URLEncoder;
import java.io.UnsupportedEncodingException;
import com.mashape.unirest.http.*;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class APICalls {

    public int trafficInformation() throws UnsupportedEncodingException, UnirestException {
        // Host url
        String host = "https://maps.googleapis.com/maps/api/directions/json";
        String charset = "UTF-8";
        // Headers for a request
        String Host = "maps.googleapis.com";
        String key = "AIzaSyBm1pd5qszZJgbbH-2n_N-eu2ouxBqfPKU";//Type here your key
        // Params
        String origin = "2755 I-20 Frontage Rd, Grand Prairie, TX 75052";
        String destination = "8613 Trinity Vista Trail, Hurst, TX 76053";
        String departure_time = "now";
        String travel_mode = "driving";
        String traffic_model = "best_guess";
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
        System.out.println("Duration without traffic: " + duration);
        System.out.println("Duration with traffic: " + durationInTraffic);

        String durationString = duration.toString().replaceAll("[^0-9]", "");

        int durationToInt = Integer.parseInt(durationString);

        System.out.println(durationToInt);

        return durationToInt;
    }
}
