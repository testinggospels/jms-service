package com.fauxauldrich.kafkaservice.helpers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

// import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

public class ParseDateHelper implements Helper<String> {

    @Override
    public Object apply(String context, Options options) throws IOException {
        String format = options.hash("format", null);
        try {
            return format == null ? new StdDateFormat().parse(context) : new SimpleDateFormat(format).parse(context);
        } catch (ParseException e) {
            return e;
        }
    }

}
