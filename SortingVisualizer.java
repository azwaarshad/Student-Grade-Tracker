import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class SortingVisualizer extends JPanel {
    private int[] array;
    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private final int BAR_WIDTH = 5;
    private final int NUM_BARS = WIDTH / BAR_WIDTH;
    private boolean sorting = false;
    private long[] sortingTimes; // To store sorting times for each algorithm

    public SortingVisualizer() {
        this.array = new int[NUM_BARS];
        this.sortingTimes = new long[8]; // Array to store times for 8 algorithms
        resetArray();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    public void resetArray() {
        Random rand = new Random();
        for (int i = 0; i < NUM_BARS; i++) {
            array[i] = rand.nextInt(HEIGHT);
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < array.length; i++) {
            int height = array[i];
            g.fillRect(i * BAR_WIDTH, HEIGHT - height, BAR_WIDTH, height);
        }
    }

    public void bubbleSort() {
        new Thread(() -> {
            sorting = true;
            long startTime = System.currentTimeMillis();
            for (int i = 0; i < array.length - 1; i++) {
                for (int j = 0; j < array.length - i - 1; j++) {
                    if (array[j] > array[j + 1]) {
                        int temp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = temp;
                        repaint();
                        sleep();
                    }
                }
            }
            long endTime = System.currentTimeMillis();
            sortingTimes[0] = endTime - startTime;
            sorting = false;
        }).start();
    }

    public void selectionSort() {
        new Thread(() -> {
            sorting = true;
            long startTime = System.currentTimeMillis();
            for (int i = 0; i < array.length - 1; i++) {
                int minIndex = i;
                for (int j = i + 1; j < array.length; j++) {
                    if (array[j] < array[minIndex]) {
                        minIndex = j;
                    }
                }
                int temp = array[minIndex];
                array[minIndex] = array[i];
                array[i] = temp;
                repaint();
                sleep();
            }
            long endTime = System.currentTimeMillis();
            sortingTimes[1] = endTime - startTime;
            sorting = false;
        }).start();
    }

    public void insertionSort() {
        new Thread(() -> {
            sorting = true;
            long startTime = System.currentTimeMillis();
            for (int i = 1; i < array.length; i++) {
                int key = array[i];
                int j = i - 1;
                while (j >= 0 && array[j] > key) {
                    array[j + 1] = array[j];
                    j = j - 1;
                    repaint();
                    sleep();
                }
                array[j + 1] = key;
                repaint();
                sleep();
            }
            long endTime = System.currentTimeMillis();
            sortingTimes[2] = endTime - startTime;
            sorting = false;
        }).start();
    }

    public void mergeSort() {
        new Thread(() -> {
            sorting = true;
            long startTime = System.currentTimeMillis();
            mergeSort(array, 0, array.length - 1);
            long endTime = System.currentTimeMillis();
            sortingTimes[3] = endTime - startTime;
            sorting = false;
        }).start();
    }

    private void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
            repaint();
            sleep();
        }
    }

    private void merge(int[] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;
        int[] L = new int[n1];
        int[] R = new int[n2];
        System.arraycopy(arr, l, L, 0, n1);
        System.arraycopy(arr, m + 1, R, 0, n2);
        int i = 0, j = 0, k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    public void quickSort() {
        new Thread(() -> {
            sorting = true;
            long startTime = System.currentTimeMillis();
            quickSort(array, 0, array.length - 1);
            long endTime = System.currentTimeMillis();
            sortingTimes[4] = endTime - startTime;
            sorting = false;
        }).start();
    }

    private void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
            repaint();
            sleep();
        }
    }

    private int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    public void heapSort() {
        new Thread(() -> {
            sorting = true;
            long startTime = System.currentTimeMillis();
            buildMaxHeap(array);
            for (int i = array.length - 1; i > 0; i--) {
                int temp = array[0];
                array[0] = array[i];
                array[i] = temp;
                maxHeapify(array, 0, i);
                repaint();
                sleep();
            }
            long endTime = System.currentTimeMillis();
            sortingTimes[5] = endTime - startTime;
            sorting = false;
        }).start();
    }

    private void buildMaxHeap(int[] arr) {
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            maxHeapify(arr, i, arr.length);
        }
    }

    private void maxHeapify(int[] arr, int i, int heapSize) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int largest = i;
        if (left < heapSize && arr[left] > arr[largest]) {
            largest = left;
        }
        if (right < heapSize && arr[right] > arr[largest]) {
            largest = right;
        }
        if (largest != i) {
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;
            maxHeapify(arr, largest, heapSize);
        }
    }

    public void shellSort() {
        new Thread(() -> {
            sorting = true;
            long startTime = System.currentTimeMillis();
            int n = array.length;
            for (int gap = n / 2; gap > 0; gap /= 2) {
                for (int i = gap; i < n; i++) {
                    int temp = array[i];
                    int j = i;
                    while (j >= gap && array[j - gap] > temp) {
                        array[j] = array[j - gap];
                        j -= gap;
                        repaint();
                        sleep();
                    }
                    array[j] = temp;
                    repaint();
                    sleep();
                }
            }
            long endTime = System.currentTimeMillis();
            sortingTimes[6] = endTime - startTime;
            sorting = false;
        }).start();
    }

    public void radixSort() {
        new Thread(() -> {
            sorting = true;
            long startTime = System.currentTimeMillis();
            int max = getMax(array);
            for (int exp = 1; max / exp > 0; exp *= 10) {
                countSort(array, exp);
                repaint();
                sleep();
            }
            long endTime = System.currentTimeMillis();
            sortingTimes[7] = endTime - startTime;
            sorting = false;
        }).start();
    }

    private int getMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    private void countSort(int[] arr, int exp) {
        int[] output = new int[arr.length];
        int[] count = new int[10];
        for (int value : arr) {
            count[(value / exp) % 10]++;
        }
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }
        System.arraycopy(output, 0, arr, 0, arr.length);
    }

    private void sleep() {
        try {
            Thread.sleep(5);  // Delay for visualization
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void visualizeSortingTimes() {
        JFrame frame = new JFrame("Sorting Algorithm Performance");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(8, 1));

        String[] algorithms = {"Bubble Sort", "Selection Sort", "Insertion Sort",
                "Merge Sort", "Quick Sort", "Heap Sort",
                "Shell Sort", "Radix Sort"};

        for (int i = 0; i < 8; i++) {
            JLabel label = new JLabel(algorithms[i] + ": " + sortingTimes[i] + " ms");
            panel.add(label);
        }

        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Sorting Visualizer");
        SortingVisualizer visualizer = new SortingVisualizer();
        frame.add(visualizer, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        JButton bubbleSortButton = new JButton("Bubble Sort");
        JButton selectionSortButton = new JButton("Selection Sort");
        JButton insertionSortButton = new JButton("Insertion Sort");
        JButton mergeSortButton = new JButton("Merge Sort");
        JButton quickSortButton = new JButton("Quick Sort");
        JButton heapSortButton = new JButton("Heap Sort");
        JButton shellSortButton = new JButton("Shell Sort");
        JButton radixSortButton = new JButton("Radix Sort");
        JButton resetButton = new JButton("Reset");
        JButton visualizeButton = new JButton("Visualize Times");

        bubbleSortButton.addActionListener(e -> {
            if (!visualizer.sorting) {
                visualizer.bubbleSort();
            }
        });

        selectionSortButton.addActionListener(e -> {
            if (!visualizer.sorting) {
                visualizer.selectionSort();
            }
        });

        insertionSortButton.addActionListener(e -> {
            if (!visualizer.sorting) {
                visualizer.insertionSort();
            }
        });

        mergeSortButton.addActionListener(e -> {
            if (!visualizer.sorting) {
                visualizer.mergeSort();
            }
        });

        quickSortButton.addActionListener(e -> {
            if (!visualizer.sorting) {
                visualizer.quickSort();
            }
        });

        heapSortButton.addActionListener(e -> {
            if (!visualizer.sorting) {
                visualizer.heapSort();
            }
        });

        shellSortButton.addActionListener(e -> {
            if (!visualizer.sorting) {
                visualizer.shellSort();
            }
        });

        radixSortButton.addActionListener(e -> {
            if (!visualizer.sorting) {
                visualizer.radixSort();
            }
        });

        resetButton.addActionListener(e -> {
            if (!visualizer.sorting) {
                visualizer.resetArray();
            }
        });

        visualizeButton.addActionListener(e -> {
            visualizer.visualizeSortingTimes();
        });

        controlPanel.add(bubbleSortButton);
        controlPanel.add(selectionSortButton);
        controlPanel.add(insertionSortButton);
        controlPanel.add(mergeSortButton);
        controlPanel.add(quickSortButton);
        controlPanel.add(heapSortButton);
        controlPanel.add(shellSortButton);
        controlPanel.add(radixSortButton);
        controlPanel.add(resetButton);
        controlPanel.add(visualizeButton);
        frame.add(controlPanel, BorderLayout.SOUTH);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}