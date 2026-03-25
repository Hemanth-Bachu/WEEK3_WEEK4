import java.util.*;

class Trade {
    String id;
    int volume;

    public Trade(String id, int volume) {
        this.id = id;
        this.volume = volume;
    }

    @Override
    public String toString() {
        return id + ":" + volume;
    }
}

public class TradeVolumeAnalysis {

    // Merge Sort: ascending, stable
    public static Trade[] mergeSort(Trade[] trades) {
        if (trades.length <= 1) return trades;

        int mid = trades.length / 2;
        Trade[] left = mergeSort(Arrays.copyOfRange(trades, 0, mid));
        Trade[] right = mergeSort(Arrays.copyOfRange(trades, mid, trades.length));

        return merge(left, right);
    }

    private static Trade[] merge(Trade[] left, Trade[] right) {
        Trade[] merged = new Trade[left.length + right.length];
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (left[i].volume <= right[j].volume) { // stable
                merged[k++] = left[i++];
            } else {
                merged[k++] = right[j++];
            }
        }
        while (i < left.length) merged[k++] = left[i++];
        while (j < right.length) merged[k++] = right[j++];

        return merged;
    }

    // Quick Sort: descending, in-place
    public static void quickSortDesc(Trade[] trades, int low, int high) {
        if (low < high) {
            int pivotIndex = lomutoPartition(trades, low, high);
            quickSortDesc(trades, low, pivotIndex - 1);
            quickSortDesc(trades, pivotIndex + 1, high);
        }
    }

    private static int lomutoPartition(Trade[] trades, int low, int high) {
        Trade pivot = trades[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (trades[j].volume >= pivot.volume) { // descending
                i++;
                Trade temp = trades[i];
                trades[i] = trades[j];
                trades[j] = temp;
            }
        }

        Trade temp = trades[i + 1];
        trades[i + 1] = trades[high];
        trades[high] = temp;
        return i + 1;
    }

    // Merge two sorted lists (ascending) and compute total volume
    public static Trade[] mergeTwoSortedLists(Trade[] list1, Trade[] list2) {
        Trade[] merged = new Trade[list1.length + list2.length];
        int i = 0, j = 0, k = 0;
        int totalVolume = 0;

        while (i < list1.length && j < list2.length) {
            if (list1[i].volume <= list2[j].volume) {
                merged[k++] = list1[i++];
            } else {
                merged[k++] = list2[j++];
            }
        }
        while (i < list1.length) merged[k++] = list1[i++];
        while (j < list2.length) merged[k++] = list2[j++];

        // Compute total volume
        for (Trade t : merged) totalVolume += t.volume;

        System.out.println("Merged total volume: " + totalVolume);
        return merged;
    }

    public static void main(String[] args) {

        Trade[] trades = {
                new Trade("trade3", 500),
                new Trade("trade1", 100),
                new Trade("trade2", 300)
        };

        System.out.println("=== Merge Sort (asc) ===");
        Trade[] mergeSorted = mergeSort(trades);
        System.out.println(Arrays.toString(mergeSorted));

        System.out.println("\n=== Quick Sort (desc) ===");
        Trade[] quickSorted = Arrays.copyOf(trades, trades.length);
        quickSortDesc(quickSorted, 0, quickSorted.length - 1);
        System.out.println(Arrays.toString(quickSorted));

        System.out.println("\n=== Merge Morning + Afternoon Sessions ===");
        Trade[] morning = {
                new Trade("t1", 100),
                new Trade("t2", 300)
        };
        Trade[] afternoon = {
                new Trade("t3", 500)
        };
        Trade[] mergedSessions = mergeTwoSortedLists(mergeSort(morning), mergeSort(afternoon));
        System.out.println(Arrays.toString(mergedSessions));
    }
}