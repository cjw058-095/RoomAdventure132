import java.util.Scanner;

public class RoomAdventure {
    // class variables
    private static Room currentRoom;
    private static String[] inventory = {null, null, null, null, null};
    private static String status;

    // constant
    final private static String Default_Status = "Sorry, I do not understand [verb] [noun]. Valid verbs include 'go', 'look', and 'take'. ";

    public static void main(String[] args) {
        setupGame();
        Scanner s = new Scanner(System.in); // Move scanner outside loop

        while (true) { 
            System.out.println(currentRoom.toString());
            System.out.println("Inventory: ");

            for(int i = 0; i < inventory.length; i++){
                System.out.print(inventory[i] + " ");
            }

            System.out.println("\nWhat would you like to do? ");

            String input = s.nextLine();

            // .split takes words and makes them a list
            // each item is divided by the arg in split
            String[] words = input.split(" ");

            if (words.length != 2){
                status = Default_Status;
            } else { // Added else block
                String verb = words[0];
                String noun = words[1];

                switch(verb){
                    case "go":
                        handleGo(noun);
                        break;
                    case "look":
                        handleLook(noun);
                        break;
                    case "take":
                        handleTake(noun);
                        break;
                    default: 
                        status = Default_Status;
                        break; // Added break
                }
            }
            
            System.out.println(status);
        }
    }

    private static void handleGo(String noun){
        status = "I don't see that room";
        String[] directions = currentRoom.getExitDirections();   
        Room[] rooms = currentRoom.getExitDestinations();

        for (int i=0; i < directions.length; i++){
            // for strings we use .equals() to compare
            if (noun.equals(directions[i])){
                if (directions[i].equals("escape!")){
                    System.out.println("You did it! You got out!");
                    System.exit(0);
                }
                currentRoom = rooms[i];
                status = "Changed Room";
            }
        }
    }

    private static void handleLook(String noun){
        status = "I do not see that item!";
        String[] items = currentRoom.getItems();
        String[] descriptions = currentRoom.getItemDescription();

        for (int i=0; i < items.length; i++){
            if (noun.equals(items[i])){
                if ((items[i]).equals("ShyGuy")){
                    System.out.println(descriptions[i]);
                    System.exit(0);
                }

                status = descriptions[i];
            }
        }
    }

    private static void handleTake(String noun){
        status = "I cannot grab that";
        String[] grabs = currentRoom.getGrabbables();

        // maybe make a addtoinventory function?
        // maybe add a item like a bag to increase inventory
        for (int i = 0; i < grabs.length; i++){
            if (noun.equals(grabs[i])){
                for (int j = 0; j < inventory.length; j++){
                    if (inventory[j] == null){
                        inventory[j] = noun;
                        // Maybe say what item was added
                        status = "Added item to inventory";
                        break;
                    }
                }
            }
        }
    }

