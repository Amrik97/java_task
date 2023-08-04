import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ToyStore {
    private List<Toy> toys;

    public ToyStore() {
        toys = new ArrayList<>();
    }

    public void addToy(Toy toy) {
        toys.add(toy);
    }

    public void updateToyWeight(int toyId, double newWeight) {
        for (Toy toy : toys) {
            if (toy.getId() == toyId) {
                toy.setWeight(newWeight);
                break;
            }
        }
    }

    public Toy getPrizeToy() {
        if (toys.isEmpty()) {
            return null;
        }

        double totalWeight = toys.stream().mapToDouble(Toy::getWeight).sum();
        double randomWeight = Math.random() * totalWeight;

        double cumulativeWeight = 0;
        for (Toy toy : toys) {
            cumulativeWeight += toy.getWeight();
            if (randomWeight <= cumulativeWeight) {
                return toy;
            }
        }

        // This should not happen, but return the last toy as a fallback
        return toys.get(toys.size() - 1);
    }

    public void savePrizeToyToFile(Toy prizeToy, String fileName) {
        try (FileWriter fileWriter = new FileWriter(fileName, true)) {
            fileWriter.write("Prize Toy: " + prizeToy.getName() + "\n");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public void decreaseToyQuantity(Toy toy) {
        toy.quantity--;
    }
}
