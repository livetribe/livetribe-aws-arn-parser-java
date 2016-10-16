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
import static org.testng.Assert.assertEquals;

/**
 * @author LiveTribe
 */
@Test
public class ApiGatewayArnTest {
    // arn:aws:apigateway:us-east-1:lambda:path/2015-03-31/functions/arn:aws:lambda:us-east-1:MY_ACCT_ID:function:helloWorld/invocations
    // arn:aws:apigateway:{region}:lambda:path/{service_api}. e.g.
    // arn:aws:apigateway:region:lambda:path/2015-03-31/functions/arn:aws:lambda:us-west-2:012345678912:function:my-function/invocations
    // arn:aws:apigateway:region:lambda:path/2015-03-31/functions/${aws_lambda_function.authorizer.arn}/invocations
    // arn:aws:apigateway:{region}:lambda:path/{service_api}
    // arn:aws:apigateway:us-east-1:kinesis:action/ListStreams
    // arn:aws:apigateway:us-west-2:lambda:path//2015-03-31/functions/arn:aws:lambda:us-west-2:123456789012:function:Calc/invocations
    // arn:aws:apigateway:region:lambda:path/2015-03-31/functions/functionArn/invocations
    // arn:aws:apigateway:${region}:lambda:path/2015-03-31/functions/arn:aws:lambda:${region}:${account_id}:function:helloworld/invocations
    // arn:aws:apigateway:us-east-1:lambda:path/2015-03-31/functions/arn:aws:lambda:us-east-1:865555555:function:myFunction/invocations
    // arn:aws:apigateway:us-east-1:lambda:path/2015-03-31/functions/arn:aws:lambda:us-east-1:1add9a6f-1496-4ff6-a610-215aa4403f51:function:livecooktv-api/invocations
    // arn:aws:apigateway:${REGION}:lambda:path/2015-03-31/functions/${LAMBDAARN}/invocations

    // arn:aws:lambda:us-east-1:1add9a6f-1496-4ff6-a610-215aa4403f51:function:livecooktv-api

    // Lambda function URI, which has the form arn:aws:apigateway:region:lambda:path/path.
    // The path usually has the form /2015-03-31/functions/LambdaFunctionARN/invocations.

    public void testApiGatewayRootArn() {
        ApiGatewayRootArn arn = new ApiGatewayRootArn("us-east-1");
        assertEquals(arn.getPartition(), "aws");
        assertEquals(arn.getService(), "apigateway");
        assertEquals(arn.getRegion(), "us-east-1");
        assertEquals(arn.getApiResource(), "");
        assertEquals(arn.toString(), "arn:aws:apigateway:us-east-1::/");
    }

    public void testApiGatewayAccountArn() {
        ApiGatewayAccountArn arn = new ApiGatewayAccountArn("us-east-1");
        assertEquals(arn.getPartition(), "aws");
        assertEquals(arn.getService(), "apigateway");
        assertEquals(arn.getRegion(), "us-east-1");
        assertEquals(arn.getApiResource(), "account");
        assertEquals(arn.toString(), "arn:aws:apigateway:us-east-1::/account");
    }

    public void testApiGatewayApiKeysArn() {
        ApiGatewayApiKeysArn arn = new ApiGatewayApiKeysArn("us-east-1");
        assertEquals(arn.getPartition(), "aws");
        assertEquals(arn.getService(), "apigateway");
        assertEquals(arn.getRegion(), "us-east-1");
        assertEquals(arn.getApiResource(), "apikeys");
        assertEquals(arn.toString(), "arn:aws:apigateway:us-east-1::/apikeys");
    }

    public void testApiGatewayClientCertificatesArn() {
        ApiGatewayClientCertificatesArn arn = new ApiGatewayClientCertificatesArn("us-east-1");
        assertEquals(arn.getPartition(), "aws");
        assertEquals(arn.getService(), "apigateway");
        assertEquals(arn.getRegion(), "us-east-1");
        assertEquals(arn.getApiResource(), "clientcertificates");
        assertEquals(arn.toString(), "arn:aws:apigateway:us-east-1::/clientcertificates");
    }

    public void testApiGatewayDomainNamesArn() {
        ApiGatewayDomainNamesArn arn = new ApiGatewayDomainNamesArn("us-east-1");
        assertEquals(arn.getPartition(), "aws");
        assertEquals(arn.getService(), "apigateway");
        assertEquals(arn.getRegion(), "us-east-1");
        assertEquals(arn.getApiResource(), "domainnames");
        assertEquals(arn.toString(), "arn:aws:apigateway:us-east-1::/domainnames");
    }

    public void testApiGatewayRestApisArn() {
        ApiGatewayRestApisArn arn = new ApiGatewayRestApisArn("us-east-1", "/a/b/c");
        assertEquals(arn.getPartition(), "aws");
        assertEquals(arn.getService(), "apigateway");
        assertEquals(arn.getRegion(), "us-east-1");
        assertEquals(arn.getApiResource(), "restapis");
        assertEquals(arn.getResource(), "/a/b/c");
        assertEquals(arn.toString(), "arn:aws:apigateway:us-east-1::/restapis/a/b/c");
    }

    public void testParseApiGatewayRootArn() throws ArnSyntaxException {
        assertEquals(parseArn("arn:aws:apigateway:us-east-1::/"), new ApiGatewayRootArn("us-east-1"));
    }

    public void testParseApiGatewayAccountArn() throws ArnSyntaxException {
        assertEquals(parseArn("arn:aws:apigateway:us-east-1::/account"), new ApiGatewayAccountArn("us-east-1"));
    }

    public void testParseApiGatewayApiKeysArn() throws ArnSyntaxException {
        assertEquals(parseArn("arn:aws:apigateway:us-east-1::/apikeys"), new ApiGatewayApiKeysArn("us-east-1"));
    }

    public void testParseApiGatewayClientCertificatesArn() throws ArnSyntaxException {
        assertEquals(parseArn("arn:aws:apigateway:us-east-1::/clientcertificates"),
                     new ApiGatewayClientCertificatesArn("us-east-1"));
    }

    public void testParseApiGatewayDomainNamesArn() throws ArnSyntaxException {
        assertEquals(parseArn("arn:aws:apigateway:us-east-1::/domainnames"),
                     new ApiGatewayDomainNamesArn("us-east-1"));
    }
    public void testParseApiGatewayRestApisArn() throws ArnSyntaxException {
        assertEquals(parseArn("arn:aws:apigateway:us-east-1::/restapis/a/b/c"),
                     new ApiGatewayRestApisArn("us-east-1", "/a/b/c"));
    }
}
