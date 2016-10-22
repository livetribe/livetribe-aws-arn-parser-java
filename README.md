# aws-arn-parser-java

[![Build Status](https://travis-ci.org/livetribe/livetribe-aws-arn-parser-java.svg?branch=master)](https://travis-ci.org/livetribe/livetribe-aws-arn-parser-java) [![Coverage Status](https://coveralls.io/repos/github/livetribe/livetribe-aws-arn-parser-java/badge.svg?branch=master)](https://coveralls.io/github/livetribe/livetribe-aws-arn-parser-java?branch=master)

> AWS ARN Parser

## Supported ARNs
The AWS ARN parser currently supports:
* `apigateway` ARNs
* `execute-api` ARNs

## Usage

### Parsing
```java
import org.livetribe.aws.arn.ExecuteApiResourceArn;
import static org.livetribe.aws.arn.ArnParser.parseArn;

String token = "arn:aws:execute-api:us-east-1:123456789012:0df34wjxyd/*/GET/mydemoresource/*";
ExecuteApiResourceArn arn = (ExecuteApiResourceArn) parseArn(token);
```

### Generating ARNs
```java
import org.livetribe.aws.arn.Arn;
import org.livetribe.aws.arn.ExecuteApiResourceArn;
import static org.livetribe.aws.arn.HttpVerb.WILDCARD;

Arn arn = new ExecuteApiResourceArn("us-east-1", "123456789012", "0df34wjxyd",
                                    "test", WILDCARD, "/mydemoresource/*");
```
Converting the ARN to a `String` will provide a correctly formatted AWS ARN.

## License
[Apache License, Version 2.0](https://www.apache.org/licenses/LICENSE-2.0)
