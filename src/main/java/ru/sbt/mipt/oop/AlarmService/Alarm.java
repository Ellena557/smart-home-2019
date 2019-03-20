package ru.sbt.mipt.oop.AlarmService;

public class Alarm {
    private AlarmState state;
    private String code;

    public Alarm(){
        this.state = new AlarmDeactivatedState(this);
    }

    public AlarmState getState(){
        return state;
    }

    public void setCode(String newCode){
        code = newCode;
    }

    public void changeState(AlarmState newState){
        this.state = newState;
    }

    public void activateAlarm(String code){
        state.activateAlarm(code);
    }

    public void deactivateAlarm(String code){
        state.deactivateAlarm(code);
    }

    public void turnOnAlert(){
        state.printAlert();
    }
}
