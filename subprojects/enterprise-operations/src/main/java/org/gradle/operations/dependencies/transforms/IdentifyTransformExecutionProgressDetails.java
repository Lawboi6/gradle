/*
 * Copyright 2023 the original author or authors.
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

package org.gradle.operations.dependencies.transforms;

import org.gradle.operations.dependencies.variants.ComponentIdentifier;

import java.util.Map;

public interface IdentifyTransformExecutionProgressDetails {

    /**
     * The identity of the transform execution.
     * <p>
     * Unique within the current build tree.
     */
    String getIdentity();

    ComponentIdentifier getInputArtifactComponentIdentifier();

    String getConsumerBuildPath();

    String getConsumerProjectPath();

    Map<String, String> getFromAttributes();

    Map<String, String> getToAttributes();

    String getInputArtifactName();

    Class<?> getTransformActionClass();

    byte[] getSecondaryInputValueHashBytes();
}
