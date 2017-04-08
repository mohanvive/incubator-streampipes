package de.fzi.cep.sepa.flink.samples.count.aggregate;

import de.fzi.cep.sepa.client.util.StandardTransportFormat;
import de.fzi.cep.sepa.flink.AbstractFlinkAgentDeclarer;
import de.fzi.cep.sepa.flink.FlinkDeploymentConfig;
import de.fzi.cep.sepa.flink.FlinkSepaRuntime;
import de.fzi.cep.sepa.flink.samples.Config;
import de.fzi.cep.sepa.model.impl.EpaType;
import de.fzi.cep.sepa.model.impl.EventSchema;
import de.fzi.cep.sepa.model.impl.EventStream;
import de.fzi.cep.sepa.model.impl.eventproperty.EventProperty;
import de.fzi.cep.sepa.model.impl.eventproperty.EventPropertyPrimitive;
import de.fzi.cep.sepa.model.impl.graph.SepaDescription;
import de.fzi.cep.sepa.model.impl.graph.SepaInvocation;
import de.fzi.cep.sepa.model.impl.output.AppendOutputStrategy;
import de.fzi.cep.sepa.model.impl.output.OutputStrategy;
import de.fzi.cep.sepa.model.impl.quality.EventStreamQualityRequirement;
import de.fzi.cep.sepa.model.impl.quality.Frequency;
import de.fzi.cep.sepa.model.impl.staticproperty.FreeTextStaticProperty;
import de.fzi.cep.sepa.model.util.SepaUtils;
import de.fzi.cep.sepa.model.vocabulary.XSD;
import de.fzi.cep.sepa.sdk.builder.ProcessingElementBuilder;
import de.fzi.cep.sepa.sdk.helpers.EpProperties;
import de.fzi.cep.sepa.sdk.helpers.Options;
import de.fzi.cep.sepa.sdk.helpers.OutputStrategies;

import java.util.ArrayList;
import java.util.List;

public class CountAggregateController extends AbstractFlinkAgentDeclarer<CountAggregateParameters>{

	@Override
	public SepaDescription declareModel() {
		return ProcessingElementBuilder.create("count", "Flink Count Aggregation",
				"Performs an aggregation based on a given event property and outputs the number of occurrences.")
				.category(EpaType.AGGREGATE)
//						.iconUrl("")
				.setStream1()
				.naryMappingPropertyWithoutRequirement("groupBy", "Group Stream By", "")
				.outputStrategy(OutputStrategies.append(EpProperties.integerEp("countValue",
						"http://schema.org/Number")))
				.requiredIntegerParameter("timeWindow", "Time Window Size", "Size of the time window " +
						"in seconds")
				.requiredSingleValueSelection("scale", "Time Window Scale", "",
						Options.from("Hours", "Minutes", "Seconds"))
				.supportedFormats(StandardTransportFormat.standardFormat())
				.supportedProtocols(StandardTransportFormat.standardProtocols())
				.build();

	}

	@Override
	protected FlinkSepaRuntime<CountAggregateParameters> getRuntime(
			SepaInvocation sepa) {

		List<String> groupBy = SepaUtils.getMultipleMappingPropertyNames(sepa,
				"groupBy", true);

		int timeWindowSize = Integer.parseInt(((FreeTextStaticProperty) (SepaUtils
				.getStaticPropertyByInternalName(sepa, "timeWindow"))).getValue());

		String scale = SepaUtils.getOneOfProperty(sepa,
				"scale");

		TimeScale timeScale;

		if (scale.equals("Hours")) timeScale = TimeScale.HOURS;
		else if (scale.equals("Minutes")) timeScale = TimeScale.MINUTES;
		else timeScale = TimeScale.SECONDS;

		List<String> selectProperties = new ArrayList<>();
		for(EventProperty p : sepa.getInputStreams().get(0).getEventSchema().getEventProperties())
		{
			selectProperties.add(p.getRuntimeName());
		}

		CountAggregateParameters staticParam = new CountAggregateParameters(sepa, timeWindowSize, groupBy, timeScale, selectProperties);



//		return new CountAggregateProgram(staticParam, new FlinkDeploymentConfig(Config.JAR_FILE, Config.FLINK_HOST, Config.FLINK_PORT));
		return new CountAggregateProgram(staticParam);
	}

}