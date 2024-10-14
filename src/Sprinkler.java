public class Sprinkler implements SecuritySystemComponent {

    private String nameOfSprinkler;
    private SmokeDetector smokeDetector;
    private Room room;
    private boolean triggerAlarm;

    public Sprinkler(String nameOfSprinkler, SmokeDetector smokeDetector, Room room) {
        this.nameOfSprinkler = nameOfSprinkler;
        this.smokeDetector = smokeDetector;
        this.room = room;
        this.triggerAlarm = false;
    }

    @Override
    public void description() {
        System.out.println("Säkerhetssystemskomponent: " + nameOfSprinkler + ", " + room.getNameOfRoom());
    }

    @Override
    public void alarmTriggered() {
        System.out.println();
        triggerAlarm = true;
        description();
        System.out.println("\u001B[34m Sprinklern sprutar vatten!\u001B[0m");
        System.out.println();
    }

    public String getNameOfSprinkler() {
        return nameOfSprinkler;
    }

    public void setNameOfSprinkler(String nameOfSprinkler) {
        this.nameOfSprinkler = nameOfSprinkler;
    }

    public SmokeDetector getSmokeDetector() {
        return smokeDetector;
    }

    public void setSmokeDetector(SmokeDetector smokeDetector) {
        this.smokeDetector = smokeDetector;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public boolean isTriggerAlarm() {
        return triggerAlarm;
    }

    public void setTriggerAlarm(boolean triggerAlarm) {
        this.triggerAlarm = triggerAlarm;
    }

    @Override
    public String toString() {
        return "Sprinkler{" +
                "nameOfSprinkler='" + nameOfSprinkler + '\'' +
                ", smokeDetector=" + smokeDetector +
                ", room=" + room +
                ", triggerAlarm=" + triggerAlarm +
                '}';
    }
}
