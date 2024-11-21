import java.io.*;
import java.util.*;
import java.lang.management.*;

public class BubbleSort {
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void saveToFile(int[] arr, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int num : arr) {
                writer.write(num + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getSystemInfo() {
        System.out.println("Java Version: " + System.getProperty("java.version"));
        System.out.println("OS: " + System.getProperty("os.name") + " " + System.getProperty("os.version"));
        System.out.println("Architecture: " + System.getProperty("os.arch"));
        System.out.println("CPU: " + Runtime.getRuntime().availableProcessors() + " cores");
        System.out.println("RAM: " + (Runtime.getRuntime().maxMemory() / (1024 * 1024)) + " MB");
    }

    public static void measureBubbleSort(String filename) {
        try {
            List<Integer> numbers = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                numbers.add(Integer.parseInt(line.trim()));
            }
            reader.close();

            int[] arr = numbers.stream().mapToInt(i -> i).toArray();

            getSystemInfo();

            long startTime = System.nanoTime();
            bubbleSort(arr);
            long endTime = System.nanoTime();

            long duration = endTime - startTime;

            saveToFile(arr, "arq-saida.txt");

            System.out.println("Sorted array saved to 'arq_saida.txt'");
            System.out.println("Time taken for Bubble Sort: " + duration / 1000000 + " ms");

            // Obtendo o uso da memória com precisão
            MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
            long memoryUsedBytes = memoryBean.getHeapMemoryUsage().getUsed();
            double memoryUsedKB = memoryUsedBytes / 1024.0; // Para obter uma precisão decimal

            // Exibindo a memória com 2 casas decimais
            System.out.printf("Memory used: %.2f KB\n", memoryUsedKB);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        measureBubbleSort("arq2.txt");
    }
}
