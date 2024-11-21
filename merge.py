import time
import tracemalloc
import platform
import psutil

def merge_sort(arr):
    if len(arr) > 1:
        mid = len(arr) // 2
        left_half = arr[:mid]
        right_half = arr[mid:]

        merge_sort(left_half)
        merge_sort(right_half)

        i = j = k = 0
        while i < len(left_half) and j < len(right_half):
            if left_half[i] < right_half[j]:
                arr[k] = left_half[i]
                i += 1
            else:
                arr[k] = right_half[j]
                j += 1
            k += 1

        while i < len(left_half):
            arr[k] = left_half[i]
            i += 1
            k += 1

        while j < len(right_half):
            arr[k] = right_half[j]
            j += 1
            k += 1

def save_to_file(arr, filename):
    with open(filename, 'w') as file:
        for num in arr:
            file.write(f"{num}\n")

def get_system_info():
    print(f"Python Version: {platform.python_version()}")
    print(f"System: {platform.system()} {platform.release()}")
    print(f"Machine: {platform.machine()}")
    print(f"CPU: {psutil.cpu_count()} cores")
    print(f"RAM: {psutil.virtual_memory().total / (1024 ** 2)} MB")

def measure_merge_sort(filename):
    with open(filename, 'r') as file:
        arr = [int(line.strip()) for line in file.readlines()]

    get_system_info()

    tracemalloc.start()

    start_time = time.time()
    merge_sort(arr)
    end_time = time.time()

    current, peak = tracemalloc.get_traced_memory()

    save_to_file(arr, 'arq-saida.txt')

    print(f"Sorted array saved to 'arq-saida.txt'")
    print(f"Time taken for Merge Sort: {(end_time - start_time) * 1000:.6f} ms")
    print(f"Memory used: Current = {current / 1024}KB; Peak = {peak / 1024}KB")
    
    tracemalloc.stop()

measure_merge_sort('arq2.txt')
