package ru.sbt.mipt.oop.Commands;

import ru.sbt.mipt.oop.AlarmService.Alarm;
import ru.sbt.mipt.oop.SmartHome;

public class TurnAlarmOnCommand implements Command {

    private SmartHome smartHome;
    private String code;

    public TurnAlarmOnCommand(SmartHome smartHome, String code) {
        this.smartHome = smartHome;
        this.code = code;
    }

    @Override
    public void executeCommand() {
        Alarm alarm = smartHome.getAlarm();
        alarm.activateAlarm(code);
    }
}
