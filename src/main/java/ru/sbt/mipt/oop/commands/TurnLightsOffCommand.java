package ru.sbt.mipt.oop.commands;

import ru.sbt.mipt.oop.Components.Light;
import ru.sbt.mipt.oop.Components.Room;
import ru.sbt.mipt.oop.SmartHome;

public class TurnLightsOffCommand implements Command {

    private final SmartHome smartHome;

    public TurnLightsOffCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void executeCommand() {
        for (Room homeRoom : smartHome.getRooms()) {
            for (Light light : homeRoom.getLights()) {
                light.setOn(false);
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                CommandSender.sendCommand(command);
            }
        }
    }
}
