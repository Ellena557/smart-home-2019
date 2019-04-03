package ru.sbt.mipt.oop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import ru.sbt.mipt.oop.Adapter.EventsManager;
import ru.sbt.mipt.oop.EventProcessors.SensorEventProcessor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

@Configuration
public class MyConfiguration {

    private SmartHome smartHome;
    private EventsManager eventsManager;

    public MyConfiguration() throws IOException {
        HomeInitializer homeInitializer = new HomeJsonInitializer();

        // creating a smartHome
        SmartHome smartHome = homeInitializer.initializeHome();
        eventsManager = new HomeManager(createProcessors(smartHome));
    }

    @Bean
    @Description("smarthome")
    SmartHome getSmartHome() {
        return smartHome;
    }

    @Bean
    @Description("eventsManager")
    EventsManager getEventsManager() {
        return eventsManager;
    }

    private ArrayList<SensorEventProcessor> createProcessors(SmartHome smartHome) {

        Collection<SensorEventProcessor> eventProcessors = EventProcessorCreator.getNewEventProcessors(smartHome);
        return (ArrayList<SensorEventProcessor>) eventProcessors;
    }
}
