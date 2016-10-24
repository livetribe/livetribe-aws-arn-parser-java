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
public class IamSamlProviderArnTest {
    public void testIamSamlProviderArn() {
        IamSamlProviderArn arn = new IamSamlProviderArn("123456789012", "ADFSProvider");
        assertEquals(arn.toString(), "arn:aws:iam::123456789012:saml-provider/ADFSProvider");
    }

    public void testParseIamSamlProviderArn() throws ArnSyntaxException {
        assertEquals(parseArn("arn:aws:iam::123456789012:saml-provider/ADFSProvider"),
                     new IamSamlProviderArn("123456789012", "ADFSProvider"));
    }

    public void testSet() {
        Set<Arn> arns = new HashSet<Arn>();

        arns.add(new IamSamlProviderArn("123456789012", "ADFSProvider"));
        arns.add(new IamSamlProviderArn("123456789012", "ADFSProvider"));

        assertEquals(arns.size(), 1);
    }

    public void testEquals() {
        Arn first = new IamSamlProviderArn("123456789012", "ADFSProvider");
        Arn second = new IamSamlProviderArn("123456789012", "ADFSProvider");

        assert first.equals(second);
    }
}
