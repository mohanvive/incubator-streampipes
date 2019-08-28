/*
Copyright 2019 FZI Forschungszentrum Informatik

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package org.streampipes.wrapper.standalone.runtime;

import org.streampipes.commons.exceptions.SpRuntimeException;
import org.streampipes.model.graph.DataSinkInvocation;
import org.streampipes.wrapper.context.EventSinkRuntimeContext;
import org.streampipes.wrapper.params.binding.EventSinkBindingParams;
import org.streampipes.wrapper.params.runtime.EventSinkRuntimeParams;
import org.streampipes.wrapper.runtime.ExternalEventSink;

import java.util.Map;
import java.util.function.Supplier;

public class StandaloneExternalEventSinkRuntime<B extends EventSinkBindingParams> extends
        StandalonePipelineElementRuntime<B, DataSinkInvocation,
                EventSinkRuntimeParams<B>, EventSinkRuntimeContext, ExternalEventSink<B>> {

  public StandaloneExternalEventSinkRuntime(Supplier<ExternalEventSink<B>> supplier, EventSinkRuntimeParams<B> runtimeParams) {
    super(supplier, runtimeParams);
  }

  @Override
  public void bindEngine() throws SpRuntimeException {
    engine.onInvocation(params.getBindingParams(), params.getRuntimeContext());
  }

  @Override
  public void process(Map<String, Object> rawEvent, String sourceInfo) throws SpRuntimeException {

  }

  @Override
  public void prepareRuntime() throws SpRuntimeException {

  }

  @Override
  public void postDiscard() throws SpRuntimeException {

  }

  @Override
  public void bindRuntime() throws SpRuntimeException {
    bindEngine();
  }

  @Override
  public void discardRuntime() throws SpRuntimeException {
    discardEngine();
    postDiscard();
  }
}
