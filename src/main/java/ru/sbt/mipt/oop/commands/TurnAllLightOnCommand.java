package ru.sbt.mipt.oop.Commands;

import ru.sbt.mipt.oop.Components.Light;
import ru.sbt.mipt.oop.SmartHome;

public class TurnAllLightOnCommand implements Command {

    private SmartHome smartHome;

    public TurnAllLightOnCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void executeCommand() {
        smartHome.execute(object ->
        {
            //check either we have reached Light or not
            if (object instanceof Light){
                Light light = (Light) object;
                light.setOn(true);
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                CommandSender.sendCommand(command);
            };
        });
    }
}
