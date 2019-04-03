package ru.sbt.mipt.oop.Adapter;

import com.coolcompany.smarthome.events.EventHandler;
import com.coolcompany.smarthome.events.SensorEventsManager;

import ru.sbt.mipt.oop.EventProcessors.SensorEventProcessor;
import java.util.List;

public class SensorEventsManagerAdapter implements EventsManager {

    final private SensorEventsManager eventsManager;

    public SensorEventsManagerAdapter(List<SensorEventProcessor> eventProcessors) {
        this.eventsManager = new SensorEventsManager();

        eventProcessors.forEach(processor -> registerEventProcessor(processor));
    }

    @Override
    public void start() {
        this.eventsManager.registerEventHandler(
                stringEvent -> {
                    System.out.println("Event type [" + stringEvent.getEventType() +
                            "] from object with id=" + stringEvent.getObjectId() + "]");
        });
        this.eventsManager.start();
    }

    @Override
    public void registerEventProcessor(SensorEventProcessor eventProcessor) {
        EventHandler handler = new EventProcessorAdapter(eventProcessor);
        this.eventsManager.registerEventHandler(handler);
    }
}
