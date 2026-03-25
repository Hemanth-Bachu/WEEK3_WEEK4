import java.util.*;

public class AccountIDLookup {

    // Linear Search: first and last occurrence
    public static void linearSearch(String[] logs, String target) {
        int firstIndex = -1;
        int lastIndex = -1;
        int comparisons = 0;

        for (int i = 0; i < logs.length; i++) {
            comparisons++;
            if (logs[i].equals(target)) {
                if (firstIndex == -1) firstIndex = i;
                lastIndex = i;
            }
        }

        System.out.println("Linear Search for '" + target + "':");
        if (firstIndex != -1) {
            System.out.println("First occurrence: index " + firstIndex);
            System.out.println("Last occurrence: index " + lastIndex);
        } else {
            System.out.println("Not found");
        }
        System.out.println("Comparisons made: " + comparisons);
    }

    // Binary Search: sorted array
    public static void binarySearch(String[] logs, String target) {
        Arrays.sort(logs); // ensure sorted
        int comparisons = 0;
        int low = 0, high = logs.length - 1;
        int foundIndex = -1;

        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;
            int cmp = logs[mid].compareTo(target);

            if (cmp == 0) {
                foundIndex = mid;
                break;
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        // Count occurrences
        int count = 0;
        if (foundIndex != -1) {
            // scan left
            int i = foundIndex;
            while (i >= 0 && logs[i].equals(target)) {
                count++;
                i--;
            }
            // scan right (skip mid)
            i = foundIndex + 1;
            while (i < logs.length && logs[i].equals(target)) {
                count++;
                i++;
            }
        }

        System.out.println("\nBinary Search for '" + target + "':");
        if (foundIndex != -1) {
            System.out.println("One index found: " + foundIndex);
            System.out.println("Total occurrences: " + count);
        } else {
            System.out.println("Not found");
        }
        System.out.println("Comparisons made: " + comparisons);
    }

    public static void main(String[] args) {
        String[] logs = {"accB", "accA", "accB", "accC", "accB", "accD"};

        String target = "accB";

        System.out.println("Original Logs: " + Arrays.toString(logs));

        // Linear Search
        linearSearch(logs, target);

        // Binary Search
        binarySearch(logs, target);
    }
}