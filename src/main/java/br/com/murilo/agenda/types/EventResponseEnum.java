package br.com.murilo.agenda.types;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Document
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
}
