public abstract class Detector implements SecuritySystemComponent {

    private boolean alarmDetectorOn;
    private boolean triggerAlarm;
    private String nameOfDetector;

    public Detector(String nameOfDetector, boolean alarmDetectorOn, boolean triggerAlarm) {
        this.alarmDetectorOn = alarmDetectorOn;
        this.nameOfDetector = nameOfDetector;
        this.triggerAlarm = triggerAlarm;
    }

    public void description(){
        System.out.print("Detektor: " + nameOfDetector + ", ");
    }

    public  void alarmTriggered(){
        triggerAlarm = true;
        System.out.println("\u001B[31mEn deterkor har löst ut!\u001B[0m");
        System.out.println();
        description();
    }

    public boolean isAlarmDetectorOn() {
        return alarmDetectorOn;
    }

    public void setAlarmDetectorOn(boolean alarmDetectorOn) {
        this.alarmDetectorOn = alarmDetectorOn;
    }

    public String getNameOfDetector() {
        return nameOfDetector;
    }

    public void setNameOfDetector(String nameOfDetector) {
        this.nameOfDetector = nameOfDetector;
    }

    public boolean isTriggerAlarm() {
        return triggerAlarm;
    }

    public void setTriggerAlarm(boolean triggerAlarm) {
        this.triggerAlarm = triggerAlarm;
    }

    @Override
    public String toString() {
        return "Detector{" +
                "alarmDetectorOn=" + alarmDetectorOn +
                ", triggerAlarm=" + triggerAlarm +
                ", nameOfDetector='" + nameOfDetector + '\'' +
                '}';
    }
}
