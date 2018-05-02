package be.nvb.aor.infrastructure.aws.port.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import spark.ResponseTransformer;

import java.util.function.Consumer;

public class JsonTransformer implements ResponseTransformer {

    private ObjectMapper mapper = new ObjectMapper();
    private Consumer<String> consumer;

    public JsonTransformer(Consumer<String> consumer) {
        this.consumer = consumer;
    }

    @Override
    public String render(Object model) {
        try {
            return mapper.writeValueAsString(model);
        } catch (JsonProcessingException e) {
            consumer.accept(String.format("Cannot serialize object: %s", e.getMessage()));
            return null;
        }
    }

}