package ru.sbt.mipt.oop;

public enum SensorEventType {
    LIGHT_ON("-1"), LIGHT_OFF("-1"), DOOR_OPEN("-1"), DOOR_CLOSED("-1"),
    ALARM_ACTIVATE("active"), ALARM_DEACTIVATE("deactive");

    private String code;

    SensorEventType(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }
}
