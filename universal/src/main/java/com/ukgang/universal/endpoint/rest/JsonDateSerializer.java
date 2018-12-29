package com.ukgang.universal.endpoint.rest;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class JsonDateSerializer extends JsonSerializer<Date> {

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @Override
    public void serialize(Date date, JsonGenerator gen, SerializerProvider provider)
        throws IOException {

        String formattedDate = new SimpleDateFormat(DATE_FORMAT).format(date);

        gen.writeString(formattedDate);
    }

}
