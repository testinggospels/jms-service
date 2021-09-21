package com.testinggospels.jmsservice.helpers;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;
import com.github.jknack.handlebars.TagType;

import static com.testinggospels.jmsservice.helpers.Encoding.decodeBase64;
import static com.testinggospels.jmsservice.helpers.Encoding.encodeBase64;

import java.io.IOException;

public class Base64Helper implements Helper<Object> {

    @Override
    public Object apply(Object context, Options options) throws IOException {
        String value = options.tagType == TagType.SECTION ? options.fn(context).toString() : context.toString();

        if (Boolean.TRUE.equals(options.hash.get("decode"))) {
            return new String(decodeBase64(value));
        }

        Object paddingOption = options.hash.get("padding");
        boolean padding = paddingOption == null || Boolean.TRUE.equals(paddingOption);
        return encodeBase64(value.getBytes(), padding);
    }

}
