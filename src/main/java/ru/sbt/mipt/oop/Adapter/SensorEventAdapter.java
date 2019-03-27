package ru.sbt.mipt.oop.Adapter;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.oop.EventProcessors.SensorEventProcessor;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SensorEventType;

import java.util.HashMap;
import java.util.Map;

public class SensorEventAdapter extends SensorEvent {

    private static HashMap<String, SensorEventType> eventAccordance = new HashMap<>();

    private final String[] eventTypes = new String[] { "LightIsOn", "LightIsOff",
            "DoorIsOpen", "DoorIsClosed", "DoorIsLocked", "DoorIsUnlocked" };

    public SensorEventAdapter(CCSensorEvent stringEvent) {
        super(determineType(stringEvent.getEventType()), stringEvent.getObjectId());
    }

    private static SensorEventType determineType(String event){
        SensorEventType eventType = null;

        switch (event){
            case "LightIsOn":
                eventType = SensorEventType.LIGHT_ON;
                return eventType;
            case "LightIsOff":
                eventType = SensorEventType.LIGHT_OFF;
                return eventType;
            case "DoorIsOpen":
                eventType = SensorEventType.DOOR_OPEN;
                return eventType;
            case "DoorIsClosed":
                eventType = SensorEventType.DOOR_CLOSED;
                return eventType;
            case "DoorIsLocked":
                eventType = SensorEventType.DOOR_LOCKED;
                return eventType;
            case "DoorIsUnlocked":
                eventType = SensorEventType.DOOR_UNLOCKED;
                return eventType;
        }

        return eventType;
    }

    private void convertVocab(){
        for (String stringEvent : eventTypes) {
            eventAccordance.put(stringEvent, determineType(stringEvent));
        }
    }

    public String getStringType(SensorEventType eventType){
        convertVocab();
        String stringEventType = null;

        for (Map.Entry<String, SensorEventType> eventTypes : eventAccordance.entrySet()) {
            if (eventTypes.getValue() == eventType){
                stringEventType = eventTypes.getKey();
            }
        }

        return stringEventType;
    }
}
