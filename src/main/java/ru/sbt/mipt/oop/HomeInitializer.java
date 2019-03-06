package ru.sbt.mipt.oop;

import java.io.IOException;

public interface HomeInitializer {

    //считываем или иным образом узнаем состояние дома
    SmartHome initializeHome() throws IOException;
}
