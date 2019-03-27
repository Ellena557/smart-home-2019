package ru.sbt.mipt.oop.Decorators;

import ru.sbt.mipt.oop.AlarmService.Alarm;
import ru.sbt.mipt.oop.AlarmService.AlarmAlertState;
import ru.sbt.mipt.oop.AlarmService.AlarmDeactivatedState;
import ru.sbt.mipt.oop.EventProcessors.SensorEventProcessor;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;

import static ru.sbt.mipt.oop.SensorEventType.*;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;

public class AlarmDecorator implements SensorEventProcessor {

    private SensorEventProcessor eventProcessor;

    private SmartHome smartHome;

    public AlarmDecorator(SensorEventProcessor eventProcessor, SmartHome smartHome){
        this.eventProcessor = eventProcessor;
        this.smartHome = smartHome;
    }

    @Override
    public void processor(SensorEvent event) {
        Alarm alarm = smartHome.getAlarm();

        if (alarm.getState() instanceof AlarmAlertState){

            // режим тревоги, события не обрабатываются
            return;
        }
        else if (alarm.getState() instanceof AlarmDeactivatedState){

            // сигнализация деактивирована, обрабатываем событие
            eventProcessor.processor(event);
        }
        else {
            // обрабатываем событие, так как тревоги еще нет
            eventProcessor.processor(event);

            // посылаем сигнал тревоги
            if (isSensorEvent(event)) {
                // открыли/закрыли дверь или включили/выключили свет
                alarm.turnOnAlert();
            }
        }
    }

    public boolean isSensorEvent(SensorEvent event){
        return (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED ||
                event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF);
    }
}
