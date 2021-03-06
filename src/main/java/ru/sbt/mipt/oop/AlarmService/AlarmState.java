package ru.sbt.mipt.oop.AlarmService;

public interface AlarmState {
    void activateAlarm(String enteredCode);

    void deactivateAlarm(String enteredCode);

    void turnOnAlert();

    AlarmState getCurrentState();
}
