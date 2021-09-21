package com.testinggospels.jmsservice.helpers;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.google.common.io.BaseEncoding;

interface Base64Encoder {
    String encode(byte[] content);

    String encode(byte[] content, boolean padding);

    byte[] decode(String base64);
}

public class Encoding {

    private static Base64Encoder encoder = null;

    private static Base64Encoder getInstance() {
        if (encoder == null) {
            synchronized (Encoding.class) {
                if (encoder == null) {
                    encoder = new GuavaBase64Encoder();
                }
            }
        }

        return encoder;
    }

    public static byte[] decodeBase64(String base64) {
        return base64 != null ? getInstance().decode(base64) : null;
    }

    public static String encodeBase64(byte[] content) {
        return encodeBase64(content, true);
    }

    public static String encodeBase64(byte[] content, boolean padding) {
        return content != null ? getInstance().encode(content, padding) : null;
    }

    public static String urlEncode(String unencodedUrl) {
        try {
            return URLEncoder.encode(unencodedUrl, "utf-8");
        } catch (UnsupportedEncodingException e) {
            return e.getMessage();
        }
    }

}

class GuavaBase64Encoder implements Base64Encoder {

    @Override
    public String encode(byte[] content) {
        return encode(content, true);
    }

    @Override
    public String encode(byte[] content, boolean padding) {
        BaseEncoding encoder = BaseEncoding.base64();
        if (!padding) {
            encoder = encoder.omitPadding();
        }
        return encoder.encode(content);
    }

    @Override
    public byte[] decode(String base64) {
        return BaseEncoding.base64().decode(base64);
    }
}