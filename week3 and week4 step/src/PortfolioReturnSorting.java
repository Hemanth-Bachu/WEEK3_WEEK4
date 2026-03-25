import java.util.*;

class Asset {
    String ticker;
    double returnRate; // in percent
    double volatility; // in percent

    public Asset(String ticker, double returnRate, double volatility) {
        this.ticker = ticker;
        this.returnRate = returnRate;
        this.volatility = volatility;
    }

    @Override
    public String toString() {
        return ticker + ":" + returnRate + "%";
    }
}

public class PortfolioReturnSorting {

    // Merge Sort: stable by returnRate ascending
    public static Asset[] mergeSort(Asset[] assets) {
        if (assets.length <= 1) return assets;

        int mid = assets.length / 2;
        Asset[] left = mergeSort(Arrays.copyOfRange(assets, 0, mid));
        Asset[] right = mergeSort(Arrays.copyOfRange(assets, mid, assets.length));

        return merge(left, right);
    }

    private static Asset[] merge(Asset[] left, Asset[] right) {
        Asset[] merged = new Asset[left.length + right.length];
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (left[i].returnRate <= right[j].returnRate) { // stable
                merged[k++] = left[i++];
            } else {
                merged[k++] = right[j++];
            }
        }
        while (i < left.length) merged[k++] = left[i++];
        while (j < right.length) merged[k++] = right[j++];

        return merged;
    }

    // Quick Sort: descending returnRate, ascending volatility, median-of-3 pivot
    public static void quickSortDesc(Asset[] assets, int low, int high) {
        if (low < high) {
            int pivotIndex = medianOfThreePivot(assets, low, high);
            pivotIndex = lomutoPartition(assets, low, high, pivotIndex);
            quickSortDesc(assets, low, pivotIndex - 1);
            quickSortDesc(assets, pivotIndex + 1, high);
        }
    }

    private static int medianOfThreePivot(Asset[] arr, int low, int high) {
        int mid = low + (high - low) / 2;
        double a = arr[low].returnRate;
        double b = arr[mid].returnRate;
        double c = arr[high].returnRate;

        int medianIndex = low;
        if ((a > b) != (a > c)) medianIndex = low;
        else if ((b > a) != (b > c)) medianIndex = mid;
        else medianIndex = high;

        return medianIndex;
    }

    private static int lomutoPartition(Asset[] arr, int low, int high, int pivotIndex) {
        Asset pivot = arr[pivotIndex];
        swap(arr, pivotIndex, high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j].returnRate > pivot.returnRate ||
                    (arr[j].returnRate == pivot.returnRate && arr[j].volatility < pivot.volatility)) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    private static void swap(Asset[] arr, int i, int j) {
        Asset temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {

        Asset[] assets = {
                new Asset("AAPL", 12, 20),
                new Asset("TSLA", 8, 30),
                new Asset("GOOG", 15, 18)
        };

        System.out.println("=== Merge Sort (asc) ===");
        Asset[] mergeSorted = mergeSort(assets);
        System.out.println(Arrays.toString(mergeSorted));

        System.out.println("\n=== Quick Sort (desc returnRate + asc volatility) ===");
        Asset[] quickSorted = Arrays.copyOf(assets, assets.length);
        quickSortDesc(quickSorted, 0, quickSorted.length - 1);
        System.out.println(Arrays.toString(quickSorted));
    }
}