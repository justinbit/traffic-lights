package sr.unasat.trafficlights.app;

public class TrafficLightSystem {
    public static void main(String[] args) {
        // Initialize traffic lights and vehicles
        TrafficLight north = new TrafficLight("North");
        TrafficLight south = new TrafficLight("South");
        TrafficLight east = new TrafficLight("East");
        TrafficLight west = new TrafficLight("West");

        // Add vehicles to each traffic light queue
        north.addVehicle(new Vehicle("N1", 1, "normal"));
        north.addVehicle(new Vehicle("N2", 2, "normal"));
        north.addVehicle(new Vehicle("N3", 3, "ambulance"));
        north.addVehicle(new Vehicle("N4", 4, "normal"));

        for (int i = 1; i <= 18; i++) {
            if (i == 17) {
                south.addVehicle(new Vehicle("S" + i, i, "firetruck"));
            } else {
                south.addVehicle(new Vehicle("S" + i, i, "normal"));
            }
        }

        for (int i = 1; i <= 5; i++) {
            east.addVehicle(new Vehicle("E" + i, i, "normal"));
        }

        for (int i = 1; i <= 14; i++) {
            if (i == 9) {
                west.addVehicle(new Vehicle("W" + i, i, "police"));
            } else {
                west.addVehicle(new Vehicle("W" + i, i, "normal"));
            }
        }

        // Set sensors
        east.sensor1 = true;
        south.sensor2 = true;
        west.sensor3 = true;
        north.sensor4 = true;

        // Process traffic lights in sequence
        for (int i = 0; i < 4; i++) {
            switch (i) {
                case 0: north.process(); break;
                case 1: south.process(); break;
                case 2: east.process(); break;
                case 3: west.process(); break;
            }
        }

        // Reverse Playback (simplified)
        Stack<Vehicle> reversePlayback = new Stack<>();
        while (!north.getQueue().isEmpty()) reversePlayback.push(north.getQueue().dequeue());
        while (!south.getQueue().isEmpty()) reversePlayback.push(south.getQueue().dequeue());
        while (!east.getQueue().isEmpty()) reversePlayback.push(east.getQueue().dequeue());
        while (!west.getQueue().isEmpty()) reversePlayback.push(west.getQueue().dequeue());

        while (!reversePlayback.isEmpty()) {
            Vehicle vehicle = reversePlayback.pop();
            switch (vehicle.licensePlate.charAt(0)) {
                case 'N': north.addVehicle(vehicle); break;
                case 'S': south.addVehicle(vehicle); break;
                case 'E': east.addVehicle(vehicle); break;
                case 'W': west.addVehicle(vehicle); break;
            }
        }
    }
}
