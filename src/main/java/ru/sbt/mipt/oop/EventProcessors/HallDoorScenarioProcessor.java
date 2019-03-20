package ru.sbt.mipt.oop.EventProcessors;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.Commands.CommandSender;
import ru.sbt.mipt.oop.Commands.CommandType;
import ru.sbt.mipt.oop.Commands.SensorCommand;
import ru.sbt.mipt.oop.Components.Door;
import ru.sbt.mipt.oop.Components.Light;
import ru.sbt.mipt.oop.Components.Room;

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
                        turnLightsOff(smartHome);
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

    private void turnLightsOff(SmartHome smartHome) {
        smartHome.execute(object ->
        {
            //check either we have reached Light or not
            if (object instanceof Light){
                Light light = (Light) object;
                light.setOn(false);
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                CommandSender.sendCommand(command);
            };
        });
    }
}
