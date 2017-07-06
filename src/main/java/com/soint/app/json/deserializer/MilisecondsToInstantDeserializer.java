package com.soint.app.json.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;

@Component
public class MilisecondsToInstantDeserializer extends JsonDeserializer<Instant> {

    private final Logger log = LoggerFactory.getLogger(MilisecondsToInstantDeserializer.class);

    @Override
    public Instant deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        String timestampMilli = jp.getText().trim();
        try {
            return Instant.ofEpochMilli(Long.valueOf(timestampMilli));
        } catch (NumberFormatException e) {
            log.error("Unable to deserialize timestamp: " + timestampMilli, e);
            return null;
        }
    }

}
