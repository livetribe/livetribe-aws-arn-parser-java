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

import static org.livetribe.aws.arn.ArnParser.parseArn;
import static org.livetribe.aws.arn.HttpVerb.GET;
import static org.livetribe.aws.arn.HttpVerb.WILDCARD;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertSame;

/**
 * @author LiveTribe
 */
@Test
public class ExecuteApiResourceArnTest {
    public void testExecuteApiAResourceArn() {

        ExecuteApiResourceArn arn = new ExecuteApiResourceArn("us-east-1", "123456789012", "0df34wjxyd", "*", GET, "/mydemoresource/*");
        assertEquals(arn.getPartition(), "aws");
        assertEquals(arn.getService(), "execute-api");
        assertEquals(arn.getRegion(), "us-east-1");
        assertEquals(arn.getAccountId(), "123456789012");
        assertEquals(arn.getApiId(), "0df34wjxyd");
        assertEquals(arn.getStageName(), "*");
        assertSame(arn.getHttpVerb(), GET);
        assertEquals(arn.getResourcePathSpecifier(), "/mydemoresource/*");
        assertEquals(arn.toString(), "arn:aws:execute-api:us-east-1:123456789012:0df34wjxyd/*/GET/mydemoresource/*");

        arn = new ExecuteApiResourceArn();
        assertEquals(arn.getPartition(), "aws");
        assertEquals(arn.getService(), "execute-api");
        assertEquals(arn.getRegion(), "*");
        assertEquals(arn.getAccountId(), "*");
        assertEquals(arn.getApiId(), "*");
        assertEquals(arn.getStageName(), "*");
        assertSame(arn.getHttpVerb(), WILDCARD);
        assertEquals(arn.getResourcePathSpecifier(), "/*");
        assertEquals(arn.toString(), "arn:aws:execute-api:*:*:*");

        arn = new ExecuteApiResourceArn("us-east-1");
        assertEquals(arn.getPartition(), "aws");
        assertEquals(arn.getService(), "execute-api");
        assertEquals(arn.getRegion(), "us-east-1");
        assertEquals(arn.getAccountId(), "*");
        assertEquals(arn.getApiId(), "*");
        assertEquals(arn.getStageName(), "*");
        assertSame(arn.getHttpVerb(), WILDCARD);
        assertEquals(arn.getResourcePathSpecifier(), "/*");
        assertEquals(arn.toString(), "arn:aws:execute-api:us-east-1:*:*");

        arn = new ExecuteApiResourceArn("us-east-1", "*", "0df34wjxyd");
        assertEquals(arn.getPartition(), "aws");
        assertEquals(arn.getService(), "execute-api");
        assertEquals(arn.getRegion(), "us-east-1");
        assertEquals(arn.getAccountId(), "*");
        assertEquals(arn.getApiId(), "0df34wjxyd");
        assertEquals(arn.getStageName(), "*");
        assertSame(arn.getHttpVerb(), WILDCARD);
        assertEquals(arn.getResourcePathSpecifier(), "/*");
        assertEquals(arn.toString(), "arn:aws:execute-api:us-east-1:*:0df34wjxyd/*");

        arn = new ExecuteApiResourceArn("us-east-1", "*", "*");
        assertEquals(arn.getPartition(), "aws");
        assertEquals(arn.getService(), "execute-api");
        assertEquals(arn.getRegion(), "us-east-1");
        assertEquals(arn.getAccountId(), "*");
        assertEquals(arn.getApiId(), "*");
        assertEquals(arn.getStageName(), "*");
        assertSame(arn.getHttpVerb(), WILDCARD);
        assertEquals(arn.getResourcePathSpecifier(), "/*");
        assertEquals(arn.toString(), "arn:aws:execute-api:us-east-1:*:*");

        arn = new ExecuteApiResourceArn("us-east-1", "*", "0df34wjxyd", "test");
        assertEquals(arn.getPartition(), "aws");
        assertEquals(arn.getService(), "execute-api");
        assertEquals(arn.getRegion(), "us-east-1");
        assertEquals(arn.getAccountId(), "*");
        assertEquals(arn.getApiId(), "0df34wjxyd");
        assertEquals(arn.getStageName(), "test");
        assertSame(arn.getHttpVerb(), WILDCARD);
        assertEquals(arn.getResourcePathSpecifier(), "/*");
        assertEquals(arn.toString(), "arn:aws:execute-api:us-east-1:*:0df34wjxyd/test/*");

        arn = new ExecuteApiResourceArn("us-east-1", "123456789012", "0df34wjxyd", "test", WILDCARD, "/mydemoresource/*");
        assertEquals(arn.getPartition(), "aws");
        assertEquals(arn.getService(), "execute-api");
        assertEquals(arn.getRegion(), "us-east-1");
        assertEquals(arn.getAccountId(), "123456789012");
        assertEquals(arn.getApiId(), "0df34wjxyd");
        assertEquals(arn.getStageName(), "test");
        assertSame(arn.getHttpVerb(), WILDCARD);
        assertEquals(arn.getResourcePathSpecifier(), "/mydemoresource/*");
        assertEquals(arn.toString(), "arn:aws:execute-api:us-east-1:123456789012:0df34wjxyd/test/*/mydemoresource/*");
    }

