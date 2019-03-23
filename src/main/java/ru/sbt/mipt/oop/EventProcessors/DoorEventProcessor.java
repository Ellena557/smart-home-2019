package ru.sbt.mipt.oop.EventProcessors;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.Components.Door;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class DoorEventProcessor implements SensorEventProcessor {

    private SmartHome smartHome;

    public DoorEventProcessor(SmartHome smartHome){
        this.smartHome = smartHome;
    }

    @Override
    public void processor(SensorEvent event) {

        // событие от двери
        if (sensorDoorEvent(event))
            smartHome.execute(object ->
            {
                //check either we have reached Door or not
                if (object instanceof Door){
                    doorProcessing((Door)object, event);
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
