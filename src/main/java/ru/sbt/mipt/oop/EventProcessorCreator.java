package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.EventProcessors.DoorEventProcessor;
import ru.sbt.mipt.oop.EventProcessors.HallDoorScenarioProcessor;
import ru.sbt.mipt.oop.EventProcessors.LightEventProcessor;
import ru.sbt.mipt.oop.EventProcessors.SensorEventProcessor;

import java.util.ArrayList;
import java.util.Collection;

public class EventProcessorCreator {
    public static Collection<SensorEventProcessor> getNewEventProcessors() {
        Collection<SensorEventProcessor> eventProcessors = new ArrayList<>();

        eventProcessors.add(new DoorEventProcessor());
        eventProcessors.add(new HallDoorScenarioProcessor());
        eventProcessors.add(new LightEventProcessor());

        return eventProcessors;
    }
}
