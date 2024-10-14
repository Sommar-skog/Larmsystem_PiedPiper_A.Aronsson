import java.util.ArrayList;
import java.util.List;

public class SmokeDetector extends Detector{

    private Sprinkler sprinkler;
    private Room room;
    public List<Sprinkler> sprinklerList;
    private int sprinklers;

    public SmokeDetector(String nameOfDetector, boolean alarmDetectorOn, boolean triggerAlarm, Room room, int sprinklers) {
        super(nameOfDetector, alarmDetectorOn, triggerAlarm);
        this.room = room;
        this.sprinklers = sprinklers;
        sprinklerList = new ArrayList<>();
        addSprinklers();
    }

    private void addSprinklers(){
        if (sprinklers == 1){
            sprinklerList.add(new Sprinkler("Sprinkler",this, this.room));
        } else {
            for (int i = 0; i < sprinklers; i++) {
                int counter = i + 1;
                sprinklerList.add(new Sprinkler("Sprinkler " + counter, this, this.room));
            }
        }
    }

    @Override
    public void description() {
        super.description();
        System.out.println(room.getNameOfRoom());
    }

    @Override
    public void alarmTriggered() {
        super.alarmTriggered();
        System.out.println("\u001B[31m Rök har upptäckts!\u001B[0m");
        for (Sprinkler sprinkler : sprinklerList){
            sprinkler.alarmTriggered();
        }
    }

    public Sprinkler getSprinkler() {
        return sprinkler;
    }

    public void setSprinkler(Sprinkler sprinkler) {
        this.sprinkler = sprinkler;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public List<Sprinkler> getSprinklerList() {
        return sprinklerList;
    }

    public void setSprinklerList(List<Sprinkler> sprinklerList) {
        this.sprinklerList = sprinklerList;
    }

    public int getSprinklers() {
        return sprinklers;
    }

    public void setSprinklers(int sprinklers) {
        this.sprinklers = sprinklers;
    }

    @Override
    public String toString() {
        return "SmokeDetector{" +
                "sprinkler=" + sprinkler +
                ", room=" + room +
                ", sprinklerList=" + sprinklerList +
                ", sprinklers=" + sprinklers +
                "} " + super.toString();
    }
}
