/*
 * Copyright 2016 the original author or authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.livetribe.aws.arn;

/**
 * @author LiveTribe
 */
public enum HttpVerb {
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE"),
    PATCH("PATCH"),
    HEAD("HEAD"),
    OPTIONS("OPTIONS"),
    WILDCARD("*");

    private final String string;

    HttpVerb(String string) {
        this.string = string;
    }

    public static HttpVerb getHttpVerb(String string) {
        if ("*".equals(string)) {
            return WILDCARD;
        } else {
            return valueOf(string.toUpperCase());
        }
    }

    @Override
    public String toString() {
        return string;
    }
}
