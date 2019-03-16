package ru.sbt.mipt.oop.EventProcessors;

import ru.sbt.mipt.oop.Components.Light;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class LightEventProcessor implements SensorEventProcessor {

    private SmartHome smartHome;

    @Override
    public void processor(SmartHome smartHome, SensorEvent event) {

        // событие от источника света
        if (sensorLightEvent(event))
            smartHome.execute(object ->
            {
                //check either we have reached Light or not
                if (object instanceof Light){
                    lightProcessing((Light) object, event);
                };
            });
    }

    private void lightProcessing(Light light, SensorEvent event){
        if (light.getId().equals(event.getObjectId())) {
            if (event.getType() == LIGHT_ON) {
                changeCondition(light, true, "turned on.");
            } else {
                changeCondition(light, false, "turned off.");
            }
        }
    }

    private boolean sensorLightEvent(SensorEvent event){
        return (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF);
    }

    private void changeCondition(Light light, boolean condition, String cond){
        light.setOn(condition);
        System.out.println("Light " + light.getId()  + " was " + cond);
    }
}
