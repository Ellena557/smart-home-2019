package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;

public class HallDoorScenarioProcessor implements SensorEventProcessor {

    @Override
    public void processor(SmartHome smartHome, SensorEvent event) {
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(event.getObjectId())) {
                    if (hallDoorScenarioEvent(event, room)) {
                        smartHome.turnLightsOff();
                    }
                }
            }
        }
    }

    private boolean hallDoorScenarioEvent(SensorEvent event, Room room){
        return room.getName().equals("hall") && event.getType() == DOOR_CLOSED;
    }
}
