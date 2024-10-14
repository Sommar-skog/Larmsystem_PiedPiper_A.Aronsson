import java.util.Random;

public class WindowDetector extends Detector{

    private Room room;

    public WindowDetector(String nameOfDetector, boolean alarmDetectorOn, boolean triggerAlarm, Room room) {
        super(nameOfDetector, alarmDetectorOn, triggerAlarm);
        this.room = room;
    }

    @Override
    public void description() {
        super.description();
        System.out.println(room.getNameOfRoom());
    }

    @Override
    public void alarmTriggered() {
        super.alarmTriggered();
        int randomNum = new Random().nextInt(2);
        System.out.print("\u001B[31m");
        if (randomNum == 0) {
            System.out.println(" Rutan har krossats!");
        } else{
            System.out.println(" Fönstret har öppnats!");
        }
        System.out.print("\u001B[0m");
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "WindowDetector{" +
                "room=" + room +
                "} " + super.toString();
    }
}
