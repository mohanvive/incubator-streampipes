package de.fzi.cep.sepa.manager.execution.status;

import java.util.HashMap;
import java.util.Map;

import de.fzi.cep.sepa.manager.monitoring.runtime.PipelineObserver;
import de.fzi.cep.sepa.manager.monitoring.runtime.SepStoppedMonitoring;

public class SepMonitoringManager {

	public static SepStoppedMonitoring SepMonitoring;
	
	private static Map<String, PipelineObserver> observers;
	
	static {
		SepMonitoring = new SepStoppedMonitoring();
		observers = new HashMap<>();
		Thread thread = new Thread(SepMonitoring);
		thread.start();
	}
	
	public static void addObserver(String pipelineId) {
		PipelineObserver observer = new PipelineObserver(pipelineId);
		observers.put(pipelineId, observer);
		SepMonitoring.register(observer);
	}
	
	public static void removeObserver(String pipelineId) {
		SepMonitoring.remove(observers.get(pipelineId));
	}
	
}
