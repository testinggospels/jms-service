package com.fauxauldrich.kafkaservice.helpers;

import java.io.IOException;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;
import java.util.Date;

public enum KafkaServiceHelper implements Helper<Object> {
    randomValue {
        private final RandomValueHelper helper = new RandomValueHelper();

        @Override
        public Object apply(final Object context, final Options options) throws IOException {
            return this.helper.apply(null, options);
        }
    },
    date {
        private final DateHelper helper = new DateHelper();

        @Override
        public Object apply(final Object context, final Options options) throws IOException {
            Date dateContext = context instanceof Date ? (Date) context : null;
            return this.helper.apply(dateContext, options);
        }
    },
    now {
        private final DateHelper helper = new DateHelper();

        @Override
        public Object apply(final Object context, final Options options) throws IOException {
            return this.helper.apply(null, options);
        }
    },
    parseDate {
        private final ParseDateHelper helper = new ParseDateHelper();

        @Override
        public Object apply(Object context, Options options) throws IOException {
            return helper.apply(context.toString(), options);
        }
    },
    trim {
        private final StringTrimHelper helper = new StringTrimHelper();

        @Override
        public Object apply(Object context, Options options) throws IOException {
            return helper.apply(context, options);
        }
    },
    base64 {
        private final Base64Helper helper = new Base64Helper();

        @Override
        public Object apply(Object context, Options options) throws IOException {
            return helper.apply(context, options);
        }
    },
    urlEncode {
        private final UrlEncodingHelper helper = new UrlEncodingHelper();

        @Override
        public Object apply(Object context, Options options) throws IOException {
            return helper.apply(context, options);
        }
    },
    size {
        private final SizeHelper helper = new SizeHelper();

        @Override
        public Object apply(Object context, Options options) throws IOException {
            return helper.apply(context, options);
        }
    },
    pickRandom {
        private final PickRandomHelper helper = new PickRandomHelper();

        @Override
        public Object apply(Object context, Options options) throws IOException {
            return helper.apply(context, options);
        }
    }
}
