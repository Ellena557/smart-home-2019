package ru.sbt.mipt.oop.Adapter;

import ru.sbt.mipt.oop.EventProcessors.SensorEventProcessor;

public interface EventsManager {
    void start();
    void registerEventProcessor(SensorEventProcessor eventProcessor);
}
