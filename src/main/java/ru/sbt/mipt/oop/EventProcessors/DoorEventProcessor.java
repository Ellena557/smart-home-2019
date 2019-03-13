package ru.sbt.mipt.oop.EventProcessors;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.Components.Door;
import ru.sbt.mipt.oop.Components.Light;
import ru.sbt.mipt.oop.Components.Room;

import java.util.Arrays;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class DoorEventProcessor implements SensorEventProcessor {

    private SmartHome smartHome;

    @Override
    public void processor(SmartHome smartHome, SensorEvent event) {

        // to check the class of the element (either it is Door or not)
        Door helpDoor = new Door(true, "1");

        Class parentClass = smartHome.getClass();
        Room helpRoom = new Room(Arrays.asList(new Light("1", false), new Light("2", true)),
                Arrays.asList(new Door(false, "1")),
                "kitchen");

        //to remember room
        Object[] array = new Object[2];
        array[0] = helpRoom;

        // событие от двери
        if (sensorDoorEvent(event))
            smartHome.execute(object ->
            {
                //check either we have reached Door or not
                if (object.getClass() == helpDoor.getClass()){
                    //System.out.println(object.getClass());
                    doorProcessing((Door)object, event);
                };

                //remember room
                if (object.getClass() == helpRoom.getClass()){
                    array[0] = object;
                };
            });
    }

    private void doorProcessing(Door door, SensorEvent event){
        if (door.getId().equals(event.getObjectId())) {
            if (event.getType() == DOOR_OPEN) {
                changeCondition(door, true, "opened.");
            } else {
                changeCondition(door, false, "closed.");
            }
        }
    }

    private boolean sensorDoorEvent(SensorEvent event){
        return (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED);
    }

    private void changeCondition(Door door, boolean condition, String openess){
        door.setOpen(condition);
        System.out.println("Door " + door.getId() + " was " + openess);
    }
}
