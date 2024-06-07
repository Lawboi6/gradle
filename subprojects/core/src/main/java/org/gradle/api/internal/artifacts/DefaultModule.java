/*
 * Copyright 2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gradle.api.internal.artifacts;

import org.gradle.api.artifacts.component.ProjectComponentIdentifier;

import javax.annotation.Nullable;

public class DefaultModule implements Module {
    private final String group;
    private final String name;
    private final String version;
    private String status = DEFAULT_STATUS;

    public DefaultModule(String group, String name, String version) {
        this.group = group;
        this.name = name;
        this.version = version;
    }

    public DefaultModule(String group, String name, String version, String status) {
        this.group = group;
        this.name = name;
        this.version = version;
        this.status = status;
    }

    @Override
    public String getGroup() {
        return group;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getVersion() {
        return version;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Nullable
    @Override
    public ProjectComponentIdentifier getOwner() {
        return null;
    }

    @Nullable
    @Override
    public ProjectComponentIdentifier getComponentId() {
        return null;
    }
}
