import java.util.*;

class Client {
    String name;
    int riskScore;
    double accountBalance;

    public Client(String name, int riskScore, double accountBalance) {
        this.name = name;
        this.riskScore = riskScore;
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return name + "(" + riskScore + ", $" + accountBalance + ")";
    }
}

public class ClientRiskRanking {

    // Bubble Sort ascending by riskScore
    public static void bubbleSortRiskAsc(Client[] clients) {
        int n = clients.length;
        int swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (clients[j].riskScore > clients[j + 1].riskScore) {
                    Client temp = clients[j];
                    clients[j] = clients[j + 1];
                    clients[j + 1] = temp;
                    swaps++;
                    swapped = true;

                    // Visualize swap
                    System.out.println("Swapped: " + clients[j].name + " with " + clients[j+1].name);
                }
            }
            if (!swapped) break; // Early termination
        }

        System.out.println("\nBubbleSort ASC by RiskScore: " + Arrays.toString(clients));
        System.out.println("Total swaps: " + swaps);
    }

    // Insertion Sort descending by riskScore, then accountBalance
    public static void insertionSortRiskDesc(Client[] clients) {
        int n = clients.length;

        for (int i = 1; i < n; i++) {
            Client key = clients[i];
            int j = i - 1;

            // Descending: first riskScore, then accountBalance
            while (j >= 0 &&
                    (clients[j].riskScore < key.riskScore ||
                            (clients[j].riskScore == key.riskScore && clients[j].accountBalance < key.accountBalance))) {
                clients[j + 1] = clients[j];
                j--;
            }
            clients[j + 1] = key;
        }

        System.out.println("\nInsertionSort DESC by RiskScore+Balance: " + Arrays.toString(clients));
    }

    // Top N risk clients
    public static void displayTopRisks(Client[] clients, int topN) {
        System.out.println("\nTop " + topN + " highest risk clients:");
        for (int i = 0; i < Math.min(topN, clients.length); i++) {
            System.out.println(clients[i]);
        }
    }

    public static void main(String[] args) {

        Client[] clients = {
                new Client("clientC", 80, 5000),
                new Client("clientA", 20, 2000),
                new Client("clientB", 50, 7000),
                new Client("clientD", 80, 3000),
                new Client("clientE", 50, 6000)
        };

        System.out.println("=== Bubble Sort Ascending (riskScore) ===");
        bubbleSortRiskAsc(Arrays.copyOf(clients, clients.length)); // small demo

        System.out.println("\n=== Insertion Sort Descending (riskScore + accountBalance) ===");
        insertionSortRiskDesc(Arrays.copyOf(clients, clients.length));

        displayTopRisks(clients, 3); // top 3 for demo
    }
}