import java.util.ArrayList;
import java.util.List;

public class Room {

    private int windows;
    private int doorsWithDoorDetectors;
    private boolean hasMotionDetector;
    private boolean hasSmokeDetector;
    private boolean hasKeypad;
    private String nameOfRoom;
    private List<Detector> detectorList;
    public Detector detector;
    private Keypad keypad;

    public Room(int windows, int doorsWithDoorDetectors, String nameOfRoom, boolean hasMotionDetector, boolean hasSmokeDetector, boolean hasKeypad) {
        this.windows = windows;
        this.doorsWithDoorDetectors = doorsWithDoorDetectors;
        this.nameOfRoom = nameOfRoom;
        this.hasMotionDetector = hasMotionDetector;
        this.hasSmokeDetector = hasSmokeDetector;
        this.hasKeypad = hasKeypad;
        this.detectorList = new ArrayList<>();
        addDetectors();
        addKeypad();
    }

    private void addDetectors(){
        for (int i = 0; i < doorsWithDoorDetectors; i ++) {
            detectorList.add(detector = new DoorDetector("Dörrdetektor", true, false, this));
        }
        if (windows == 1) {
            detectorList.add(detector = new WindowDetector("Fönsterdetektor ", true, false, this));
        } else if (windows > 1) {
            for (int i = 0; i < windows; i++) {
                int counter = i + 1;
                detectorList.add(detector = new WindowDetector("Fönsterdetektor " + counter, true, false, this));
            }
        }
        if (hasMotionDetector){
            detectorList.add(detector = new MotionDetector("Rörelsedetektor", true, false, this));
        }
        if (hasSmokeDetector){
            detectorList.add(detector = new SmokeDetector("Rökdetektor", true, false,this, 1));
        }
    }

    private void addKeypad(){
        if (hasKeypad){
            keypad = new Keypad("Larmknappsats", this, false, true);
        }
    }

    public boolean isHasMotionDetector() {
        return hasMotionDetector;
    }

    public void setHasMotionDetector(boolean hasMotionDetector) {
        this.hasMotionDetector = hasMotionDetector;
    }

    public boolean isHasSmokeDetector() {
        return hasSmokeDetector;
    }

    public void setHasSmokeDetector(boolean hasSmokeDetector) {
        this.hasSmokeDetector = hasSmokeDetector;
    }

    public List<Detector> getDetectorList() {
        return detectorList;
    }

    public void setDetectorList(List<Detector> detectorList) {
        this.detectorList = detectorList;
    }

    public Detector getDetector() {
        return detector;
    }

    public void setDetector(Detector detector) {
        this.detector = detector;
    }

    public int getWindows() {
        return windows;
    }

    public void setWindows(int windows) {
        this.windows = windows;
    }

    public int getDoorsWithDoorDetectors() {
        return doorsWithDoorDetectors;
    }

    public void setDoorsWithDoorDetectors(int doorsWithDoorDetectors) {
        this.doorsWithDoorDetectors = doorsWithDoorDetectors;
    }

    public String getNameOfRoom() {
        return nameOfRoom;
    }

    public void setNameOfRoom(String nameOfRoom) {
        this.nameOfRoom = nameOfRoom;
    }

    public boolean isHasKeypad() {
        return hasKeypad;
    }

    public void setHasKeypad(boolean hasKeypad) {
        this.hasKeypad = hasKeypad;
    }

    public Keypad getKeypad() {
        return keypad;
    }

    public void setKeypad(Keypad keypad) {
        this.keypad = keypad;
    }

    @Override
    public String toString() {
        return "Room{" +
                "windows=" + windows +
                ", exteriorDoors=" + doorsWithDoorDetectors +
                ", hasMotionDetector=" + hasMotionDetector +
                ", hasSmokeDetector=" + hasSmokeDetector +
                ", hasKeypad=" + hasKeypad +
                ", nameOfRoom='" + nameOfRoom + '\'' +
                ", detectorList=" + detectorList +
                ", detector=" + detector +
                ", keypad=" + keypad +
                '}';
    }
}
