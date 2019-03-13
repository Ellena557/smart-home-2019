package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.EventProcessors.SensorEventProcessor;

import java.io.IOException;
import java.util.Collection;

public class Application {

    public static void main(String... args) throws IOException {

        HomeInitializer homeInitializer = new HomeJsonInitializer();

        // creating a smartHome
        SmartHome smartHome = homeInitializer.initializeHome();

        //цикл обработки событий
        processEvents(smartHome);
    }

    private static void processEvents(SmartHome smartHome) {
        SensorEventCreator eventCreator = new RandomSensorEventCreator();
        SensorEvent event = eventCreator.getNextSensorEvent();

        Collection<SensorEventProcessor> eventProcessors = EventProcessorCreator.getNewEventProcessors();

        while (event != null) {
            System.out.println("Got event: " + event);

            for (SensorEventProcessor oneProcessor : eventProcessors) {
                oneProcessor.processor(smartHome, event);
            }

            event = eventCreator.getNextSensorEvent();
        }
    }
}
