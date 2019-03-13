package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.Components.Room;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements Actionable{
    Collection<Room> rooms;

    public SmartHome() {
        rooms = new ArrayList<>();
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

        //Iterator<Room> roomIter = this.rooms.iterator();
        for (Room room : rooms) {
            room.execute(action);
        }

    }
}
