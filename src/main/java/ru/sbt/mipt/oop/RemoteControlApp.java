package ru.sbt.mipt.oop;

import rc.RemoteControl;
import rc.RemoteControlRegistry;
import ru.sbt.mipt.oop.Commands.Command;

import java.util.HashMap;

public class RemoteControlApp implements RemoteControl {

    private HashMap<String, Command> buttonsVocab;
    private RemoteControlRegistry remoteControlRegistry;

    public RemoteControlApp(RemoteControlRegistry remoteControlRegistry) {
        this.buttonsVocab = new HashMap<>();
        this.remoteControlRegistry = remoteControlRegistry;
    }

    public void addCommand(String buttonCode, Command command){
        if ((buttonCode.length() == 1) && isCorrectCode(buttonCode)){
            buttonsVocab.put(buttonCode, command);
        }
    }

    public boolean isCorrectCode(String buttonCode){
        switch (buttonCode){
            case "A":
            case "B":
            case "C":
            case "D":
            case "1":
            case "2":
            case "3":
            case "4":
                return true;
        }

        return false;
    }

    @Override
    public void onButtonPressed(String buttonCode, String rcId) {
        if (buttonsVocab.containsKey(buttonCode)){
            Command buttonCommand = buttonsVocab.get(buttonCode);
            buttonCommand.executeCommand();
        }
    }
}
