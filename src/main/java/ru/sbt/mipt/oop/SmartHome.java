package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.AlarmService.Alarm;
import ru.sbt.mipt.oop.Components.Room;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements Actionable{
    Collection<Room> rooms;
    private Alarm alarm;

    public SmartHome() {
        rooms = new ArrayList<>();
        alarm = new Alarm();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Collection<Room> getRooms() {
        return rooms;
    }

    @Override
    public void execute(Action action){
        action.executeObj(this);

        for (Room room : rooms) {
            room.execute(action);
        }
    }

    public Alarm getAlarm(){
        return alarm;
    }
}
