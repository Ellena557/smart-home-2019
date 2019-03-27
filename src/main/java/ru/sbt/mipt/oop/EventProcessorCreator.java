package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.Decorators.AlarmDecorator;
import ru.sbt.mipt.oop.Decorators.SmsDecorator;
import ru.sbt.mipt.oop.EventProcessors.*;

import java.util.ArrayList;
import java.util.Collection;

public class EventProcessorCreator {

    public static Collection<SensorEventProcessor> getNewEventProcessors(SmartHome smartHome) {
        Collection<SensorEventProcessor> eventProcessors = new ArrayList<>();

        eventProcessors.add(new AlarmEventProcessor(smartHome));

        eventProcessors.add(new AlarmDecorator(new DoorEventProcessor(smartHome), smartHome));
        eventProcessors.add(new AlarmDecorator(new HallDoorScenarioProcessor(smartHome), smartHome));
        eventProcessors.add(new AlarmDecorator(new LightEventProcessor(smartHome), smartHome));

        eventProcessors.add(new SmsDecorator(new DoorEventProcessor(smartHome), smartHome));
        eventProcessors.add(new SmsDecorator(new HallDoorScenarioProcessor(smartHome), smartHome));
        eventProcessors.add(new SmsDecorator(new LightEventProcessor(smartHome), smartHome));
        return eventProcessors;
    }
}
