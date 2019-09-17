/*
 * Copyright 2018 FZI Forschungszentrum Informatik
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
 *
 */

package org.streampipes.connect.adapter.format.json.arraynokey;


import org.streampipes.connect.adapter.model.generic.Format;
import org.streampipes.connect.adapter.format.json.AbstractJsonFormat;
import org.streampipes.model.connect.grounding.FormatDescription;
import org.streampipes.sdk.builder.adapter.FormatDescriptionBuilder;

public class JsonArrayFormat extends AbstractJsonFormat {

    public static final String ID = "https://streampipes.org/vocabulary/v1/format/json/arraynokey";

    @Override
    public Format getInstance(FormatDescription formatDescription) {
        return new JsonArrayFormat();
    }

    @Override
    public FormatDescription declareModel() {
        return FormatDescriptionBuilder.create(ID, "Json Array No Key", "Transforms all objects within array to events")
                .build();
    }

    @Override
    public String getId() {
        return ID;
    }


}