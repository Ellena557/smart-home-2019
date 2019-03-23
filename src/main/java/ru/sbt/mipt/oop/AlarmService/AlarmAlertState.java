package ru.sbt.mipt.oop.AlarmService;

public class AlarmAlertState implements AlarmState {

    private AlarmState state;
    private Alarm alarm;

    public AlarmAlertState(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activateAlarm(String enteredCode) {
        System.out.println("Alarm is already activated, alert is turned on!");
    }

    @Override
    public void deactivateAlarm(String enteredCode) {
        if (alarm.validateCode(enteredCode)){

            // пароль введен верно, выключаем сигнализацию и сирену
            AlarmState newState = new AlarmDeactivatedState(alarm);
            alarm.changeState(newState);
            System.out.println("Alarm deactivated");
        } else {
            System.out.println("Wrong password entered!");

            // пароль введен неверно, остаемся в этом состоянии
            AlarmState newState = new AlarmAlertState(alarm);
            alarm.changeState(newState);
            alarm.turnOnAlert();
        }
    }

    @Override
    public void turnOnAlert() {
        System.out.println("Alert!");
    }

    @Override
    public AlarmState getCurrentState() {
        return state;
    }
}
