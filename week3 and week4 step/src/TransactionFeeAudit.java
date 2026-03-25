import java.util.*;

class Transaction {
    String id;
    double fee;
    String timestamp;

    public Transaction(String id, double fee, String timestamp) {
        this.id = id;
        this.fee = fee;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return id + ": " + fee + "@" + timestamp;
    }
}

public class TransactionFeeAudit {

    // Bubble Sort: fees only
    public static void bubbleSortFees(List<Transaction> transactions) {
        int n = transactions.size();
        int passes = 0;
        int swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            passes++;

            for (int j = 0; j < n - 1 - i; j++) {
                if (transactions.get(j).fee > transactions.get(j + 1).fee) {
                    Collections.swap(transactions, j, j + 1);
                    swaps++;
                    swapped = true;
                }
            }

            if (!swapped) break; // Early termination
        }

        System.out.println("BubbleSort (fees): " + transactions);
        System.out.println("Passes: " + passes + ", Swaps: " + swaps);
    }

    // Insertion Sort: fee + timestamp
    public static void insertionSortFeeTimestamp(List<Transaction> transactions) {
        int n = transactions.size();

        for (int i = 1; i < n; i++) {
            Transaction key = transactions.get(i);
            int j = i - 1;

            // Compare fee first, then timestamp (stable)
            while (j >= 0 &&
                    (transactions.get(j).fee > key.fee ||
                            (transactions.get(j).fee == key.fee &&
                                    transactions.get(j).timestamp.compareTo(key.timestamp) > 0))) {
                transactions.set(j + 1, transactions.get(j));
                j--;
            }

            transactions.set(j + 1, key);
        }

        System.out.println("InsertionSort (fee+ts): " + transactions);
    }

    // Detect high-fee outliers (>50)
    public static void detectHighFeeOutliers(List<Transaction> transactions) {
        List<Transaction> outliers = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.fee > 50) {
                outliers.add(t);
            }
        }

        if (outliers.isEmpty()) {
            System.out.println("High-fee outliers: none");
        } else {
            System.out.println("High-fee outliers: " + outliers);
        }
    }

    public static void main(String[] args) {
        List<Transaction> smallBatch = new ArrayList<>();
        smallBatch.add(new Transaction("id1", 10.5, "10:00"));
        smallBatch.add(new Transaction("id2", 25.0, "09:30"));
        smallBatch.add(new Transaction("id3", 5.0, "10:15"));

        System.out.println("=== Bubble Sort (Small Batch) ===");
        bubbleSortFees(new ArrayList<>(smallBatch)); // Pass a copy
        detectHighFeeOutliers(smallBatch);

        System.out.println("\n=== Insertion Sort (Medium Batch) ===");
        List<Transaction> mediumBatch = new ArrayList<>();
        mediumBatch.add(new Transaction("id1", 10.5, "10:00"));
        mediumBatch.add(new Transaction("id2", 25.0, "09:30"));
        mediumBatch.add(new Transaction("id3", 5.0, "10:15"));

        insertionSortFeeTimestamp(mediumBatch);
        detectHighFeeOutliers(mediumBatch);
    }
}