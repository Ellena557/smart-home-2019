package ru.sbt.mipt.oop.AlarmService;

public class AlarmDeactivatedState implements AlarmState {
    private Alarm alarm;
    private AlarmState state;

    public AlarmDeactivatedState(Alarm alarm){
        this.alarm = alarm;
    }


    @Override
    public void activateAlarm(String enteredCode) {
        AlarmState newState = new AlarmActivatedState(alarm);
        alarm.changeState(newState);
        System.out.println("Alarm activated");
    }

    @Override
    public void deactivateAlarm(String enteredCode) {
        System.out.println("Alarm is already deactivated");
    }

    @Override
    public void turnOnAlert() {
        // pass
    }

    @Override
    public AlarmState getCurrentState() {
        return state;
    }
}
