package ru.sbt.mipt.oop;

import java.io.IOException;

public class Application {

    public static void main(String... args) throws IOException {

        HomeInitializer homeInitializer = new HomeJsonInitializer();

        // creating a smartHome
        SmartHome smartHome = homeInitializer.initializeHome();

        //цикл обработки событий
        processEvents(smartHome);
    }

    private static void processEvents(SmartHome smartHome) {

        SensorEvent event = SensorEventCreator.getNextSensorEvent();

        while (event != null) {
            System.out.println("Got event: " + event);
            SensorEventProcessor eventProcessor = EventProcessorCreator.getNewEventProcessor(smartHome, event);
            eventProcessor.processor(smartHome, event);

            event = SensorEventCreator.getNextSensorEvent();
        }
    }
}
