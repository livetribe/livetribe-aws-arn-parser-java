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

import static org.livetribe.aws.arn.Util.requireNonNull;

/**
 * @author LiveTribe
 */
public class ApiGatewayArn extends Arn {
    private final String region;

    public ApiGatewayArn(String region) {
        super("aws", "apigateway");
        this.region = requireNonNull(region, "Region must not be null");
    }

    public String getRegion() {
        return region;
    }

    static ApiGatewayArn parseArn(ArnLexer lexer) throws ArnSyntaxException {

        String region = lexer.scanToColon();
        lexer.colon();
        lexer.colon();

        lexer.consume("/");

        if (lexer.done()) {
            return new ApiGatewayRootArn(region);
        }

        String apiResources = lexer.scan('/');

        if (apiResources.equals("account")) {
            return new ApiGatewayAccountArn(region);
        } else if (apiResources.equals("apikeys")) {
            return new ApiGatewayApiKeysArn(region);
        } else if (apiResources.equals("clientcertificates")) {
            return new ApiGatewayClientCertificatesArn(region);
        } else if (apiResources.equals("domainnames")) {
            return new ApiGatewayDomainNamesArn(region);
        } else if (apiResources.equals("restapis")) {
            return ApiGatewayRestApisArn.parseArn(region, lexer);
        }

        return new ApiGatewayArn(region);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ApiGatewayArn)) return false;
        if (!super.equals(o)) return false;

        ApiGatewayArn that = (ApiGatewayArn) o;

        return region.equals(that.region);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + region.hashCode();
        return result;
    }
}
