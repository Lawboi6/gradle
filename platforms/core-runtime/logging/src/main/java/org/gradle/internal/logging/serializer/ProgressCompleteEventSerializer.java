/*
 * Copyright 2016 the original author or authors.
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

package org.gradle.internal.logging.serializer;

import org.gradle.internal.logging.events.ProgressCompleteEvent;
import org.gradle.internal.operations.OperationIdentifier;
import org.gradle.internal.serialize.Decoder;
import org.gradle.internal.serialize.Encoder;
import org.gradle.internal.serialize.Serializer;
import org.gradle.internal.time.Timestamp;

public class ProgressCompleteEventSerializer implements Serializer<ProgressCompleteEvent> {
    private final Serializer<Timestamp> timestampSerializer;

    public ProgressCompleteEventSerializer(Serializer<Timestamp> timestampSerializer) {
        this.timestampSerializer = timestampSerializer;
    }

    @Override
    public void write(Encoder encoder, ProgressCompleteEvent event) throws Exception {
        encoder.writeSmallLong(event.getProgressOperationId().getId());
        timestampSerializer.write(encoder, event.getTime());
        encoder.writeString(event.getStatus());
        encoder.writeBoolean(event.isFailed());
    }

    @Override
    public ProgressCompleteEvent read(Decoder decoder) throws Exception {
        OperationIdentifier id = new OperationIdentifier(decoder.readSmallLong());
        Timestamp timestamp = timestampSerializer.read(decoder);
        String status = decoder.readString();
        boolean failed = decoder.readBoolean();
        return new ProgressCompleteEvent(id, timestamp, status, failed);
    }
}
