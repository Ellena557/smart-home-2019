package ru.sbt.mipt.oop.Commands;

import ru.sbt.mipt.oop.Components.Door;
import ru.sbt.mipt.oop.Components.Room;
import ru.sbt.mipt.oop.SmartHome;

import java.util.Collection;

public class CloseHallDoorCommand implements Command {

    private SmartHome smartHome;

    public CloseHallDoorCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void executeCommand() {
        smartHome.execute(obj -> {
            if(obj instanceof Room && ((Room) obj).getName().equals("hall")){
                Collection<Door> doors = ((Room) obj).getDoors();
                for (Door door : doors){
                    door.setOpen(false);
                }
            }
        });
    }
}
