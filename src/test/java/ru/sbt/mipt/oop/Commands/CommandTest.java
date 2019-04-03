package ru.sbt.mipt.oop.Commands;

import org.junit.Test;
import rc.RemoteControlRegistry;
import ru.sbt.mipt.oop.AlarmService.*;
import ru.sbt.mipt.oop.Components.Door;
import ru.sbt.mipt.oop.Components.Light;
import ru.sbt.mipt.oop.Components.Room;
import ru.sbt.mipt.oop.HomeInitializer;
import ru.sbt.mipt.oop.HomeJsonInitializer;
import ru.sbt.mipt.oop.RemoteControlApp;
import ru.sbt.mipt.oop.SmartHome;

import java.io.IOException;

import static org.junit.Assert.*;

public class CommandTest {
    @Test
    public void turnAllLightOnCommandTest() throws IOException {
        HomeInitializer homeInitializer = new HomeJsonInitializer();
        SmartHome smartHome = homeInitializer.initializeHome();
        RemoteControlRegistry remoteControlRegistry = new RemoteControlRegistry();
        RemoteControlApp remoteControlApp = new RemoteControlApp(remoteControlRegistry);

        Command command = new TurnAllLightOnCommand(smartHome);
        remoteControlApp.addCommand("A", command);

        remoteControlApp.onButtonPressed("A", "1");
        boolean allLightOn = true;

        for (Room room : smartHome.getRooms()){
            for (Light light : room.getLights()){
                if (!light.isOn()){
                    allLightOn = false;
                }
            }
        }

        assertEquals(allLightOn, true);
    }

    @Test
    public void turnAllLightOffCommandTest() throws IOException {
        HomeInitializer homeInitializer = new HomeJsonInitializer();
        SmartHome smartHome = homeInitializer.initializeHome();
        RemoteControlRegistry remoteControlRegistry = new RemoteControlRegistry();
        RemoteControlApp remoteControlApp = new RemoteControlApp(remoteControlRegistry);

        Command command = new TurnAllLightOffCommand(smartHome);
        remoteControlApp.addCommand("B", command);

        remoteControlApp.onButtonPressed("B", "1");
        boolean allLightOff = true;

        for (Room room : smartHome.getRooms()){
            for (Light light : room.getLights()){
                if (light.isOn()){
                    allLightOff = false;
                }
            }
        }

        assertEquals(allLightOff, true);
    }

    @Test
    public void closeHallDoorCommandTest() throws IOException {
        HomeInitializer homeInitializer = new HomeJsonInitializer();
        SmartHome smartHome = homeInitializer.initializeHome();
        RemoteControlRegistry remoteControlRegistry = new RemoteControlRegistry();
        RemoteControlApp remoteControlApp = new RemoteControlApp(remoteControlRegistry);

        Command command = new CloseHallDoorCommand(smartHome);
        remoteControlApp.addCommand("2", command);

        remoteControlApp.onButtonPressed("2", "1");
        boolean hallDoorClosed = true;

        for (Room room : smartHome.getRooms()){
            if(room.getName().equals("hall")){
                for (Door door : room.getDoors()){
                    if (door.isOpened()){
                        hallDoorClosed = false;
                    }
                }
            }
        }

        assertEquals(hallDoorClosed, true);
    }

    @Test
    public void turnOnHallLightCommandTest() throws IOException {
        HomeInitializer homeInitializer = new HomeJsonInitializer();
        SmartHome smartHome = homeInitializer.initializeHome();
        RemoteControlRegistry remoteControlRegistry = new RemoteControlRegistry();
        RemoteControlApp remoteControlApp = new RemoteControlApp(remoteControlRegistry);

        Command command = new TurnOnHallLightCommand(smartHome);
        remoteControlApp.addCommand("C", command);

        remoteControlApp.onButtonPressed("C", "1");
        boolean hallLightOn = true;

        for (Room room : smartHome.getRooms()){
            if(room.getName().equals("hall")){
                for (Light light : room.getLights()){
                    if (!light.isOn()){
                        hallLightOn = false;
                    }
                }
            }
        }

        assertEquals(hallLightOn, true);
    }

    @Test
    public void turnAlarmOnCommandTest() throws IOException {
        HomeInitializer homeInitializer = new HomeJsonInitializer();
        SmartHome smartHome = homeInitializer.initializeHome();
        RemoteControlRegistry remoteControlRegistry = new RemoteControlRegistry();
        RemoteControlApp remoteControlApp = new RemoteControlApp(remoteControlRegistry);

        Alarm alarm = new Alarm();
        AlarmState currentState = new AlarmDeactivatedState(alarm);
        alarm.changeState(currentState);

        Command command = new TurnAlarmOnCommand(smartHome, "goodcode");
        remoteControlApp.addCommand("D", command);

        remoteControlApp.onButtonPressed("D", "1");
        Alarm newAlarm = smartHome.getAlarm();
        AlarmState finalState = newAlarm.getState();

        assertTrue(finalState instanceof AlarmActivatedState);
    }

    @Test
    public void turnAlertOnCommandTest() throws IOException {
        HomeInitializer homeInitializer = new HomeJsonInitializer();
        SmartHome smartHome = homeInitializer.initializeHome();
        RemoteControlRegistry remoteControlRegistry = new RemoteControlRegistry();
        RemoteControlApp remoteControlApp = new RemoteControlApp(remoteControlRegistry);

        Command command = new TurnAlertOnCommand(smartHome);
        remoteControlApp.addCommand("3", command);

        remoteControlApp.onButtonPressed("3", "1");
        Alarm newAlarm = smartHome.getAlarm();
        AlarmState finalState = newAlarm.getState();

        assertTrue(finalState instanceof AlarmAlertState);
    }
}