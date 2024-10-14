public class DoorDetector extends Detector{

    private Room room;

    public DoorDetector(String nameOfDetector, boolean alarmOn, boolean triggerAlarm, Room room) {
        super(nameOfDetector, alarmOn, triggerAlarm);
        this.room = room;
    }

    @Override
    public void alarmTriggered() {
        super.alarmTriggered();
        System.out.println("\u001B[31m En ytterdörr/garagedörr/innedörr med detektor har öppnats!\u001B[0m");
        System.out.println();
    }

    @Override
    public void description() {
        super.description();
        System.out.println(room.getNameOfRoom());
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "DoorDetector{" +
                "room=" + room +
                "} " + super.toString();
    }
}
