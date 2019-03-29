package ru.sbt.mipt.oop.Commands;

import ru.sbt.mipt.oop.Components.Door;
import ru.sbt.mipt.oop.Components.Light;
import ru.sbt.mipt.oop.Components.Room;
import ru.sbt.mipt.oop.SmartHome;

import java.util.Collection;
import java.util.List;

public class TurnOnHallLightCommand implements Command {

    private SmartHome smartHome;

    public TurnOnHallLightCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void executeCommand() {
        smartHome.execute(obj -> {
            if(obj instanceof Room && ((Room) obj).getName().equals("hall")){
                Collection<Light> lights = ((Room) obj).getLights();
                for (Light light : lights){
                    light.setOn(true);
                }
            }
        });
    }
}
