package com.testinggospels.jmsservice.helpers;

import java.io.IOException;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

import java.util.List;
import java.util.Map;

public class SizeHelper implements Helper<Object> {
    @Override
    public Object apply(Object context, Options options) throws IOException {
        Class<?> contextClass = context.getClass();

        if (CharSequence.class.isAssignableFrom(contextClass)) {
            return ((CharSequence) context).length();
        }

        if (List.class.isAssignableFrom(contextClass)) {
            return ((List) context).size();
        }

        if (Map.class.isAssignableFrom(contextClass)) {
            return ((Map) context).size();
        }

        return null;
    }

}
