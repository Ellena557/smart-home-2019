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
        SensorEventAdapter eventAdapter = new SensorEventAdapter();
        SensorEvent sensorEvent = eventAdapter.getEventTypeFromString(stringEvent);
        eventProcessor.processor(sensorEvent);
    }
}
