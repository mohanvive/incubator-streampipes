/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.apache.streampipes.serializers.json;

import org.apache.streampipes.model.template.PipelineElementTemplate;
import org.apache.streampipes.serializers.utils.PipelineElementTemplateHelpers;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestGsonSerializer {
  @Test
  public void testPipelineElementTemplateSerialization() {
    PipelineElementTemplate template = PipelineElementTemplateHelpers.makePipelineElementTemplate();

    String json = GsonSerializer.getGsonWithIds().toJson(template);
    PipelineElementTemplate template2 = GsonSerializer.getGsonWithIds().fromJson(json, PipelineElementTemplate.class);
    PipelineElementTemplateHelpers.assertions(template2);
    assertEquals(2.0, template2.getTemplateConfigs().get("test-key-2").getValue());
  }
}
