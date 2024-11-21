import java.io.*;
import java.util.*;
import java.lang.management.*;
import java.lang.Runtime;

public class MergeSort {
    // Função Merge Sort
    public static void mergeSort(int[] arr) {
        if (arr.length <= 1)
            return;

        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);

        mergeSort(left);
        mergeSort(right);

        merge(arr, left, right);
    }

    // Função para juntar os arrays
    private static void merge(int[] arr, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }

        while (i < left.length) {
            arr[k++] = left[i++];
        }

        while (j < right.length) {
            arr[k++] = right[j++];
        }
    }

    // Função para salvar o resultado em arquivo
    public static void saveToFile(int[] arr, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int num : arr) {
                writer.write(num + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Função para coletar as informações do sistema
    public static void getSystemInfo() {
        // Exibindo informações sobre o sistema e CPU
        System.out.println("Java Version: " + System.getProperty("java.version"));
        System.out.println("OS: " + System.getProperty("os.name") + " " + System.getProperty("os.version"));
        System.out.println("Architecture: " + System.getProperty("os.arch"));
        System.out.println("CPU: " + Runtime.getRuntime().availableProcessors() + " cores");

        // Exibindo a quantidade de memória RAM disponível
        System.out.println("RAM: " + (Runtime.getRuntime().maxMemory() / (1024 * 1024)) + " MB");
    }

    // Função para medir o tempo de execução do Merge Sort e coletar informações de
    // memória e CPU
    public static void measureMergeSort(String filename) {
        try {
            List<Integer> numbers = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                numbers.add(Integer.parseInt(line.trim()));
            }
            reader.close();

            // Convertendo para um array
            int[] arr = numbers.stream().mapToInt(i -> i).toArray();

            // Coletando informações do sistema
            getSystemInfo();

            // Medindo o tempo de execução
            long startTime = System.nanoTime();
            mergeSort(arr);
            long endTime = System.nanoTime();

            long duration = endTime - startTime;

            // Salvando o array ordenado
            saveToFile(arr, "arq-saida.txt");

            System.out.println("Sorted array saved to 'arq-saida.txt'");
            System.out.println("Time taken for Merge Sort: " + duration / 1000000 + " ms");

            // Coletando a memória usada
            MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
            System.out.println("Memory used: " + memoryBean.getHeapMemoryUsage().getUsed() / 1024 + " KB");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        measureMergeSort("arq2.txt");
    }
}
