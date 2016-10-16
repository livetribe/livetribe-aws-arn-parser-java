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

/**
 * @author LiveTribe
 */
public final class ArnParser {
    public static Arn parseArn(String string) throws ArnSyntaxException {
        ArnLexer lexer = new ArnLexer(string);

        lexer.consume("arn");
        lexer.colon();
        lexer.consume("aws");
        lexer.colon();

        String service = lexer.scanToColon();
        lexer.colon();

        Arn arn;
        if (service.equals("apigateway")) {
            arn = ApiGatewayArn.parseArn(lexer);
        } else if (service.equals("execute-api")) {
            arn = ExecuteApiArn.parseArn(lexer);
        } else {
            throw new ArnSyntaxException("Unrecognized service");
        }

        return arn;
    }
}
