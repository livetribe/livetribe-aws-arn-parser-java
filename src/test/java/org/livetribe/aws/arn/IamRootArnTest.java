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

import static org.livetribe.aws.arn.ArnParser.parseArn;
import static org.livetribe.aws.arn.HttpVerb.GET;
import static org.livetribe.aws.arn.HttpVerb.WILDCARD;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertSame;

import org.testng.annotations.Test;


/**
 * @author LiveTribe
 */
@Test
public class IamRootArnTest
{
    public void testIamRootArn() {
        IamRootArn arn = new IamRootArn("123456789012");
        assertEquals(arn.getPartition(), "aws");
        assertEquals(arn.getService(), "iam");
        assertEquals(arn.getAccountId(), "123456789012");
        assertEquals(arn.toString(), "arn:aws:iam::123456789012:root");
    }

    public void testParseIamRootArn() throws ArnSyntaxException {
        assertEquals(parseArn("arn:aws:iam::123456789012:root"),
                     new IamRootArn("123456789012"));
    }
}
