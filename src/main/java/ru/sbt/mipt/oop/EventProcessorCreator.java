package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.*;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;

public class EventProcessorCreator {
    public static SensorEventProcessor getNewEventProcessor(SmartHome smartHome, SensorEvent event) {
        if (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF) {
            LightEventProcessor lightProcessor = new LightEventProcessor();
            return lightProcessor;
        }
        if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
            DoorEventProcessor doorProcessor = new DoorEventProcessor();
            return doorProcessor;
        }
        else{
            return null;
        }
    }
}
