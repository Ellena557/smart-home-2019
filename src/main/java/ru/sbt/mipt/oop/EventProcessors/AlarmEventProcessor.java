package ru.sbt.mipt.oop.EventProcessors;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;

import static ru.sbt.mipt.oop.SensorEventType.ALARM_ACTIVATE;
import static ru.sbt.mipt.oop.SensorEventType.ALARM_DEACTIVATE;

public class AlarmEventProcessor implements SensorEventProcessor {

    @Override
    public void processor(SmartHome smartHome, SensorEvent event) {
        if (isAlarmEvent(event)){
            if (event.getType() == ALARM_ACTIVATE){
                String currentCode = ALARM_ACTIVATE.getCode();
                smartHome.activateAlarm(currentCode);
            } else {
                String currentCode = ALARM_DEACTIVATE.getCode();
                smartHome.deactivateAlarm(currentCode);
            }
        }
    }

    public boolean isAlarmEvent(SensorEvent event){
        return (event.getType() == ALARM_ACTIVATE || event.getType() == ALARM_DEACTIVATE);
    }
}