    public void testParseExecuteApiResourceArn() throws ArnSyntaxException {
        assertEquals(parseArn("arn:aws:execute-api:us-east-1:123456789012:0df34wjxyd/*/GET/mydemoresource/*"),
                     new ExecuteApiResourceArn("us-east-1", "123456789012", "0df34wjxyd", "*", GET, "/mydemoresource/*"));

        assertEquals(parseArn("arn:aws:execute-api:*:*:*"),
                     new ExecuteApiResourceArn());

        assertEquals(parseArn("arn:aws:execute-api:*:*:*/*"),
                     new ExecuteApiResourceArn());

        assertEquals(parseArn("arn:aws:execute-api:*:*:*/*/*"),
                     new ExecuteApiResourceArn());

        assertEquals(parseArn("arn:aws:execute-api:us-east-1:*:*"),
                     new ExecuteApiResourceArn("us-east-1"));

        assertEquals(parseArn("arn:aws:execute-api:us-east-1:*:*/*"),
                     new ExecuteApiResourceArn("us-east-1"));

        assertEquals(parseArn("arn:aws:execute-api:us-east-1:*:*/*/*"),
                     new ExecuteApiResourceArn("us-east-1"));

        assertEquals(parseArn("arn:aws:execute-api:us-east-1:*:0df34wjxyd/*"),
                     new ExecuteApiResourceArn("us-east-1", "*", "0df34wjxyd"));

        assertEquals(parseArn("arn:aws:execute-api:us-east-1:*:0df34wjxyd/*/*"),
                     new ExecuteApiResourceArn("us-east-1", "*", "0df34wjxyd"));

        assertEquals(parseArn("arn:aws:execute-api:us-east-1:*:*"),
                     new ExecuteApiResourceArn("us-east-1", "*", "*"));

        assertEquals(parseArn("arn:aws:execute-api:us-east-1:*:*/*"),
                     new ExecuteApiResourceArn("us-east-1", "*", "*"));

        assertEquals(parseArn("arn:aws:execute-api:us-east-1:*:*/*/*"),
                     new ExecuteApiResourceArn("us-east-1", "*", "*"));

        assertEquals(parseArn("arn:aws:execute-api:us-east-1:*:0df34wjxyd/test/*"),
                     new ExecuteApiResourceArn("us-east-1", "*", "0df34wjxyd", "test"));

        assertEquals(parseArn("arn:aws:execute-api:us-east-1:123456789012:0df34wjxyd/test/*/mydemoresource/*"),
                     new ExecuteApiResourceArn("us-east-1", "123456789012", "0df34wjxyd", "test", WILDCARD, "/mydemoresource/*"));
    }
}
