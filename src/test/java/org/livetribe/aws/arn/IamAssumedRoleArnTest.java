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
public class IamAssumedRoleArnTest {
    public void testIamAssumedRoleArn() {
        IamAssumedRoleArn arn = new IamAssumedRoleArn("123456789012", "Accounting-Role", "Mary");
        assertEquals(arn.toString(), "arn:aws:iam::123456789012:assumed-role/Accounting-Role/Mary");
    }

    public void testParseIamAssumedRoleArn() throws ArnSyntaxException {
        assertEquals(parseArn("arn:aws:iam::123456789012:assumed-role/Accounting-Role/Mary"),
                     new IamAssumedRoleArn("123456789012", "Accounting-Role", "Mary"));
    }

    public void testSet() {
        Set<Arn> arns = new HashSet<Arn>();

        arns.add(new IamAssumedRoleArn("123456789012", "Accounting-Role", "Mary"));
        arns.add(new IamAssumedRoleArn("123456789012", "Accounting-Role", "Mary"));

        assertEquals(arns.size(), 1);
    }

    public void testEquals() {
        Arn first = new IamAssumedRoleArn("123456789012", "Accounting-Role", "Mary");
        Arn second = new IamAssumedRoleArn("123456789012", "Accounting-Role", "Mary");

        assert first.equals(second);
    }
}
