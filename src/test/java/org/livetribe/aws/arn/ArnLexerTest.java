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

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author LiveTribe
 */
@Test
public class ArnLexerTest {
    public void testConsumeOverflow() throws ArnSyntaxException {
        new ArnLexer("abc").consume("abc");

        try {
            new ArnLexer("abc").consume("abcd");
            fail("Should have thrown an exception");
        } catch (ArnSyntaxException ignore) {
        }
    }

    public void testConsumeEmpty() throws ArnSyntaxException {
        ArnLexer lexer = new ArnLexer("abc");

        lexer.consume("");

        assertFalse(lexer.done());
    }

    public void testConsume() throws ArnSyntaxException {
        ArnLexer lexer = new ArnLexer("abc");

        lexer.consume("a");
        lexer.consume("bc");

        assertTrue(lexer.done());
    }

    public void testConsumeMismatchedToken() throws ArnSyntaxException {
        ArnLexer lexer = new ArnLexer("abc");

        lexer.consume("ab");

        try {
            lexer.consume("z");
            fail("Should have thrown an exception");
        } catch (ArnSyntaxException ignore) {
        }

        assertFalse(lexer.done());
    }

    public void testColon() throws ArnSyntaxException {
        ArnLexer lexer = new ArnLexer(":");

        lexer.colon();

        assertTrue(lexer.done());
    }

    public void testMissingColon() throws ArnSyntaxException {
        ArnLexer lexer = new ArnLexer("a:");

        try {
            lexer.colon();
            fail("Should have thrown an exception");
        } catch (ArnSyntaxException ignore) {
        }

        assertFalse(lexer.done());
    }

    public void testScan() {
        ArnLexer lexer = new ArnLexer("arn:");

        assertEquals(lexer.scanToColon(), "arn");
        assertFalse(lexer.done());

        assertEquals(lexer.scanToColon(), "");
        assertFalse(lexer.done());
    }

    public void testMarkRestore() {
        ArnLexer lexer = new ArnLexer("arn");

        lexer.mark();
        assertEquals(lexer.scanToColon(), "arn");
        assertTrue(lexer.done());

        lexer.restore();
        assertFalse(lexer.done());
        assertEquals(lexer.scanToColon(), "arn");
        assertTrue(lexer.done());
    }
}
