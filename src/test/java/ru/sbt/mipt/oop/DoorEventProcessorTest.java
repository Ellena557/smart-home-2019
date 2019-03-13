package ru.sbt.mipt.oop;

import org.junit.Test;
import ru.sbt.mipt.oop.Components.Door;
import ru.sbt.mipt.oop.Components.Room;
import ru.sbt.mipt.oop.EventProcessors.DoorEventProcessor;

import java.io.IOException;

import static org.junit.Assert.*;

public class DoorEventProcessorTest {

    private HomeInitializer homeInitializer = new HomeJsonInitializer();

    @Test
    public void testOpeningDoorProcessor() throws IOException {
        SmartHome smartHome = homeInitializer.initializeHome();

        //Id of the kitchen
        String kitchenId = "1";

        SensorEvent doorOpened = new SensorEvent(SensorEventType.DOOR_OPEN, kitchenId);

        DoorEventProcessor openDoorProcessor = new DoorEventProcessor();
        openDoorProcessor.processor(smartHome, doorOpened);
        boolean openess = doorOpeness(getDoor(smartHome, kitchenId));

        assertTrue(openess);
    }

    @Test
    public void testClosingDoorProcessor() throws IOException {
        SmartHome smartHome = homeInitializer.initializeHome();

        //Id of the kitchen
        String kitchenId = "1";

        SensorEvent doorClosed = new SensorEvent(SensorEventType.DOOR_CLOSED, kitchenId);

        DoorEventProcessor closeDoorProcessor = new DoorEventProcessor();
        closeDoorProcessor.processor(smartHome, doorClosed);
        boolean openess = doorOpeness(getDoor(smartHome, kitchenId));

        assertFalse(openess);
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

    public static boolean doorOpeness(Door door){
        return door.isOpened();
    }
}