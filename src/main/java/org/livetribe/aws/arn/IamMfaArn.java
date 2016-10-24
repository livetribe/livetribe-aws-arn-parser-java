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
public class IamMfaArn extends IamArn {
    private final String virtualDevice;

    public IamMfaArn(String accountId, String virtualDevice) {
        super(accountId);

        this.virtualDevice = requireNonNull(virtualDevice, "Virtual device cannot be null");
    }

    public String getVirtualDevice() {
        return virtualDevice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IamMfaArn)) return false;
        if (!super.equals(o)) return false;

        IamMfaArn that = (IamMfaArn)o;

        return virtualDevice.equals(that.virtualDevice);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + virtualDevice.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "arn:aws:iam::" + getAccountId() + ":mfa/" + virtualDevice;
    }
}
