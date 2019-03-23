package ru.sbt.mipt.oop.EventCreators;

import ru.sbt.mipt.oop.SensorEvent;

public interface SensorEventCreator {
    public SensorEvent getNextSensorEvent();
}
