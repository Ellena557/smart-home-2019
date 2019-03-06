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
                    if (light.getId().equals(event.getObjectId())) {
                        if (event.getType() == LIGHT_ON) {
                            changeCondition(room, light, true, "turned on.");
                        } else {
                            changeCondition(room, light, false, "turned off.");
                        }
                    }
                }
            }
        } else {
            // событие не от источника света
            return;
        }
    }

    private boolean sensorLightEvent(SensorEvent event){
        if (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF){
            return true;
        } else {
            return false;
        }
    }

    private void changeCondition(Room room, Light light, boolean condition, String cond){
        light.setOn(condition);
        System.out.println("Light " + light.getId() + " in room " + room.getName() + " was " + cond);
    }
}
