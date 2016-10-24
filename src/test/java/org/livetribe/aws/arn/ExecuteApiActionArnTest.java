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

import java.util.HashSet;
import java.util.Set;

import static org.livetribe.aws.arn.ArnParser.parseArn;
import static org.livetribe.aws.arn.ExecuteApiActionEnum.*;
import static org.testng.Assert.*;

/**
 * @author LiveTribe
 */
@Test
public class ExecuteApiActionArnTest {
    public void testExecuteApiActionArnWildcard() {
        ExecuteApiActionArn arn = new ExecuteApiActionArn();
        assertEquals(arn.getPartition(), "aws");
        assertEquals(arn.getService(), "execute-api");
        assertEquals(arn.getAction(), WILDCARD);
        assertEquals(arn.toString(), "arn:aws:execute-api:*");

        arn = new ExecuteApiActionArn(getExecuteApiActionEnum("*"));
        assertEquals(arn.getPartition(), "aws");
        assertEquals(arn.getService(), "execute-api");
        assertEquals(arn.getAction(), WILDCARD);
        assertEquals(arn.toString(), "arn:aws:execute-api:*");
    }

    public void testExecuteApiActionArnInvoke() {
        ExecuteApiActionArn arn = new ExecuteApiActionArn(getExecuteApiActionEnum("Invoke"));
        assertEquals(arn.getPartition(), "aws");
        assertEquals(arn.getService(), "execute-api");
        assertEquals(arn.getAction(), INVOKE);
        assertEquals(arn.toString(), "arn:aws:execute-api:Invoke");

    }

    public void testExecuteApiActionArnInvalidateCache() {
        ExecuteApiActionArn arn = new ExecuteApiActionArn(getExecuteApiActionEnum("InvalidateCache"));
        assertEquals(arn.getPartition(), "aws");
        assertEquals(arn.getService(), "execute-api");
        assertEquals(arn.getAction(), INVALIDATE_CACHE);
        assertEquals(arn.toString(), "arn:aws:execute-api:InvalidateCache");
    }

    public void testExecuteApiActionArnEquals() {
        assertEquals(new ExecuteApiActionArn(getExecuteApiActionEnum("InvalidateCache")),
                     new ExecuteApiActionArn(getExecuteApiActionEnum("InvalidateCache")));

        assertNotEquals(new ExecuteApiActionArn(getExecuteApiActionEnum("*")),
                        new ExecuteApiActionArn(getExecuteApiActionEnum("InvalidateCache")));

    }

    public void testExecuteApiActionArnHash() {
        Set<Arn> set = new HashSet<Arn>();

        set.add(new ExecuteApiActionArn(getExecuteApiActionEnum("*")));
        set.add(new ExecuteApiActionArn(getExecuteApiActionEnum("*")));

        assertEquals(set.size(), 1);
    }

    public void testExecuteApiActionArnNPE() {
        try {
            new ExecuteApiActionArn(null);
            fail("Should not be able to pass null to ExecuteApiActionArn");
        } catch (NullPointerException ignore) {
        }
    }

    public void testParseExecuteApiResourceArn() throws ArnSyntaxException {
        assertEquals(parseArn("arn:aws:execute-api:*"), new ExecuteApiActionArn());

        assertEquals(parseArn("arn:aws:execute-api:Invoke"), new ExecuteApiActionArn(INVOKE));

        assertEquals(parseArn("arn:aws:execute-api:InvalidateCache"), new ExecuteApiActionArn(INVALIDATE_CACHE));
    }

    public void testSet() {
        Set<Arn> arns = new HashSet<Arn>();

        arns.add(new ExecuteApiActionArn(INVALIDATE_CACHE));
        arns.add(new ExecuteApiActionArn(INVALIDATE_CACHE));

        assertEquals(arns.size(), 1);
    }

    public void testEquals() {
        Arn first = new ExecuteApiActionArn(INVALIDATE_CACHE);
        Arn second = new ExecuteApiActionArn(INVALIDATE_CACHE);

        assert first.equals(second);
    }
}
