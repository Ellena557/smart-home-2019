package ru.sbt.mipt.oop.Adapter;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.oop.EventProcessors.SensorEventProcessor;
import ru.sbt.mipt.oop.SensorEvent;

public class EventProcessorAdapter implements EventHandler {

    private SensorEventProcessor eventProcessor;

    public EventProcessorAdapter(SensorEventProcessor sensorEventProcessor) {
        this.eventProcessor = sensorEventProcessor;
    }

    @Override
    public void handleEvent(CCSensorEvent stringEvent){
        //String stringEventType = stringEvent.getEventType();
        SensorEvent sensorEvent = new SensorEventAdapter(stringEvent);
        eventProcessor.processor(sensorEvent);
    }
}
