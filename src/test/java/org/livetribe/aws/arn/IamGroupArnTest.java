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
import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;


/**
 * @author LiveTribe
 */
@Test
public class IamGroupArnTest {
    public void testIamGroupArn() {
        IamGroupArn arn = new IamGroupArn("123456789012", "Developers");
        assertEquals(arn.toString(), "arn:aws:iam::123456789012:group/Developers");

        arn = new IamGroupArn("123456789012", "division_abc/subdivision_xyz/product_A/Developers");
        assertEquals(arn.toString(), "arn:aws:iam::123456789012:group/division_abc/subdivision_xyz/product_A/Developers");
    }

    public void testParseIamGroupArn() throws ArnSyntaxException {
        assertEquals(parseArn("arn:aws:iam::123456789012:group/Developers"),
                     new IamGroupArn("123456789012", "Developers"));

        assertEquals(parseArn("arn:aws:iam::123456789012:group/division_abc/subdivision_xyz/product_A/Developers"),
                     new IamGroupArn("123456789012", "division_abc/subdivision_xyz/product_A/Developers"));
    }
}
