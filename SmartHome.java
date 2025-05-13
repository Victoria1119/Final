import java.util.*;

public class SmartHome {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Stack<Room> roomStack = new Stack<>();

        boolean running = true;

        while (running) {
            System.out.println("\nEnter a room name (EXIT to stop): ");
            String roomName = scanner.nextLine();
            if (roomName.equalsIgnoreCase("exit")) {
                running = false;
                break;
            }

            Room room = new Room(roomName);

            // Add Light to room
            System.out.println("Enter light name: ");
            String lightName = scanner.nextLine();
            Light light = new Light(lightName);
            room.addDevice(light);

            // Set Light settings
            System.out.println("Set light brightness (0-10): ");
            int lightSetting = Integer.parseInt(scanner.nextLine());
            light.setSetting(lightSetting);

            System.out.println("Set light color: ");
            String lightColor = scanner.nextLine();
            light.setColor(lightColor);

            // Add Thermostat to room
            System.out.println("Enter thermostat name: ");
            String thermostatName = scanner.nextLine();
            Thermostat thermostat = new Thermostat(thermostatName);
            room.addDevice(thermostat);

            // Set Thermostat temperature
           int thermostatTemp=0;
           boolean validTemp=false;
           while (!validTemp){
            System.out.println("Set thermostat temperature: ");
            String tempInput=scanner.nextLine();
            try{
                thermostatTemp=Integer.parseInt(tempInput);
                validTemp=true;
            }
            catch(NumberFormatException e){
                try{
                    throw new InvalidTempException("Input must be an integer.");}
                    catch(InvalidTempException exception){
                        System.out.println(exception.getMessage());
                    }
                }

           } thermostat.setTemp(thermostatTemp);

            roomStack.push(room);
            System.out.println("Room added: " + room.getRoomName());

            // Show current room devices
            System.out.println("\nCurrent room:");
            room.showDevices();

            // Ask user if they want to continue adding rooms or stop
            System.out.println("\nWould you like to add another room? (Y or N)");
            String response = scanner.nextLine();
            if (!response.equalsIgnoreCase("y")) {
                running = false;
            }
        }

        // After user chooses to stop, show all rooms in the stack
        System.out.println("\nRooms in Manager:");
        for (int i = 0; i < roomStack.size(); i++) {
            Room r = roomStack.get(i);  // Get the room at index i
            r.showDevices();  // Print the devices in the room
        }

        // After user chooses to stop, show all rooms in the stack
        System.out.println("Do you want to remove the last room entry? Y or N");
        String removeLast = scanner.nextLine();

// Check if user wants to remove the last room
if (removeLast.equalsIgnoreCase("Y")) {
    if (!roomStack.isEmpty()) {
        // Remove and display the last room
        Room removedRoom = roomStack.pop();
        System.out.println("Room removed: " + removedRoom.getRoomName());
        removedRoom.showDevices();
    } else {
        System.out.println("No rooms to remove.");
    }
} else {
    System.out.println("No room removed.");
}

System.out.println("Room and Device List:");
for (int i = 0; i < roomStack.size(); i++) {
    Room r = roomStack.get(i);  // Get the room at index i
    r.showDevices();  // Print the devices in the room
}
System.out.println("\nWould you like to power on or off any device? Y or N");
String powerChoice = scanner.nextLine();

if (powerChoice.equalsIgnoreCase("y")) {
    boolean continuePoweringDevices = true; // Flag to control the loop

    while (continuePoweringDevices) {
        // Show all rooms and devices
        System.out.println("\nAvailable devices:");
        for (int i = 0; i < roomStack.size(); i++) {
            Room r = roomStack.get(i);  // Get the room at index i
            r.showDevices();  // Print the devices in the room
        }

        // Select room to power a device
        System.out.println("Enter the room name to select the device:");
        String roomToPower = scanner.nextLine();
        Room selectedRoom = null;
        for (int i = 0; i < roomStack.size(); i++) {
            if (roomStack.get(i).getRoomName().equalsIgnoreCase(roomToPower)) {
                selectedRoom = roomStack.get(i);
                break;
            }
        }

        if (selectedRoom != null) {
            System.out.println("Enter the device name to power on or off:");
            String deviceToPower = scanner.nextLine();
            Device selectedDevice = null;

            // Find the device in the selected room
            for (Device d : selectedRoom.devices) {
                if (d.getName().equalsIgnoreCase(deviceToPower)) {
                    selectedDevice = d;
                    break;
                }
            }

            if (selectedDevice != null) {
                // Ask user whether to power the device on or off
                System.out.println("Do you want to power on or off the device? (on/off)");
                String powerAction = scanner.nextLine();
                if (powerAction.equalsIgnoreCase("on")) {
                    selectedDevice.powerOn();
                } else if (powerAction.equalsIgnoreCase("off")) {
                    selectedDevice.powerOff();
                } else {
                    System.out.println("Invalid action. Please enter 'on' or 'off'.");
                }
            } else {
                System.out.println("Device not found in the selected room.");
            }
        } else {
            System.out.println("Room not found.");
        }

        // Ask if the user wants to continue powering more devices
        System.out.println("\nDo you want to power on/off another device? Y or N");
        String continueChoice = scanner.nextLine();
        if (!continueChoice.equalsIgnoreCase("y")) {
            continuePoweringDevices = false;
        }
    }
} else {
    System.out.println("No device changes.");
}

System.out.println("Summary:");
for (int i = 0; i < roomStack.size(); i++) {
    Room r = roomStack.get(i);  // Get the room at index i
    r.showDevices();  // Print the devices in the room
}
scanner.close();
}}