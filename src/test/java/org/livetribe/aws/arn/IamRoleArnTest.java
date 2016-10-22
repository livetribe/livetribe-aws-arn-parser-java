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
public class IamRoleArnTest {
    public void testIamUserArn() {
        IamRoleArn arn = new IamRoleArn("123456789012", "S3Access");
        assertEquals(arn.toString(), "arn:aws:iam::123456789012:role/S3Access");

        arn = new IamRoleArn("123456789012", "application_abc/component_xyz/S3Access");
        assertEquals(arn.toString(), "arn:aws:iam::123456789012:role/application_abc/component_xyz/S3Access");
    }

    public void testParseIamRoleArn() throws ArnSyntaxException {
        assertEquals(parseArn("arn:aws:iam::123456789012:role/S3Access"),
                     new IamRoleArn("123456789012", "S3Access"));

        assertEquals(parseArn("arn:aws:iam::123456789012:role/application_abc/component_xyz/S3Access"),
                     new IamRoleArn("123456789012", "application_abc/component_xyz/S3Access"));
    }
}
