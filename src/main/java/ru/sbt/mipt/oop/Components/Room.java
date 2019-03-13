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

        // Можно через цикл for, можно через Iterator.
        // Здесь и в классе SmartHome реализовано два раных способа.
        Iterator<Light> lightIter = this.lights.iterator();
        Iterator<Door> doorIter = this.doors.iterator();

        while (lightIter.hasNext()) {
            Light light = lightIter.next();
            light.execute(action);
        }

        while (doorIter.hasNext()) {
            Door door = doorIter.next();
            door.execute(action);
        }
    }
}
