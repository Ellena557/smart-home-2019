package ru.sbt.mipt.oop;

import org.junit.Test;
import ru.sbt.mipt.oop.Components.Light;
import ru.sbt.mipt.oop.Components.Room;
import ru.sbt.mipt.oop.EventProcessors.LightEventProcessor;

import java.io.IOException;

import static org.junit.Assert.*;

public class LightEventProcessorTest {
    private HomeInitializer homeInitializer = new HomeJsonInitializer();

    @Test
    public void testOnLightProcessor() throws IOException {
        SmartHome smartHome = homeInitializer.initializeHome();

        //Id of the kitchen Light
        String kitchenLightId = "1";

        SensorEvent lightOn = new SensorEvent(SensorEventType.LIGHT_ON, kitchenLightId);

        LightEventProcessor lightOnProcessor = new LightEventProcessor(smartHome);
        lightOnProcessor.processor(lightOn);
        boolean lightState = (getLight(smartHome, kitchenLightId)).isOn();

        assertTrue(lightState);
    }

    @Test
    public void testOffLightProcessor() throws IOException {
        SmartHome smartHome = homeInitializer.initializeHome();

        //Id of the kitchen Light
        String kitchenLightId = "1";

        SensorEvent lightOff = new SensorEvent(SensorEventType.LIGHT_OFF, kitchenLightId);

        LightEventProcessor lightOffProcessor = new LightEventProcessor(smartHome);
        lightOffProcessor.processor(lightOff);
        boolean lightState = (getLight(smartHome, kitchenLightId)).isOn();

        assertFalse(lightState);
    }


    public static Light getLight (SmartHome smartHome, String lightId){
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(lightId)){
                    return light;
                }
            }
        }
        return null;
    }
}

