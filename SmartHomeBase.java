import java.util.*;

interface Controllable {
    void powerOn();
    void powerOff();
}

abstract class Device implements Controllable {
    String name;
    boolean power;

    public Device(String name) {
        this.name = name;
        this.power = false;
    }

    public String getName() { return name; }

    public boolean mode() { return power; }

    @Override
    public void powerOn() {
        power = true;
        System.out.println(name + " is on.");
    }

    @Override
    public void powerOff() {
        power = false;
        System.out.println(name + " is off.");
    }

    public abstract String deviceInformation();
}

class Light extends Device {
    int setting;
    String color;

    public Light(String name) {
        super(name);
        this.setting = 5;
        this.color = "White";
    }

    public void setSetting(int num) {
        this.setting = Math.max(0, Math.min(10, num));
        System.out.println(name + " setting: " + setting);
    }

    public void setColor(String color) {
        this.color = color;
        System.out.println(name + " color: " + color);
    }

    public String getColor() { return color; }

    @Override
    public String deviceInformation() {
        return name + " : Setting: " + setting + ", Color: " + color + ", Power: " + (power ? "On" : "Off");
    }
}

class Thermostat extends Device {
    int temp;

    public Thermostat(String name) {
        super(name);
        this.temp = 60;
    }

    public void setTemp(int temp) {
        this.temp = temp;
        System.out.println(name + " temperature set to: " + temp);
    }

    @Override
    public String deviceInformation() {
        return name + " : Temperature: " + temp + ", Power: " + (power ? "On" : "Off");
    }
}

class Room {
    String roomName;
    List<Device> devices;

    public Room(String name) {
        this.roomName = name;
        this.devices = new ArrayList<>();
    }

    public void addDevice(Device device) {
        devices.add(device);
        System.out.println("Added " + device.getName() + " to " + roomName);
    }

    public void showDevices() {
        System.out.println("Devices in " + roomName + ":");
        for (Device d : devices) {
            System.out.println(d.deviceInformation());
        }
    }

    public String getRoomName() {
        return roomName;
    }
}