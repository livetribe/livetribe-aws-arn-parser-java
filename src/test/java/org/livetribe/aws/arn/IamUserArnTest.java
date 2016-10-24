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

import java.util.HashSet;
import java.util.Set;

import static org.livetribe.aws.arn.ArnParser.parseArn;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;


/**
 * @author LiveTribe
 */
@Test
public class IamUserArnTest {
    public void testIamUserArn() {
        IamUserArn arn = new IamUserArn("123456789012", "Bob");
        assertEquals(arn.toString(), "arn:aws:iam::123456789012:user/Bob");

        arn = new IamUserArn("123456789012", "division_abc/subdivision_xyz/Bob");
        assertEquals(arn.toString(), "arn:aws:iam::123456789012:user/division_abc/subdivision_xyz/Bob");
    }

    public void testParseIamUserArn() throws ArnSyntaxException {
        assertEquals(parseArn("arn:aws:iam::123456789012:user/Bob"),
                     new IamUserArn("123456789012", "Bob"));

        assertEquals(parseArn("arn:aws:iam::123456789012:user/division_abc/subdivision_xyz/Bob"),
                     new IamUserArn("123456789012", "division_abc/subdivision_xyz/Bob"));
    }

    public void testSet() {
        Set<Arn> arns = new HashSet<Arn>();

        arns.add(new IamUserArn("123456789012", "Bob"));
        arns.add(new IamUserArn("123456789012", "Bob"));

        assertEquals(arns.size(), 1);
    }

    public void testEquals() {
        Arn first = new IamUserArn("123456789012", "Bob");
        Arn second = new IamUserArn("123456789012", "Bob");

        assert first.equals(second);
    }
}
