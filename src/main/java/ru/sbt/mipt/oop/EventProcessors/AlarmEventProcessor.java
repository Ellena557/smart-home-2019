package ru.sbt.mipt.oop.EventProcessors;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;

import static ru.sbt.mipt.oop.SensorEventType.ALARM_ACTIVATE;
import static ru.sbt.mipt.oop.SensorEventType.ALARM_DEACTIVATE;

public class AlarmEventProcessor implements SensorEventProcessor {

    private final SmartHome smartHome;

    public AlarmEventProcessor(SmartHome smartHome){
        this.smartHome = smartHome;
    }

    @Override
    public void processor(SensorEvent event) {
        if (isAlarmEvent(event)){
            if (event.getType() == ALARM_ACTIVATE){
                String currentCode = ALARM_ACTIVATE.getCode();
                smartHome.getAlarm().activateAlarm(currentCode);
            } else {
                String currentCode = ALARM_DEACTIVATE.getCode();
                smartHome.getAlarm().deactivateAlarm(currentCode);
            }
        }
    }

    public boolean isAlarmEvent(SensorEvent event){
        return (event.getType() == ALARM_ACTIVATE || event.getType() == ALARM_DEACTIVATE);
    }
}
