package ru.sbt.mipt.oop;

import org.junit.Test;
import ru.sbt.mipt.oop.Components.Door;
import ru.sbt.mipt.oop.Components.Light;
import ru.sbt.mipt.oop.Components.Room;
import ru.sbt.mipt.oop.EventProcessors.HallDoorScenarioProcessor;

import java.io.IOException;

import static org.junit.Assert.*;

public class HallDoorScenarioProcessorTest {

    private HomeInitializer homeInitializer = new HomeJsonInitializer();

    @Test
    public void testHallDoorClosed() throws IOException {
        SmartHome smartHome = homeInitializer.initializeHome();

        String hallDoorId = hallDoorId(smartHome);

        SensorEvent hallDoorClosed = new SensorEvent(SensorEventType.DOOR_CLOSED, hallDoorId);

        HallDoorScenarioProcessor closeHallDoorProcessor = new HallDoorScenarioProcessor();
        closeHallDoorProcessor.processor(smartHome, hallDoorClosed);

        boolean openess = doorOpeness(getDoor(smartHome, hallDoorId));

        assertFalse(openess);
    }

    @Test
    public void testLightsOff() throws IOException {
        SmartHome smartHome = homeInitializer.initializeHome();
        String hallDoorId = hallDoorId(smartHome);

        SensorEvent hallDoorClosed = new SensorEvent(SensorEventType.DOOR_CLOSED, hallDoorId);

        HallDoorScenarioProcessor closeHallDoorProcessor = new HallDoorScenarioProcessor();
        closeHallDoorProcessor.processor(smartHome, hallDoorClosed);

        boolean lightsOff = checkLightsAreOff(smartHome);

        assertTrue(lightsOff);

    }

    public static Door getDoor (SmartHome smartHome, String doorId){
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(doorId)){
                    return door;
                }
            }
        }
        return null;
    }

    public static String hallDoorId(SmartHome smartHome){
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (room.getName().equals("hall")){
                    return door.getId();
                }
            }
        }
        return null;
    }

    public static boolean doorOpeness(Door door){
        return door.isOpened();
    }

    public boolean checkLightsAreOff(SmartHome smartHome){
        boolean lightsOff = true;

        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.isOn()){
                    lightsOff = false;
                }
            }
        }

        return lightsOff;
    }
}