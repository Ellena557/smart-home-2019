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
                    if (door.getId().equals(event.getObjectId())) {
                        if (event.getType() == DOOR_OPEN) {
                            changeCondition(room, door, true, "opened.");
                        } else {
                            changeCondition(room, door, false, "closed.");

                            // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
                            // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)

                            if (room.getName().equals("hall")) {
                                for (Room homeRoom : smartHome.getRooms()) {
                                    for (Light light : homeRoom.getLights()) {
                                        light.setOn(false);
                                        SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                                        sendCommand(command);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else{
            // событие не от двери
            return;
        }
    }

    private boolean sensorDoorEvent(SensorEvent event){
        if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED){
            return true;
        } else {
            return false;
        }
    }

    private static void sendCommand(SensorCommand command) {
        System.out.println("Pretent we're sending command " + command);
    }

    private void changeCondition(Room room, Door door, boolean condition, String openess){
        door.setOpen(condition);
        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was " + openess);
    }
}
