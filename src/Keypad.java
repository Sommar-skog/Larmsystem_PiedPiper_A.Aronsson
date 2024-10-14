import java.io.IOException;
import java.util.Scanner;

public class Keypad implements SecuritySystemComponent {

    private String nameOfKeypad;
    private boolean keypadEnterPinMode;
    private Room room;
    private boolean alarmTriggered;
    private int pin = 1234;

    public Keypad(String nameOfKeypad, Room room, boolean alarmTriggered, boolean keypadEnterPinMode){
        this.nameOfKeypad = nameOfKeypad;
        this.room = room;
        this.keypadEnterPinMode = keypadEnterPinMode;
        this.alarmTriggered = alarmTriggered;
    }

    private int countDown(){
        Scanner scanner = new Scanner(System.in);
        long startTime = System.currentTimeMillis(); //tiden då metoden körs
        long timeLimit = 30 * 1000; // 30 sek i millisekunder
        System.out.print("Vänligen knappa in din PIN, inom 30 sek, för att larma av: ");
        while ((System.currentTimeMillis() - startTime) < timeLimit) {
            try {
                if (System.in.available() > 0) {  //Vill att jag använder IoExpectation eller Try/Catch
                    if (scanner.hasNextInt()) {
                        return scanner.nextInt(); //Om input kommer inom 30 sek, avsluta loopen.
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return -1; // tiden är slut
    }

    public boolean enterPin(){
        boolean alarmTriggeredByWrongPin;
        int userInputPin = countDown();
        if (userInputPin == pin) {
            System.out.println();
            description();
            System.out.println("\u001B[32m Rätt PIN. Inblottslarm avslaget!\u001B[0m");
            alarmTriggeredByWrongPin = false;
        } else if (userInputPin == -1){
            System.out.println(); //Behövs två .println för att ge samma visuella uttryck i ourputten eftersom .print lämnas tom. När användaren skriver in kod genereras automatiskt en radbrytning vilken uteblir när anv. inte skriver in något.
            System.out.println();
            alarmTriggered();
            System.out.println("\u001B[31m Tiden är ute! \u001B[0m");
            alarmTriggeredByWrongPin = true;
        } else {
            System.out.println();
            alarmTriggered();
            System.out.println("\u001B[31m Fel PIN! \u001B[0m");
            alarmTriggeredByWrongPin = true;
        }
        return alarmTriggeredByWrongPin;
    }

    @Override
    public void description() {
        System.out.println("Säkerhetssystemskomponent: " + nameOfKeypad + ", " + room.getNameOfRoom());
    }

    @Override
    public void alarmTriggered() {
        description();
        System.out.print("\u001B[31m Inbrottslarm utlöst: \u001B[0m");
        alarmTriggered = true;
    }

    public String getNameOfKeypad() {
        return nameOfKeypad;
    }

    public void setNameOfKeypad(String nameOfKeypad) {
        this.nameOfKeypad = nameOfKeypad;
    }

    public boolean isKeypadEnterPinMode() {
        return keypadEnterPinMode;
    }

    public void setKeypadEnterPinMode(boolean keypadEnterPinMode) {
        this.keypadEnterPinMode = keypadEnterPinMode;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public boolean isAlarmTriggered() {
        return alarmTriggered;
    }

    public void setAlarmTriggered(boolean alarmTriggered) {
        this.alarmTriggered = alarmTriggered;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    @Override
    public String toString() {
        return "Keypad{" +
                "nameOfKeypad='" + nameOfKeypad + '\'' +
                ", keypadEnterPinMode=" + keypadEnterPinMode +
                ", room=" + room +
                ", alarmTriggered=" + alarmTriggered +
                ", pin=" + pin +
                '}';
    }
}
