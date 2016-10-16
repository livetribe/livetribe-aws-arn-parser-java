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
public abstract class Arn {
    private final String partition;
    private final String service;

    Arn(String partition, String service) {
        this.partition = requireNonNull(partition, "partition cannot be null");
        this.service = requireNonNull(service, "service cannot be null");
    }

    public String getPartition() {
        return partition;
    }

    public String getService() {
        return service;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Arn)) return false;

        Arn arn = (Arn) o;

        if (!partition.equals(arn.partition)) return false;
        return service.equals(arn.service);
    }

    @Override
    public int hashCode() {
        int result = partition.hashCode();
        result = 31 * result + service.hashCode();
        return result;
    }
}
