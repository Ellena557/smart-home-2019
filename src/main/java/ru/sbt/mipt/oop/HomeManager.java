package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.Adapter.EventsManager;
import ru.sbt.mipt.oop.EventCreators.RandomSensorEventCreator;
import ru.sbt.mipt.oop.EventCreators.SensorEventCreator;
import ru.sbt.mipt.oop.EventProcessors.SensorEventProcessor;

import java.util.List;

public class HomeManager implements EventsManager {
    private List<SensorEventProcessor> eventProcessors;

    public HomeManager(List<SensorEventProcessor> eventProcessors) {
        this.eventProcessors = eventProcessors;
    }

    @Override
    public void start() {
        SensorEventCreator eventCreator = new RandomSensorEventCreator();
        SensorEvent event = eventCreator.getNextSensorEvent();

        while (event != null) {
            System.out.println("Got event: " + event);

            for (SensorEventProcessor oneProcessor : eventProcessors) {
                oneProcessor.processor(event);
            }

            event = eventCreator.getNextSensorEvent();
        }
    }

    @Override
    public void registerEventProcessor(SensorEventProcessor eventProcessor) {
        eventProcessors.add(eventProcessor);
    }
}
