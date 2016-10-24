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
public class IamAssumedRoleArn extends IamArn {
    private final String role;
    private final String roleSession;

    public IamAssumedRoleArn(String accountId, String role, String roleSession) {
        super(accountId);

        this.role = requireNonNull(role, "Role cannot be null");
        this.roleSession = requireNonNull(roleSession, "Role session cannot be null");
    }

    public String getRole() {
        return role;
    }

    public String getRoleSession() {
        return roleSession;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IamAssumedRoleArn)) return false;
        if (!super.equals(o)) return false;

        IamAssumedRoleArn that = (IamAssumedRoleArn)o;

        if (!role.equals(that.role)) return false;
        return roleSession.equals(that.roleSession);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + role.hashCode();
        result = 31 * result + roleSession.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "arn:aws:iam::" + getAccountId() + ":assumed-role/" + role + "/" + roleSession;
    }
}
