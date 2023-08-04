import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ToyStore toyStore = new ToyStore();
        toyStore.addToy(new Toy(1, "Toy 1", 10, 30));
        toyStore.addToy(new Toy(2, "Toy 2", 5, 20));
        toyStore.addToy(new Toy(3, "Toy 3", 8, 25));

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n===== Toy Store Menu =====");
            System.out.println("1. Update Toy Weight");
            System.out.println("2. Get Prize Toy");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            if (choice == 1) {
                System.out.print("Enter toy ID: ");
                int toyId = scanner.nextInt();
                System.out.print("Enter new weight: ");
                double newWeight = scanner.nextDouble();
                toyStore.updateToyWeight(toyId, newWeight);
            } else if (choice == 2) {
                Toy prizeToy = toyStore.getPrizeToy();
                if (prizeToy != null) {
                    System.out.println("Congratulations! You won: " + prizeToy.getName());
                    toyStore.decreaseToyQuantity(prizeToy);
                    toyStore.savePrizeToyToFile(prizeToy, "prize_toys.txt");
                } else {
                    System.out.println("No toys available for the prize.");
                }
            } else if (choice == 3) {
                break;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
        scanner.close();
    }
}