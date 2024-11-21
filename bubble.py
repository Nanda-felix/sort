import time
import os
import psutil
import platform

# Função Bubble Sort
def bubble_sort(arr):
    n = len(arr)
    for i in range(n):
        for j in range(n - i - 1):
            if arr[j] > arr[j + 1]:
                arr[j], arr[j + 1] = arr[j + 1], arr[j]

# Função para salvar o resultado no arquivo
def save_to_file(arr, filename):
    with open(filename, 'w') as file:
        for num in arr:
            file.write(f"{num}\n")

# Função para coletar informações do sistema
def get_system_info():
    print(f"Python Version: {platform.python_version()}")
    print(f"OS: {platform.system()} {platform.version()}")
    print(f"Architecture: {platform.architecture()[0]}")
    print(f"CPU: {psutil.cpu_count(logical=False)} cores")
    print(f"RAM: {psutil.virtual_memory().total / (1024 * 1024)} MB")

# Função para medir o tempo e memória do Bubble Sort
def measure_bubble_sort(filename):
    try:
        # Lendo os números do arquivo
        with open(filename, 'r') as file:
            arr = [int(line.strip()) for line in file.readlines()]

        # Coletando informações do sistema
        get_system_info()

        # Medindo o tempo de execução
        start_time = time.time()
        bubble_sort(arr)
        end_time = time.time()

        duration = (end_time - start_time) * 1000  # Tempo em milissegundos

        # Salvando o array ordenado
        save_to_file(arr, "arq-saida.txt")

        print(f"Sorted array saved to 'arq-saida.txt'")
        print(f"Time taken for Bubble Sort: {duration:.2f} ms")

        # Coletando a memória usada
        memory_info = psutil.Process(os.getpid()).memory_info()
        print(f"Memory used: {memory_info.rss / 1024} KB")  # Memória em KB

    except Exception as e:
        print(f"Error: {e}")

if __name__ == "__main__":
    measure_bubble_sort("arq2.txt")
