package ru.sbt.mipt.oop.Commands;

import ru.sbt.mipt.oop.AlarmService.Alarm;
import ru.sbt.mipt.oop.AlarmService.AlarmAlertState;
import ru.sbt.mipt.oop.AlarmService.AlarmState;
import ru.sbt.mipt.oop.SmartHome;

public class TurnAlertOnCommand implements Command {

    private SmartHome smartHome;

    public TurnAlertOnCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void executeCommand() {
        Alarm alarm = smartHome.getAlarm();
        AlarmState alertState = new AlarmAlertState(alarm);
        alarm.changeState(alertState);
        alarm.turnOnAlert();
    }
}
