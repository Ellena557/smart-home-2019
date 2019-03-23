package ru.sbt.mipt.oop.AlarmService;

public class AlarmActivatedState implements AlarmState {

    private Alarm alarm;
    private AlarmState state;

    public AlarmActivatedState(Alarm alarm){
        this.alarm = alarm;
    }

    @Override
    public void activateAlarm(String enteredCode) {
        System.out.println("Alarm is already activated");
    }

    @Override
    public void deactivateAlarm(String enteredCode) {
        if (alarm.validateCode(enteredCode)){

            // выключаем сигнализацию, если пароль введен верно
            AlarmState newState = new AlarmDeactivatedState(alarm);
            alarm.changeState(newState);
            System.out.println("Alarm deactivated");
        }
        else {
            System.out.println("Wrong password entered!");

            // если пароль введен неверно, переходим в состояние тревоги
            AlarmState newState = new AlarmAlertState(alarm);
            alarm.changeState(newState);

            // включаем сирену
            alarm.turnOnAlert();
        }
    }

    @Override
    public void turnOnAlert() {
        // pass
    }

    @Override
    public AlarmState getCurrentState() {
        return this.state;
    }
}
