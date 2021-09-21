package com.testinggospels.jmsservice.helpers;

import java.io.IOException;
import java.util.Date;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

public class DateHelper implements Helper<Date> {

    @Override
    public Object apply(Date context, Options options) throws IOException {
        String format = options.hash("format", null);
        String offset = options.hash("offset", null);
        String timezone = options.hash("timezone", null);

        Date date = context != null ? context : new Date();
        if (offset != null) {
            date = new DateOffset(offset).shift(date);
        }

        return new RenderableDate(date, format, timezone);
    }
}