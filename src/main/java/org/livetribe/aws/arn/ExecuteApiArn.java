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

import static org.livetribe.aws.arn.ExecuteApiActionEnum.getExecuteApiActionEnum;
import static org.livetribe.aws.arn.HttpVerb.WILDCARD;
import static org.livetribe.aws.arn.HttpVerb.getHttpVerb;

/**
 * @author LiveTribe
 */
public abstract class ExecuteApiArn extends Arn {

    ExecuteApiArn() {
        super("aws", "execute-api");
    }

    static ExecuteApiArn parseArn(ArnLexer lexer) throws ArnSyntaxException {

        lexer.mark();
        String action = lexer.scanToColon();
        if (lexer.done()) {
            try {
                return new ExecuteApiActionArn(getExecuteApiActionEnum(action));
            } catch (IllegalArgumentException e) {
                throw new ArnSyntaxException("Illegal action");
            }
        } else {
            lexer.restore();

            String region = lexer.scanToColon();
            lexer.colon();

            String accountId = lexer.scanToColon();
            lexer.colon();

            String apiId = lexer.scan('/');

            String stageName = null;
            HttpVerb httpVerb = null;
            String resourcePathSpecifier = null;


            if (!lexer.done()) {
                lexer.consume("/");

                stageName = lexer.scan('/');
            }

            if (!lexer.done()) {
                lexer.consume("/");

                try {
                    httpVerb = getHttpVerb(lexer.scan('/'));
                } catch (IllegalArgumentException e) {
                    throw new ArnSyntaxException("Illegal HTTP verb");
                }
            }

            if (!lexer.done()) {
                lexer.consume("/");
                resourcePathSpecifier = "/" + lexer.scanToColon();
            }

            if (!lexer.done()) {
                throw new ArnSyntaxException("Invalid execute-api resource ARN");
            }

            if (resourcePathSpecifier != null && httpVerb != null
                && resourcePathSpecifier.equals("*")) {
                resourcePathSpecifier = null;
            }

            if (resourcePathSpecifier == null && httpVerb != null && stageName != null
                && httpVerb == WILDCARD) {
                httpVerb = null;
            }

            if (resourcePathSpecifier == null && httpVerb == null && stageName != null && apiId != null
                && stageName.equals("*")) {
                stageName = null;
            }

            return new ExecuteApiResourceArn(region, accountId, apiId, stageName, httpVerb, resourcePathSpecifier);
        }
    }
}
