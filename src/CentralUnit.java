import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class CentralUnit implements SecuritySystemComponent {

    private String nameOfUnit;
    private Scanner userInput = new Scanner(System.in);
    private boolean menuOn = true;
    private int menuSelection;
    private boolean alarmTriggered = false;
    private List<Room> rooms;
    private Room room;
    private Room selectedRoom;
    private Detector detector;
    private Random random = new Random();
    private int sirens;
    private Siren Siren;
    private List<Siren> sirenList;

    public CentralUnit(String nameOfUnit, int sirens) {
        this.nameOfUnit = nameOfUnit;
        this.rooms = new ArrayList<>();
        this.sirenList = new ArrayList<>();
        this.sirens = sirens;
        addSirens();
    }

    public void mainMenu(){
        while (menuOn) {
            printMainMenu();
            userInputMenuOption();
            switchMainMenu();
        }
    }

    private void printMainMenu() {
        System.out.println();
        System.out.println( "            ---- Security System Menu ----\n" +
                            "               --- Central Unit ---\n" +
                            "\n" +
                            "   1. Larma av \n" +
                            "   2. Larma på \n" +
                            "   3. Lista alla säkerhetssystemskomponenter inkl. detektorer i systemet\n" +
                            "   4. Återkalla utlöst larm\n" +
                            "   5. Simulera larm\n" +
                            "   6. Avsluta huvudmenyn");
        System.out.println();
    }

    private int userInputMenuOption() {
        System.out.print("Gör ditt menyval: ");
        menuSelection = userInput.nextInt();
        return menuSelection;
    }

    private void switchMainMenu(){
        switch (menuSelection) {
            case 1:
                deactivateBurglaryAlarm();
                break;
            case 2:
                activateBurglaryAlarm();
                break;
            case 3:
                printListOfAllSecuritySystemComponents();
                break;
            case 4:
                resetTriggeredAlarm();
                break;
            case 5:
                simulateMenu();
                break;
            case 6:
                separatorLine();
                System.out.println("Avslutar huvudmenyn");
                menuOn = false;
                break;
            default:
                separatorLine();
                System.out.println("Ogiltigt menyval. Välj alternativ 1-6 (skriv in ett heltal.");
                break;
        }
    }

    private void deactivateBurglaryAlarm(){
        separatorLine();
        System.out.println("Stänger av inbrottslarmet...");
        for (Room room : rooms) {
            for (Detector detector : room.getDetectorList()) {
                if (detector instanceof DoorDetector || detector instanceof WindowDetector || detector instanceof MotionDetector) {
                    detector.setAlarmDetectorOn(false);
                }
            }
            if (room.isHasKeypad()){
                room.getKeypad().setKeypadEnterPinMode(false);
            }
        }
        System.out.println("Dörrdetektorer, fönsterdetektorer och rörelsedetektorer avslagna.");
        separatorLine();
    }

    private void activateBurglaryAlarm(){
        separatorLine();
        System.out.println("Sätter på inbrottslarmet...");
        for (Room room : rooms) {
            for (Detector detector : room.getDetectorList()) { //Eftersom brandlarmet alltid ska vara på kan jag sätta på alla här (brandlarm kommer inte ändra status).
                detector.setAlarmDetectorOn(true);
            }
            if (room.isHasKeypad()){
                room.getKeypad().setKeypadEnterPinMode(true);
            }
        }

        System.out.println("Dörrdetektorer, fönsterdetektorer och rörelsedetektorer påslagna.");
        separatorLine();
    }

    private void printListOfAllSecuritySystemComponents(){
        separatorLine();
        System.out.println("                    --- SÄKERHETSSYSTEMSKOMPONENTER ---");
        System.out.println();
        System.out.println("Färgförklaring: " +
                "\u001B[32mGrön - Komponent/detektor påslagen och ej utlöst\u001B[0m," +
                "\u001B[37m\u001B[3m Vit och kursiv -  Komponent/detektor avslagen\u001B[0m," +
                "\u001B[31m Röd -  Komponent/detektor utlöst\u001B[0m");
        System.out.println();
        printListOfAllDetectors();
        printListOfSecuritySystemComponentsExceptDetectors();
        separatorLine();
    }

    private void printListOfAllDetectors() {
        System.out.println("                            --- Detektorer ---");
        System.out.println();
        for (Room room : rooms) {
            System.out.println(room.getNameOfRoom().toUpperCase());
            for (Detector detector : room.getDetectorList()) {
                String detectorDescription = "      " + detector.getNameOfDetector() + ": [Detektor aktiv: " + detector.isAlarmDetectorOn() + ", Detektor utlöst: " + detector.isTriggerAlarm() + "] ";
                if (detector.isAlarmDetectorOn()) {
                    if (detector.isTriggerAlarm()) {
                        System.out.println("\u001B[31m" + detectorDescription + "\u001B[0m");
                    } else {
                        System.out.println("\u001B[32m" + detectorDescription + "\u001B[0m");
                    }
                } else {
                    System.out.println("\u001B[37m\u001B[3m" + detectorDescription + "\u001B[0m");
                }
            }
            System.out.println();
        }
    }

    private void printListOfSecuritySystemComponentsExceptDetectors(){
        System.out.println("                    --- Övriga säkerhetssystemskomponenter ---");
        System.out.println();
        System.out.println("CENTRALENHET");
        String centralUnitAlarmTriggered =  "    " + nameOfUnit + ": [Larm utlöst: " + alarmTriggered + "]";
        if (alarmTriggered){
            System.out.println("\u001B[31m" + centralUnitAlarmTriggered + "\u001B[0m");
        } else {
            System.out.println("\u001B[32m" + centralUnitAlarmTriggered + "\u001B[0m");
        }
        System.out.println();
        System.out.println("LARMKNAPPSATS");
        for (Room room : rooms) {
            if (room.isHasKeypad()) {
                String keypadDescription = "    " + room.getKeypad().getNameOfKeypad() + ", " + room.getNameOfRoom() + ": [Larm utlöst: " + room.getKeypad().isAlarmTriggered() + "]";
                if (room.getKeypad().isKeypadEnterPinMode()) {
                    if (room.getKeypad().isAlarmTriggered()) {
                        System.out.println("\u001B[31m" + keypadDescription + "\u001B[0m");
                    } else {
                        System.out.println("\u001B[32m" + keypadDescription + "\u001B[0m");
                    }
                } else {
                    System.out.println("\u001B[37m\u001B[3m" + keypadDescription + "\u001B[0m");
                }
            }
        }

        System.out.println();
        System.out.println("SIREN");
        for (Siren siren : sirenList) {
            String sirenAlarmedTriggered = "     " + siren.getNameOfSiren() + ", " + nameOfUnit + ": [Siren utlöst: " + siren.isTriggerAlarm() + "]";
            if (siren.isTriggerAlarm()) {
                System.out.println("\u001B[31m" + sirenAlarmedTriggered + "\u001B[0m");
            } else {
                System.out.println("\u001B[32m" + sirenAlarmedTriggered + "\u001B[0m");
            }
        }
        System.out.println();
        System.out.println("SPRINKLER");
        for (Room room : rooms) {
            for (Detector detector : room.getDetectorList()){
                if (detector instanceof SmokeDetector){
                    SmokeDetector smokeDetector = (SmokeDetector) detector; //typkonvertering (cast) till SmokeDetector
                    if (smokeDetector.getSprinklers() > 0) {
                        for (Sprinkler sprinkler : smokeDetector.getSprinklerList()) {
                            String sprinklerAlarmTriggered = "     " + sprinkler.getNameOfSprinkler() + ", " + sprinkler.getRoom().getNameOfRoom() + ": [Detektorn utlöst: " + sprinkler.isTriggerAlarm() + "]";
                            if (sprinkler.isTriggerAlarm()) {
                                System.out.println("\u001B[31m" + sprinklerAlarmTriggered + "\u001B[0m");
                            } else {
                                System.out.println("\u001B[32m" + sprinklerAlarmTriggered + "\u001B[0m");
                            }
                        }
                    }
                }
            }
        }

    }

    private void resetTriggeredAlarm(){
        separatorLine();
        for (Room room : rooms) {
            for (Detector detector : room.getDetectorList()) {
                detector.setTriggerAlarm(false);
                if (detector instanceof SmokeDetector){
                    SmokeDetector smokeDetector = (SmokeDetector) room.getDetector();
                    for (Sprinkler sprinkler : smokeDetector.sprinklerList){
                        sprinkler.setTriggerAlarm(false);
                    }
                }
            }
        }
        for (Siren siren : sirenList){
            siren.setTriggerAlarm(false);
        }
        for (Room room : rooms) {
            if (room.isHasKeypad()){
                room.getKeypad().setAlarmTriggered(false);
            }
        }
        alarmTriggered = false;
        System.out.println("Utlöst alarm återställt!");
        separatorLine();
    }

    private void simulateMenu(){
        separatorLine();
        boolean on = true;
        while (on) {
            System.out.println("    --- Simulera larm ---");
            System.out.println();
            System.out.println("    --- Simuleringsmeny ---");
            System.out.println();
            System.out.println( "    1. Simulera inbrott\n" +
                                "    2. Simulera rörelse utomhus\n" +
                                "    3. Simulera brand\n" +
                                "    4. Simulera att en dörr med dörrdetektor öppnas\n" +
                                "    5. Återgå till huvudmenyn\n");
            int selection = userInputMenuOption();
            separatorLine();
            if (selection == 1) {
                triggerBurglaryAlarm();
                backToMainMenu();
            } else if (selection == 2) {
                triggerMotionAlarmByPool();
                backToMainMenu();
            } else if (selection == 3) {
                triggerFireAlarm();
                backToMainMenu();
            } else if (selection == 4){
                triggerDoorDetector();
                backToMainMenu();
            } else if (selection == 5) {
                System.out.println("Återgår till huvudmenyn");
                on = false;
            } else {
                System.out.println("Fel angivet menyval. Välj 1-4.");
            }
        }
        separatorLine();
    }

    private Detector randomDetectorInRandomRoom(){
        int randomRoomIndex = random.nextInt(rooms.size());
        this.selectedRoom = rooms.get(randomRoomIndex);
        int randomDetectorIndex = random.nextInt(selectedRoom.getDetectorList().size());
        return selectedRoom.getDetectorList().get(randomDetectorIndex);
    }

    private void triggerBurglaryAlarm(){
        boolean alarmedTriggeredByRandom = false;
        while (!alarmedTriggeredByRandom){
            Detector selectedDetector = randomDetectorInRandomRoom();
            if ( selectedDetector instanceof WindowDetector || selectedDetector instanceof DoorDetector || selectedDetector instanceof MotionDetector) {
                if (!selectedDetector.isAlarmDetectorOn()) {
                    System.out.println("\u001B[31mInbrottslarm avstängt! Simulering ej tillgänglig.\u001b[0m ");
                    alarmedTriggeredByRandom = true;
                    } else {
                        if (selectedDetector instanceof WindowDetector || selectedDetector instanceof MotionDetector) {
                        selectedDetector.alarmTriggered();
                        System.out.println();
                        alarmTriggered();
                        alarmedTriggeredByRandom = true;
                        } else if (selectedDetector instanceof DoorDetector) {
                            selectedDetector.alarmTriggered();
                            boolean wrongPin = selectedRoom.getKeypad().enterPin();
                            if (!wrongPin) {
                                selectedDetector.setTriggerAlarm(false);
                                deactivateBurglaryAlarm();
                                alarmedTriggeredByRandom = true;
                            } else {
                                System.out.println();
                                alarmTriggered();
                                alarmedTriggeredByRandom = true;
                        }
                    }
                }
            }
        }
        this.selectedRoom = null;
    }

    private void triggerMotionAlarmByPool(){
        for (Room room : rooms){
            if (room.getNameOfRoom().equals("Trädgården (poolområdet)")) {
                if (room.getDetector() instanceof MotionDetector){
                    if (!room.getDetector().isAlarmDetectorOn()){
                        System.out.println("\u001B[31mInbrottslarm avstängt! Simulering ej tillgänglig.\u001B[0m");
                    } else {
                        room.getDetector().alarmTriggered();
                        alarmTriggered();
                        System.out.println();
                    }
                }
            }
        }
    }

    private void triggerFireAlarm(){
        boolean alarmedTriggeredByRandom = false;
        while (!alarmedTriggeredByRandom) {
            Detector selectedDetector = randomDetectorInRandomRoom();
            if (selectedDetector instanceof SmokeDetector){
                selectedDetector.alarmTriggered();
                alarmedTriggeredByRandom = true;
            }
        }
        alarmTriggered();
    }

    private void triggerDoorDetector(){
        boolean alarmedTriggeredByRandom = false;
        while (!alarmedTriggeredByRandom){
            Detector selectedDetector = randomDetectorInRandomRoom();
            if (selectedDetector instanceof DoorDetector){
                selectedDetector.alarmTriggered();
                boolean wrongPin = selectedRoom.getKeypad().enterPin();
                if (!wrongPin){
                    selectedDetector.setTriggerAlarm(false);
                    alarmedTriggeredByRandom = true;
                } else {
                    System.out.println();
                    alarmTriggered();
                    alarmedTriggeredByRandom = true;
                }
            }
        }
        selectedRoom = null;
    }

    public void addRoom(Room... newRoom) { //[...] efter en parameter i en metod, behandlar Java det som en array internt.
        rooms.addAll(List.of(newRoom));
    }

    private void addSirens(){
        for (int i = 0; i < sirens; i++) {
            int counter = i + 1;
            sirenList.add(new Siren("Siren " + counter, this));
        }
    }

    @Override
    public void description() {
        System.out.println("Säkerhetssystemskomponent: " + nameOfUnit );
    }

    @Override
    public void alarmTriggered() {
        description();
        System.out.println("\u001B[31m Larm utlöst!\u001B[0m");
        System.out.println();
        for (Siren siren : sirenList) {
            siren.alarmTriggered();
            System.out.println();
        }
        alarmTriggered = true;
    }

    private void backToMainMenu(){
        System.out.println();
        System.out.println("\u001B[3mÅtergå till huvudmenyn för att se lista över detektorer, om de löst ut eller inte, och återställa utlöst larm\u001B[0m");
        separatorLine();
    }

    private void separatorLine(){
        System.out.println();
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println();
    }

    public String getNameOfUnit() {
        return nameOfUnit;
    }

    public void setNameOfUnit(String nameOfUnit) {
        this.nameOfUnit = nameOfUnit;
    }

    public Siren getSiren() {
        return Siren;
    }

    public void setSiren(Siren siren) {
        Siren = siren;
    }

    public Detector getDetector() {
        return detector;
    }

    public void setDetector(Detector detector) {
        this.detector = detector;
    }

    public Room getSelectedRoom() {
        return selectedRoom;
    }

    public void setSelectedRoom(Room selectedRoom) {
        this.selectedRoom = selectedRoom;
    }

    public int getMenuSelection() {
        return menuSelection;
    }

    public void setMenuSelection(int menuSelection) {
        this.menuSelection = menuSelection;
    }

    public boolean isAlarmTriggered() {
        return alarmTriggered;
    }

    public void setAlarmTriggered(boolean alarmTriggered) {
        this.alarmTriggered = alarmTriggered;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public int getSirens() {
        return sirens;
    }

    public void setSirens(int sirens) {
        this.sirens = sirens;
    }

    public List<Siren> getSirenList() {
        return sirenList;
    }

    public void setSirenList(List<Siren> sirenList) {
        this.sirenList = sirenList;
    }

    public Scanner getUserInput() {
        return userInput;
    }

    public void setUserInput(Scanner userInput) {
        this.userInput = userInput;
    }

    public boolean isMenuOn() {
        return menuOn;
    }

    public void setMenuOn(boolean menuOn) {
        this.menuOn = menuOn;
    }

    @Override
    public String toString() {
        return "CentralUnit{" +
                "nameOfUnit='" + nameOfUnit + '\'' +
                ", userInput=" + userInput +
                ", menuOn=" + menuOn +
                ", menuSelection=" + menuSelection +
                ", alarmTriggered=" + alarmTriggered +
                ", rooms=" + rooms +
                ", room=" + room +
                ", selectedRoom=" + selectedRoom +
                ", detector=" + detector +
                ", random=" + random +
                ", sirens=" + sirens +
                ", Siren=" + Siren +
                ", sirenList=" + sirenList +
                '}';
    }
}
