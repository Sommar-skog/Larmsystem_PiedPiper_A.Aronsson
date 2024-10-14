public class Siren implements SecuritySystemComponent {

    private String nameOfSiren;
    private boolean triggerAlarm = false;
    private CentralUnit centralUnit;

    public Siren(String nameOfSiren, CentralUnit centralUnit){
        this.nameOfSiren = nameOfSiren;
        this.centralUnit = centralUnit;
    }

    @Override
    public void description() {
        System.out.println("Säkerhetssystemskomponent: " + nameOfSiren + ", " + centralUnit.getNameOfUnit());
    }

    @Override
    public void alarmTriggered() {
        triggerAlarm = true;
        description();
        System.out.println("\u001B[33m Sirenen tjuter! *wiiio *wiiio* *wiiooo*\u001B[0m");

    }

    public String getNameOfSiren() {
        return nameOfSiren;
    }

    public void setNameOfSiren(String nameOfSiren) {
        this.nameOfSiren = nameOfSiren;
    }

    public boolean isTriggerAlarm() {
        return triggerAlarm;
    }

    public void setTriggerAlarm(boolean triggerAlarm) {
        this.triggerAlarm = triggerAlarm;
    }

    public CentralUnit getCentralUnit() {
        return centralUnit;
    }

    public void setCentralUnit(CentralUnit centralUnit) {
        this.centralUnit = centralUnit;
    }

    @Override
    public String toString() {
        return "Siren{" +
                "nameOfSiren='" + nameOfSiren + '\'' +
                ", triggerAlarm=" + triggerAlarm +
                ", centralUnit=" + centralUnit +
                '}';
    }
}
