package de.fzi.cep.sepa.sources.samples.random;

import de.fzi.cep.sepa.client.declarer.EventStreamDeclarer;
import de.fzi.cep.sepa.commons.config.ClientConfiguration;
import de.fzi.cep.sepa.messaging.kafka.StreamPipesKafkaProducer;
import de.fzi.cep.sepa.model.impl.EventStream;
import de.fzi.cep.sepa.model.impl.graph.SepDescription;
import de.fzi.cep.sepa.sdk.builder.DataStreamBuilder;
import de.fzi.cep.sepa.sdk.helpers.EpProperties;
import de.fzi.cep.sepa.sdk.helpers.Formats;
import de.fzi.cep.sepa.sources.samples.config.SampleSettings;
import org.codehaus.jettison.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * Created by riemer on 28.01.2017.
 */
public class RandomNumberStreamList implements EventStreamDeclarer {

  private StreamPipesKafkaProducer kafkaProducer;

  private static final String topic = "de.fzi.random.number.list";

  @Override
  public EventStream declareModel(SepDescription sep) {

    return DataStreamBuilder.create("random-list", "Random Number (List)", "Random number stream " +
            "to test list-based properties")
            .format(Formats.jsonFormat())
            .protocol(SampleSettings.kafkaProtocol(topic))
            .property(EpProperties.integerEp("timestamp", "http://schema.org/DateTime"))
            .property(EpProperties.integerEp("counter", "http://schema.org/counter"))
            .property(EpProperties.listIntegerEp("values", "http://schema.org/random"))
            .build();
  }

  @Override
  public void executeStream() {
    kafkaProducer = new StreamPipesKafkaProducer(ClientConfiguration.INSTANCE.getKafkaUrl(), topic);

    Runnable r = new Runnable() {

      @Override
      public void run() {
        Random random = new Random();
        int j = 0;
        for (;;) {
          try {
            if (j % 10000 == 0) {
              System.out.println(j +" Events (Random Number) sent.");
            }
            Optional<byte[]> nextMsg = getMessage(System.currentTimeMillis(), j);
            if (nextMsg.isPresent()) kafkaProducer.publish(nextMsg.get());
            Thread.sleep(1000);
            j++;
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
    };
    Thread thread = new Thread(r);
    thread.start();

  }

  private Optional<byte[]> getMessage(long timestamp, int counter) {
    try {
      JSONObject json = new JSONObject();
      json.put("timestamp", timestamp);
      json.put("counter", counter);
      json.put("values", buildRandomList());

      return Optional.of(json.toString().getBytes());
    } catch (Exception e) {
      return Optional.empty();
    }
  }

  private List<Integer> buildRandomList() {
    Random random = new Random();
    List<Integer> result = new ArrayList<>();
    for(int i = 0; i <= 20; i++) {
      result.add(random.nextInt());
    }
    return result;
  }

  @Override
  public boolean isExecutable() {
    return true;
  }
}