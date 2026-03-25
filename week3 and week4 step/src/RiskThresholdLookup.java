import java.util.*;

public class RiskThresholdLookup {

    // Linear search for exact match
    public static void linearSearch(int[] risks, int target) {
        int comparisons = 0;
        boolean found = false;

        for (int i = 0; i < risks.length; i++) {
            comparisons++;
            if (risks[i] == target) {
                System.out.println("Linear search: Found " + target + " at index " + i);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Linear search: " + target + " not found");
        }

        System.out.println("Comparisons made: " + comparisons);
    }

    // Binary search exact match
    public static void binarySearchExact(int[] risks, int target) {
        int low = 0, high = risks.length - 1;
        int comparisons = 0;
        int foundIndex = -1;

        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;
            if (risks[mid] == target) {
                foundIndex = mid;
                break;
            } else if (risks[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        if (foundIndex != -1) {
            System.out.println("Binary search: Found " + target + " at index " + foundIndex);
        } else {
            System.out.println("Binary search: " + target + " not found");
        }
        System.out.println("Comparisons made: " + comparisons);
    }

    // Binary search floor and ceiling
    public static void binaryFloorCeiling(int[] risks, int target) {
        int low = 0, high = risks.length - 1;
        int comparisons = 0;
        int floor = Integer.MIN_VALUE;
        int ceiling = Integer.MAX_VALUE;

        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;

            if (risks[mid] == target) {
                floor = ceiling = risks[mid];
                break;
            } else if (risks[mid] < target) {
                floor = risks[mid];
                low = mid + 1;
            } else {
                ceiling = risks[mid];
                high = mid - 1;
            }
        }

        System.out.println("Binary floor: " + (floor == Integer.MIN_VALUE ? "N/A" : floor));
        System.out.println("Binary ceiling: " + (ceiling == Integer.MAX_VALUE ? "N/A" : ceiling));
        System.out.println("Comparisons made: " + comparisons);
    }

    public static void main(String[] args) {
        int[] risks = {10, 25, 50, 100}; // already sorted
        int target = 30;

        System.out.println("Risk bands: " + Arrays.toString(risks));

        System.out.println("\n=== Linear Search ===");
        linearSearch(risks, target);

        System.out.println("\n=== Binary Search Exact Match ===");
        binarySearchExact(risks, target);

        System.out.println("\n=== Binary Floor and Ceiling ===");
        binaryFloorCeiling(risks, target);
    }
}