    public static void setupGame() {
        Room room1 = new Room("Room 1");
        Room room2 = new Room("Room 2");
        Room room3 = new Room("Room 3");
        Room room4 = new Room("Room 4");
        Room room5 = new Room("Room 5");

        // Room 1 setup
        String[] room1ExitDirections = {"south", "east"};
        Room[] room1ExitDestinations = {room2, room4}; // assuming south leads nowhere
        String[] room1Items = {"chair", "desk"};
        String[] room1ItemDescriptions = {
            "It is a chair, there is a nail on it.",
            "It's a desk, there is an apple on it."
        };
        String[] room1Grabbables = {"nail", "apple"};

        room1.setExitDirections(room1ExitDirections);
        room1.setExitDestinations(room1ExitDestinations);
        room1.setItems(room1Items);
        room1.setItemDescription(room1ItemDescriptions);
        room1.setGrabbables(room1Grabbables);

        // Room 2 setup
        String[] room2ExitDirections = {"north", "east"};
        Room[] room2ExitDestinations = {room1, room3}; // assuming north leads nowhere
        String[] room2Items = {"chair", "desk"};
        String[] room2ItemDescriptions = {
            "It is a chair",
            "It's a desk, there is a key on it."
        };
        String[] room2Grabbables = {"key"};

        room2.setExitDirections(room2ExitDirections);
        room2.setExitDestinations(room2ExitDestinations);
        room2.setItems(room2Items);
        room2.setItemDescription(room2ItemDescriptions);
        room2.setGrabbables(room2Grabbables);

        String[] room3ExitDirections = {"north", "west"};
        Room[] room3ExitDestinations = {room4, room2};
        String[] room3Items = {"Game Disk", "Body"};
        String[] room3ItemDescriptions = 
        {"A video game disk. Says Helldivers 2. Seems like a good game.", 
            "A body on the floor. Theres a code on it. My beloved Josh. Weird."};
        String[] room3Grabbables = {"Code"};
        

        room3.setExitDirections(room3ExitDirections);
        room3.setExitDestinations(room3ExitDestinations);
        room3.setItems(room3Items);
        room3.setItemDescription(room3ItemDescriptions);
        room3.setGrabbables(room3Grabbables);

        String[] room4ExitDirections = {"west", "south", "north"};
        Room[] room4ExitDestinations = {room1, room3, room5};
        String[] room4Items = {"ShyGuy", "Victim"};
        String[] room4ItemDescriptions = {
    "                       ______\n" +
    "        .-\"      \"-.\n" +
    "       /            \\\n" +
    "      |,  .-.  .-.  ,|\n" +
    "      | )(_o/  \\o_)( |\n" +
    "      |/     /\\     \\|\n" +
    "      (_     ^^     _)\n" +
    "       \\__|IIIIII|__/\n" +
    "        | \\IIIIII/ |\n" +
    "        \\          /\n" +
    "         `--------'\n" +
    "      ___/        \\___\n" +
    "     /___\\        /___\\" ,
    "Someone looked at the wrong person."
};

        String[] room4Grabbables = {"null"};
        

        room4.setExitDirections(room4ExitDirections);
        room4.setExitDestinations(room4ExitDestinations);
        room4.setItems(room4Items);
        room4.setItemDescription(room4ItemDescriptions);
        room4.setGrabbables(room4Grabbables);

         String[] room5ExitDirections = {"south", "escape!"};
        Room[] room5ExitDestinations = {null, null}; // assuming south leads nowhere
        String[] room5Items = {null, null};
        String[] room5ItemDescriptions = {
            null,
            null
        };
        String[] room5Grabbables = {null, null};

        room5.setExitDirections(room5ExitDirections);
        room5.setExitDestinations(room5ExitDestinations);
        room5.setItems(room5Items);
        room5.setItemDescription(room5ItemDescriptions);
        room5.setGrabbables(room5Grabbables);




        currentRoom = room1;
    }
}

class Room {
    private String name;
    private String[] exitDirections; // north, south, east, west
    private Room[] exitDestinations;
    private String[] items;
    private String[] itemDescription;
    private String[] grabbables;

    public Room(String name) {
        this.name = name;
    }

    public void setExitDirections(String[] exitDirections) {
        this.exitDirections = exitDirections;
    }

    public String[] getExitDirections() {
        return exitDirections;
    }

    public void setExitDestinations(Room[] exitDestinations) {
        this.exitDestinations = exitDestinations;
    }

    public Room[] getExitDestinations() {
        return exitDestinations;
    }

    public void setItems(String[] items) {
        this.items = items;
    }

    public String[] getItems() {
        return items;
    }

    public void setItemDescription(String[] itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String[] getItemDescription() {
        return itemDescription;
    }

    public void setGrabbables(String[] grabbables) {
        this.grabbables = grabbables;
    }

    public String[] getGrabbables() {
        return grabbables;
    }

    @Override
    public String toString() {
        String result = "\nLocation: " + name;

        result += "\nYou see: ";

        // for i loop
        for (int i = 0; i < items.length; i++) {
            result += items[i] + " ";
        }

        result += "\nExits: ";
        //for each loop
        for (String direction : exitDirections) {
            result += direction + " ";
        }

        return result + "\n";
    }
}