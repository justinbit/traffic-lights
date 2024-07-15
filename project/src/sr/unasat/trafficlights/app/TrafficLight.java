package sr.unasat.trafficlights.app;

public class TrafficLight {
    private Queue<Vehicle> queue = new Queue<>();
    private String direction;
    boolean sensor1, sensor2, sensor3, sensor4;

    public TrafficLight(String direction) {
        this.direction = direction;
    }

    public void addVehicle(Vehicle vehicle) {
        queue.enqueue(vehicle);
    }

    public void process() {
        // Implement the logic based on sensors
        if (direction.equals("East") && sensor1 && queue.isEmpty()) {
            System.out.println("Skipping green light for East due to sensor 1.");
        } else if (direction.equals("South") && sensor2 && queue.size() > 10) {
            System.out.println("South light remains green due to sensor 2 until 10 vehicles pass.");
            while (queue.size() > 10) {
                queue.dequeue();
            }
        } else if (direction.equals("West") && sensor3 && (queue.isEmpty() || queue.size() > 10)) {
            if (queue.isEmpty()) {
                System.out.println("Skipping green light for West due to sensor 1 part of sensor 3.");
            } else {
                System.out.println("West light remains green due to sensor 2 part of sensor 3 until 10 vehicles pass.");
                while (queue.size() > 10) {
                    queue.dequeue();
                }
            }
        } else if (direction.equals("North") && sensor4 && queue.size() < 5) {
            System.out.println("North light remains green until all vehicles pass due to sensor 4.");
            while (!queue.isEmpty()) {
                queue.dequeue();
            }
        } else {
            int count = Math.min(queue.size(), 5);
            System.out.println(direction + " light is green for " + count + " vehicles.");
            for (int i = 0; i < count; i++) {
                queue.dequeue();
            }
        }
    }

    public Queue<Vehicle> getQueue() {
        return queue;
    }
}
