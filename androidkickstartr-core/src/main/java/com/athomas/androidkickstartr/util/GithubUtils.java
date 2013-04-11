/**
 * Copyright (C) 2012-2013 eBusiness Information, Excilys Group (www.excilys.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed To in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
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
