package org.streampipes.manager.pipeline;

import org.streampipes.manager.operations.Operations;
import org.streampipes.model.client.pipeline.Pipeline;
import org.streampipes.storage.management.StorageDispatcher;

public class StopPipeline {

    public static void main(String[] args) {
        Pipeline pipeline = StorageDispatcher.INSTANCE.getNoSqlStore().getPipelineStorageAPI().getPipeline
                ("2720e901-73d8-4d9e-8508-6a89551fe6fe");

        Operations.stopPipeline(pipeline);
    }
}
