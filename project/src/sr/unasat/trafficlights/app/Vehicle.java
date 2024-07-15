package sr.unasat.trafficlights.app;

public class Vehicle
{
        String licensePlate;
        int position;
        String type; // "normal", "ambulance", "firetruck", "police"

        public Vehicle(String licensePlate, int position, String type) {
            this.licensePlate = licensePlate;
            this.position = position;
            this.type = type;
        }
    }