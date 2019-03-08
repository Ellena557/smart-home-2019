package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class DoorEventProcessor implements SensorEventProcessor {

    private SmartHome smartHome;

    @Override
    public void processor(SmartHome smartHome, SensorEvent event) {

        // событие от двери
        if (sensorDoorEvent(event)) {
            for (Room room : smartHome.getRooms()) {
                for (Door door : room.getDoors()) {
                    doorProcessing(room, door, event);
                }
            }
        }
    }

    private void doorProcessing(Room room, Door door, SensorEvent event){
        if (door.getId().equals(event.getObjectId())) {
            if (event.getType() == DOOR_OPEN) {
                changeCondition(room, door, true, "opened.");
            } else {
                changeCondition(room, door, false, "closed.");
            }
        }
    }

    private boolean sensorDoorEvent(SensorEvent event){
        return (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED);
    }

    private void changeCondition(Room room, Door door, boolean condition, String openess){
        door.setOpen(condition);
        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was " + openess);
    }
}
