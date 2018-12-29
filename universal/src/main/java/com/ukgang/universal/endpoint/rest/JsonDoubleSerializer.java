package com.ukgang.universal.endpoint.rest;

import java.io.IOException;
import java.text.DecimalFormat;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class JsonDoubleSerializer extends JsonSerializer<Double> {

    private static final String DOUBLE_FORMAT = "0.00";

    @Override
    public void serialize(Double num, JsonGenerator gen, SerializerProvider provider)
        throws IOException {
        String formattedDate = new DecimalFormat(DOUBLE_FORMAT).format(num);

        gen.writeString(formattedDate);
    }

}
