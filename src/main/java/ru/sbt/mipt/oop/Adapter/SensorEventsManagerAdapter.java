package ru.sbt.mipt.oop.Adapter;

import com.coolcompany.smarthome.events.EventHandler;
import com.coolcompany.smarthome.events.SensorEventsManager;

import ru.sbt.mipt.oop.EventProcessors.SensorEventProcessor;
import ru.sbt.mipt.oop.SensorEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SensorEventsManagerAdapter implements EventsManager {

    final private SensorEventsManager eventsManager;

    public SensorEventsManagerAdapter(List<SensorEventProcessor> eventProcessors) {
        this.eventsManager = new SensorEventsManager();

        Collection<EventHandler> handlers = new ArrayList<>();

        for (SensorEventProcessor eventProcessor : eventProcessors) {
            EventHandler handler = new EventProcessorAdapter(eventProcessor);
            ((ArrayList<EventHandler>) handlers).add(handler);
        }

        for (EventHandler handler : handlers){
            this.eventsManager.registerEventHandler(handler);
        }
    }

    @Override
    public void start() {
        this.eventsManager.registerEventHandler(
                stringEvent -> {
                    toString(stringEvent.getEventType(), stringEvent.getObjectId());
        });
        this.eventsManager.start();
    }

    @Override
    public void registerEventProcessor(SensorEventProcessor eventProcessor) {
        EventHandler handler = new EventProcessorAdapter(eventProcessor);
        this.eventsManager.registerEventHandler(handler);
    }


    public String toString(String type, String objectId) {
        return "SensorCommand{" +
                "type=" + type +
                ", objectId='" + objectId + '\'' +
                '}';
    }
}
