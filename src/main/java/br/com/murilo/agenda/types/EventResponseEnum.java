package br.com.murilo.agenda.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Document(collection = "event")
public enum EventResponseEnum {

    YES("Yes"),
    NO("No"),
    MAYBE("Maybe");

    private String response;
    private static Map<String, EventResponseEnum> byResponse = new HashMap<>();

    EventResponseEnum(final String response) {
        this.response = response;
    }

    static {
        byResponse.putAll(
                Stream.of(
                        values())
                        .collect(
                                Collectors.toMap(
                                        EventResponseEnum::getResponse,
                                        Function.identity())));
    }

    public String getResponse() {
        return response;
    }

    public static Map<String, EventResponseEnum> getByResponse() {
        return byResponse;
    }

    public static EventResponseEnum fromString(String response) {
        for (EventResponseEnum item : values()) {
            if(item.getResponse().equalsIgnoreCase(response)) {
                return item;
            }
        }
        return null;

    }
}
