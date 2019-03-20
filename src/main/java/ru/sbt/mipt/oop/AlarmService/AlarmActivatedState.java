package ru.sbt.mipt.oop.AlarmService;

public class AlarmActivatedState implements AlarmState {

    private Alarm alarm;
    private String code;
    private AlarmState state;

    public AlarmActivatedState(Alarm alarm, String code){
        this.alarm = alarm;
        this.code = code;
    }

    @Override
    public void activateAlarm(String enteredCode) {
        System.out.println("Alarm is already activated");
    }

    @Override
    public void deactivateAlarm(String enteredCode) {
        if (this.code.equals(enteredCode)){
            //System.out.println(this.code);

            // выключаем сигнализацию, если пароль введен верно
            AlarmState newState = new AlarmDeactivatedState(alarm);
            alarm.changeState(newState);
            System.out.println("Alarm deactivated");
        } else {
            System.out.println("Wrong password entered!");

            // если пароль введен неверно, переходим в состояние тревоги
            AlarmState newState = new AlarmAlertState(alarm, this.code);
            alarm.changeState(newState);

            // включаем сирену
            alarm.turnOnAlert();
        }
    }

    @Override
    public void printAlert() {
        // pass
    }

    @Override
    public AlarmState getCurrentState() {
        return this.state;
    }
}
