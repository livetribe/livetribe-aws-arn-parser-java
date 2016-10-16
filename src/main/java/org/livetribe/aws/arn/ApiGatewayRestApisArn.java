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
public class ApiGatewayRestApisArn extends ApiGatewayApiResourceArn {

    private final String resource;

    public ApiGatewayRestApisArn(String region, String resource) {
        super(region, "restapis");

        this.resource = requireNonNull(resource, "Resource path must be specified");
    }

    public String getResource() {
        return resource;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ApiGatewayRestApisArn)) return false;
        if (!super.equals(o)) return false;

        ApiGatewayRestApisArn that = (ApiGatewayRestApisArn) o;

        return resource.equals(that.resource);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + resource.hashCode();
        return result;
    }
    @Override
    public String toString() {
        return "arn:aws:apigateway:" + getRegion() + "::/restapis" + resource;
    }

    public static ApiGatewayRestApisArn parseArn(String region, ArnLexer lexer) throws ArnSyntaxException {
        lexer.consume("/");
        StringBuilder builder = new StringBuilder("/");
        builder.append(lexer.scan('/'));

        while (!lexer.done()) {
            lexer.consume("/");
            builder.append('/').append(lexer.scan('/'));
        }

        return new ApiGatewayRestApisArn(region, builder.toString());
    }
}
