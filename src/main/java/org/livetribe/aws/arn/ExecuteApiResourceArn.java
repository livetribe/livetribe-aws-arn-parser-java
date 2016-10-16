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

import static org.livetribe.aws.arn.HttpVerb.WILDCARD;
import static org.livetribe.aws.arn.Util.requireNonNull;

/**
 * @author LiveTribe
 */
public class ExecuteApiResourceArn extends ExecuteApiArn {
    private final String region;
    private final String accountId;
    private final String apiId;
    private final String stageName;
    private final HttpVerb httpVerb;
    private final String resourcePathSpecifier;

    public ExecuteApiResourceArn() {
        this("*", "*", "*", null, null, null);
    }

    public ExecuteApiResourceArn(String region) {
        this(region, "*", "*", null, null, null);
    }

    public ExecuteApiResourceArn(String region, String accountId, String apiId) {
        this(region, accountId, apiId, null, null, null);
    }

    public ExecuteApiResourceArn(String region, String accountId, String apiId, String stageName) {
        this(region, accountId, apiId, stageName, null, null);
    }

    public ExecuteApiResourceArn(String region, String accountId, String apiId, String stageName,
                                 HttpVerb httpVerb, String resourcePathSpecifier) {
        this.region = requireNonNull(region, "region cannot be null");
        this.accountId = requireNonNull(accountId, "accountId");
        this.apiId = requireNonNull(apiId, "apiId");
        this.stageName = stageName;
        this.httpVerb = httpVerb;
        this.resourcePathSpecifier = resourcePathSpecifier;

        if (httpVerb == null && resourcePathSpecifier != null) {
            throw new IllegalArgumentException("resourcePathSpecifier must be null");
        }
    }

    public String getRegion() {
        return region == null ? "*" : region;
    }

    public String getAccountId() {
        return accountId == null ? "*" : accountId;
    }

    public String getApiId() {
        return apiId == null ? "*" : apiId;
    }

    public String getStageName() {
        return stageName == null ? "*" : stageName;
    }

    public HttpVerb getHttpVerb() {
        return httpVerb == null ? WILDCARD : httpVerb;
    }

    public String getResourcePathSpecifier() {
        return resourcePathSpecifier == null ? "/*" : resourcePathSpecifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExecuteApiResourceArn)) return false;
        if (!super.equals(o)) return false;

        ExecuteApiResourceArn that = (ExecuteApiResourceArn) o;

        if (!region.equals(that.region)) return false;
        if (!accountId.equals(that.accountId)) return false;
        if (!apiId.equals(that.apiId)) return false;
        if (stageName != null ? !stageName.equals(that.stageName) : that.stageName != null) return false;
        if (httpVerb != that.httpVerb) return false;
        return resourcePathSpecifier != null ? resourcePathSpecifier.equals(that.resourcePathSpecifier) : that.resourcePathSpecifier == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + region.hashCode();
        result = 31 * result + accountId.hashCode();
        result = 31 * result + apiId.hashCode();
        result = 31 * result + (stageName != null ? stageName.hashCode() : 0);
        result = 31 * result + (httpVerb != null ? httpVerb.hashCode() : 0);
        result = 31 * result + (resourcePathSpecifier != null ? resourcePathSpecifier.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("arn:aws:execute-api:");

        builder.append(region).append(":");
        builder.append(accountId).append(":");
        builder.append(apiId);

        if (stageName != null) {
            builder.append("/").append(stageName);

            if (httpVerb != null) {
                builder.append("/").append(httpVerb);

                if (resourcePathSpecifier != null) {
                    builder.append(resourcePathSpecifier);
                }
            } else if (!stageName.equals("*")) {
                builder.append("/*");
            }
        } else if (!apiId.equals("*")) {
            builder.append("/*");
        }

        return builder.toString();
    }
}
