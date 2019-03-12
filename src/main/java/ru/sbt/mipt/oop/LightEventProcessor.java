package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class LightEventProcessor implements SensorEventProcessor {

    private SmartHome smartHome;

    @Override
    public void processor(SmartHome smartHome, SensorEvent event) {

        // событие от источника света
        if (sensorLightEvent(event)) {
            for (Room room : smartHome.getRooms()) {
                for (Light light : room.getLights()) {
                    lightProcessing(room, light, event);
                }
            }
        }
    }

    private void lightProcessing(Room room, Light light, SensorEvent event){
        if (light.getId().equals(event.getObjectId())) {
            if (event.getType() == LIGHT_ON) {
                changeCondition(room, light, true, "turned on.");
            } else {
                changeCondition(room, light, false, "turned off.");
            }
        }
    }

    private boolean sensorLightEvent(SensorEvent event){
        return (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF);
    }

    private void changeCondition(Room room, Light light, boolean condition, String cond){
        light.setOn(condition);
        System.out.println("Light " + light.getId() + " in room " + room.getName() + " was " + cond);
    }
}
