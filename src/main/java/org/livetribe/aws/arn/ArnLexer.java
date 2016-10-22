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

import java.util.Stack;

import static java.util.Arrays.copyOfRange;

/**
 * @author LiveTribe
 */
class ArnLexer {
    private final char[] characters;
    private int current = 0;
    private Stack<Integer> stack = new Stack<Integer>();

    ArnLexer(String string) {
        characters = string.toCharArray();
    }

    void consume(String token) throws ArnSyntaxException {
        if (current + token.length() > characters.length) {
            throw new ArnSyntaxException("Token is larger than remaining characters " + new String(characters));
        }

        for (int i = 0; i < token.length(); i++) {
            if (characters[current + i] != token.charAt(i)) {
                throw new ArnSyntaxException("Token is larger than remaining characters " + new String(characters));
            }
        }
        current += token.length();
    }

    void colon() throws ArnSyntaxException {
        consume(":");
    }

    String scanToColon() {
        return scan(':');
    }

    String scan(Character stop) {
        StringBuilder buffer = new StringBuilder();
        while (current < characters.length && characters[current] != stop) {
            buffer.append(characters[current++]);
        }
        return buffer.toString();
    }

    String scanToEOL() {
        StringBuilder buffer = new StringBuilder();
        while (current < characters.length) {
            buffer.append(characters[current++]);
        }
        return buffer.toString();
    }

    void mark() {
        stack.push(current);
    }

    void restore() {
        current = stack.pop();
    }

    boolean done() {
        return current == characters.length;
    }

    @Override
    public String toString() {
        return "ArnLexer{" +
               "toBeScanned=" + new String(copyOfRange(characters, current, characters.length)) +
               ", characters=" + new String(characters) +
               ", current=" + current +
               ", stack=" + stack +
               '}';
    }
}
