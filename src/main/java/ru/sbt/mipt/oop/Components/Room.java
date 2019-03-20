package ru.sbt.mipt.oop.Components;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Actionable;

import java.util.Collection;
import java.util.Iterator;

public class Room implements Actionable {
    private Collection<Light> lights;
    private Collection<Door> doors;
    private String name;

    public Room(Collection<Light> lights, Collection<Door> doors, String name) {
        this.lights = lights;
        this.doors = doors;
        this.name = name;
    }

    public Collection<Light> getLights() {
        return lights;
    }

    public Collection<Door> getDoors() {
        return doors;
    }

    public String getName() {
        return name;
    }

    @Override
    public void execute(Action action) {
        action.executeObj(this);

        lights.forEach(light -> light.execute(action));
        doors.forEach(door -> door.execute(action));
    }
}
