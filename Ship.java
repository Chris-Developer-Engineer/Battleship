import java.util.ArrayList;

public class Ship {

    //variables
    private ArrayList<String> location = new ArrayList<String>();
    private int size;
    private String name;

    //Constructor
    public Ship(String name,  int size) {
        this.size = size;
        this.name = name;
    }

    //Get the ship name
    public String getShipName() {
        return name;
    }

    //Get the ship size
    public int getShipSize() {
        return size;
    }

    //Get the current ship locations
    public ArrayList<String> getCurrLocations() {
        return location;
    }

    //Set the ship locations
    public void setShipLocations(ArrayList<String> locationToSet) {
        this.location.addAll(locationToSet);
    }

    //Check the user's answer
    public String check(String answer) {
        String result = "miss";
        if(location.contains(answer)) {
            location.remove(answer);
            result = location.isEmpty() ? "kill" : "hit";
        }

        return result;
    }
}
