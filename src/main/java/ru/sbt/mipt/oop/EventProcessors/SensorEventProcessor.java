package ru.sbt.mipt.oop.EventProcessors;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;

public interface SensorEventProcessor {

    void processor(SmartHome smartHome, SensorEvent event);
}
