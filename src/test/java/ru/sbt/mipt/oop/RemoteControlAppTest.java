package ru.sbt.mipt.oop;

import org.junit.Test;
import rc.RemoteControlRegistry;
import ru.sbt.mipt.oop.Commands.Command;
import ru.sbt.mipt.oop.Commands.TurnAllLightOffCommand;
import ru.sbt.mipt.oop.Commands.TurnAllLightOnCommand;
import ru.sbt.mipt.oop.Components.Light;
import ru.sbt.mipt.oop.Components.Room;
import ru.sbt.mipt.oop.EventProcessors.LightEventProcessor;
import ru.sbt.mipt.oop.EventProcessors.SensorEventProcessor;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;

import static org.junit.Assert.*;

public class RemoteControlAppTest {
    @Test
    public void CorrectOnButtonPressedTest() throws IOException {
        HomeInitializer homeInitializer = new HomeJsonInitializer();
        SmartHome smartHome = homeInitializer.initializeHome();
        Command command = new TurnAllLightOnCommand(smartHome);
        RemoteControlRegistry remoteControlRegistry = new RemoteControlRegistry();
        RemoteControlApp remoteControlApp = new RemoteControlApp(remoteControlRegistry);

        remoteControlApp.addCommand("C", command);

        remoteControlApp.onButtonPressed("C", "1");
        boolean lightOn = true;

        for (Room room : smartHome.getRooms()){
            for (Light light : room.getLights()){
                if (!light.isOn()){
                    lightOn = false;
                }
            }
        }

        assertEquals(lightOn, true);
    }

    @Test
    public void IncorrectOnButtonPressedTest() throws IOException {
        HomeInitializer homeInitializer = new HomeJsonInitializer();
        SmartHome smartHome = homeInitializer.initializeHome();
        RemoteControlRegistry remoteControlRegistry = new RemoteControlRegistry();
        RemoteControlApp remoteControlApp = new RemoteControlApp(remoteControlRegistry);

        Command command = new TurnAllLightOffCommand(smartHome);

        SensorEventProcessor lightProcessor = new LightEventProcessor(smartHome);
        String kitchenLightId = "1";
        SensorEvent lightOn = new SensorEvent(SensorEventType.LIGHT_ON, kitchenLightId);
        lightProcessor.processor(lightOn);

        remoteControlApp.addCommand("5", command);

        remoteControlApp.onButtonPressed("5", "1");
        boolean isLightOff = true;

        for (Room room : smartHome.getRooms()){
            for (Light light : room.getLights()){
                if (light.isOn()){
                    isLightOff = false;
                }
            }
        }

        assertEquals(isLightOff, false);
    }

    @Test
    public void addCommandTest() throws IOException, NoSuchFieldException, IllegalAccessException {
        HomeInitializer homeInitializer = new HomeJsonInitializer();
        SmartHome smartHome = homeInitializer.initializeHome();

        RemoteControlRegistry remoteControlRegistry = new RemoteControlRegistry();
        RemoteControlApp remoteControlApp = new RemoteControlApp(remoteControlRegistry);

        Command command1 = new TurnAllLightOffCommand(smartHome);
        Command command2 = new TurnAllLightOnCommand(smartHome);

        remoteControlApp.addCommand("C", command1);
        remoteControlApp.addCommand("L", command2);

        Field buttonsField = RemoteControlApp.class.getDeclaredField("buttonsVocab");
        buttonsField.setAccessible(true);
        HashMap<String, Command> buttonMap = (HashMap<String, Command>)
                (buttonsField).get(remoteControlApp);

        assertEquals(buttonMap.size(), 1);
        assertEquals(buttonMap.get("C"), command1);
    }
}