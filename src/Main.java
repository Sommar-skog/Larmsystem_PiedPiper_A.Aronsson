public class Main {
    public static void main(String[] args) {

        CentralUnit centralunit = new CentralUnit("Centralenhet (garage)", 2);

        Room garage = new Room(1, 1, "Garage", false, true, true);
        Room kitchen = new Room(1, 0, "Kök", false, true, false);
        Room livingroom = new Room(2, 1, "Vardagsrum", true, true, true);
        Room bedroom1 = new Room(2, 0, "Erlich Bachmans sovrum (sovrum 1)", false, true, false);
        Room bedroom2 = new Room(1, 1, "Jian Yangs sovrum (sovrum 2)", false, true, true);
        Room bedroom3 = new Room(1, 0, "Ritchard Hendricks sovrum (sovrum 3)", false, true, false);
        Room bedroom4 = new Room(2, 0, "Bertram Gilfoyles sovrum (sovrum 4)", false, true, false);
        Room bedroom5 = new Room(1, 0, "Dinesh Chugtais sovrum (sovrum 5)", false, true, false);
        Room entranceHall = new Room(1, 1, "Hall", true, true, true);
        Room garden = new Room(0, 0, "Trädgården (poolområdet)", true, false, false);

        centralunit.addRoom(garage, kitchen, livingroom, bedroom1, bedroom2, bedroom3, bedroom4, bedroom5, entranceHall, garden);

        centralunit.mainMenu();

    }
}