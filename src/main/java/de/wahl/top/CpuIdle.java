package de.wahl.top;

import java.util.function.Consumer;

import jdk.jfr.consumer.RecordingStream;

public class CpuIdle {
    private static final String JDK_CPU_LOAD = "jdk.CPULoad";

	public static void main(String[] args) {
    	watch(idleRounded -> System.out.printf("System Idle: %d %%%n", idleRounded));
    }

	public static void watch(Consumer<Long> doOnEvent) {
		try (var rs = new RecordingStream()) {
    	    rs.enable(JDK_CPU_LOAD).withPeriod(java.time.Duration.ofSeconds(1));
    	    rs.onEvent(JDK_CPU_LOAD, event -> {
    	        float totalLoad = event.getFloat("machineTotal");
                double idleDouble = (1.0 - totalLoad) * 100;
                long idleRounded = Math.round(idleDouble);
                doOnEvent.accept(idleRounded);
    	    });
    	    rs.start();
    	}
	}
}