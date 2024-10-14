public class MotionDetector extends Detector{

    private Room room;

    public MotionDetector(String nameOfDetector, boolean alarmOn, boolean triggerAlarm, Room room) {
        super(nameOfDetector, alarmOn, triggerAlarm);
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
        System.out.println("\u001B[31m Upprepade rörelser har upptäckts! \u001B[0m");
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "MotionDetector{" +
                "room=" + room +
                "} " + super.toString();
    }
}
