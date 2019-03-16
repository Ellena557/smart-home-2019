package ru.sbt.mipt.oop.EventProcessors;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.Components.Door;
import ru.sbt.mipt.oop.Components.Room;
import ru.sbt.mipt.oop.commands.TurnLightsOffCommand;

import java.util.Collection;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;

public class HallDoorScenarioProcessor implements SensorEventProcessor {

    @Override
    public void processor(SmartHome smartHome, SensorEvent event) {

        // событие от закрытой двери
        if (sensorDoorClosedEvent(event))
            smartHome.execute(object ->
            {
                //check either we have reached Door or not
                if (object instanceof Door){
                    if (hallDoorScenarioEvent(smartHome, event, (Door) object )) {
                        TurnLightsOffCommand lightsOffCommand = new TurnLightsOffCommand(smartHome);
                        lightsOffCommand.executeCommand();
                    }
                };
            });

    }

    private boolean hallDoorScenarioEvent(SmartHome smartHome, SensorEvent event, Door door){

    boolean isHallDoor = false;

    for (Room room : smartHome.getRooms()) {

        if (room.getName().equals("hall")) {
            Collection<Door> doors = room.getDoors();
            isHallDoor = doorsHaveOurs(doors, door);
        }
    }

    return isHallDoor;
    }

    private boolean doorsHaveOurs(Collection<Door> doors, Door door){
        boolean has = false;

        for (Door oneDoor : doors){
            if (oneDoor.getId().equals(door.getId())){
                has = true;
            }
        }
        return  has;
    }

    private boolean sensorDoorClosedEvent(SensorEvent event){
        return (event.getType() == DOOR_CLOSED);
    }
}
