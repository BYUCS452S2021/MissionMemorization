package com.example.missionmemorizeapp.network;

import com.example.missionmemorizeapp.model.Verse;
import com.google.gson.Gson;

public class Serializer {

    public static String serialize(Object requestInfo) {
        return (new Gson()).toJson(requestInfo);
    }

    public static <T> T deserialize(String value, Class<T> returnType) {
        return (new Gson()).fromJson(value, returnType);
    }

    public static Verse[] deserialize(String value, Verse[] returnType) {
        return (new Gson()).fromJson(value, Verse[].class);
    }
}
