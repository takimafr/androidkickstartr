package com.athomas.androidkickstartr.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GithubUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(GithubUtils.class);

    public static final String ACCESS_TOKEN = "accessToken";
    private static final String PROTOCOL = "http";
    private static final String HOSTNAME = "androidkickstartr.com";
    private static final int HOSTPORT = 80;

    public static URI createAndroidKickstartRUriWithAccessToken(String accessToken, String parameterName, String parameterValue) {
        return createAndroidKickstartRUri(new String[]{ACCESS_TOKEN, parameterName}, new String[]{accessToken, parameterValue});
    }

    public static URI createAndroidKickstartRUri(String parameterName, String parameterValue) {
        return createAndroidKickstartRUri(new String[]{parameterName}, new String[]{parameterValue});
    }

    public static URI createAndroidKickstartRUri(String[] parameterNames, String[] parameterValues) {
        try {
            return new URI(PROTOCOL, null, HOSTNAME, HOSTPORT, null,
                    createParametersString(parameterNames, parameterValues), null);
        } catch (URISyntaxException e) {
            LOGGER.error("Couldn't create URI : {}", e.getMessage());
        }
        return null;
    }

    public static String findAccessTokenInString(String string) {
        String accessToken = null;
        Pattern pattern = Pattern.compile("(\\w{40})");
        Matcher matcher = pattern.matcher(string);
        if (matcher.find()) {
            accessToken = matcher.group();
        }
        return accessToken;
    }

    private static String createParametersString(String[] parameterNames, String[] parameterValues) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < parameterNames.length; i++) {
            builder.append(parameterNames[i]).append("=").append(parameterValues[i]).append("&");
        }
        return builder.deleteCharAt(builder.length() - 1).toString();
    }

}
