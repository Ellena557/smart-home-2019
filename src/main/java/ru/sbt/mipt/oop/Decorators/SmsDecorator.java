package ru.sbt.mipt.oop.Decorators;

import ru.sbt.mipt.oop.AlarmService.Alarm;
import ru.sbt.mipt.oop.AlarmService.AlarmDeactivatedState;
import ru.sbt.mipt.oop.EventProcessors.SensorEventProcessor;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;

import static ru.sbt.mipt.oop.SensorEventType.*;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;

public class SmsDecorator implements SensorEventProcessor {

    private SensorEventProcessor eventProcessor;

    private SmartHome smartHome;

    public SmsDecorator(SensorEventProcessor eventProcessor, SmartHome smartHome){
        this.eventProcessor = eventProcessor;
        this.smartHome = smartHome;
    }

    @Override
    public void processor(SensorEvent event) {
        Alarm alarm = smartHome.getAlarm();

        if(!isSensorEvent(event)) {
            // нужны только события со светом (включение, выключение) и дверями (открытие, закрытие)
            return;
        }

        if (alarm.getState() instanceof AlarmDeactivatedState){
            // все в порядке, смс отправлять не нужно
            // eventProcessor.processor(event);
            return;
        }
        else {

            // отправляем смс
            System.out.println("Sending sms");
            return;
        }
    }

    public boolean isSensorEvent(SensorEvent event){
        return (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED ||
                event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF);
    }
}
