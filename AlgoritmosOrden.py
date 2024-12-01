#Ordene a mesma lista utilizando Shell Sort, Merge Sort, Selection Sort, Quick Sort, Bucket Sort e Radix Sort. 
#Registre os tempos de execução e número de comparações realizadas.

import time
import random
import numpy as np

class SortingAlgorithms:
    def __init__(self):
        self.comparisons = 0

    def shell_sort(self, arr):
        self.comparisons = 0
        n = len(arr)
        gap = n // 2
        while gap > 0:
            for i in range(gap, n):
                temp = arr[i]
                j = i
                while j >= gap and arr[j - gap] > temp:
                    self.comparisons += 1
                    arr[j] = arr[j - gap]
                    j -= gap
                arr[j] = temp
                self.comparisons += 1
            gap //= 2
        return arr

    def merge_sort(self, arr):
        if len(arr) > 1:
            mid = len(arr) // 2
            L = arr[:mid]
            R = arr[mid:]
            self.merge_sort(L)
            self.merge_sort(R)
            i = j = k = 0
            while i < len(L) and j < len(R):
                self.comparisons += 1
                if L[i] < R[j]:
                    arr[k] = L[i]
                    i += 1
                else:
                    arr[k] = R[j]
                    j += 1
                k += 1
            while i < len(L):
                arr[k] = L[i]
                i += 1
                k += 1
            while j < len(R):
                arr[k] = R[j]
                j += 1
                k += 1
        return arr

    def selection_sort(self, arr):
        self.comparisons = 0
        n = len(arr)
        for i in range(n):
            min_idx = i
            for j in range(i+1, n):
                self.comparisons += 1
                if arr[j] < arr[min_idx]:
                    min_idx = j
            arr[i], arr[min_idx] = arr[min_idx], arr[i]
        return arr

    def quick_sort(self, arr, low, high):
        if low < high:
            pi = self.partition(arr, low, high)
            self.quick_sort(arr, low, pi - 1)
            self.quick_sort(arr, pi + 1, high)
        return arr

    def partition(self, arr, low, high):
        pivot = arr[high]
        i = low - 1
        for j in range(low, high):
            self.comparisons += 1
            if arr[j] < pivot:
                i += 1
                arr[i], arr[j] = arr[j], arr[i]
        arr[i + 1], arr[high] = arr[high], arr[i + 1]
        return i + 1

    def bucket_sort(self, arr):
        self.comparisons = 0
        max_value = max(arr)
        n = len(arr)
        num_buckets = int(np.ceil(np.sqrt(n)))  # Ajuste para calcular o número de baldes

        buckets = [[] for _ in range(num_buckets)]

        for num in arr:
            index = min(num // (max_value // num_buckets + 1), num_buckets - 1)  # Garantir que o índice está no intervalo
            buckets[index].append(num)

        sorted_arr = []
        for bucket in buckets:
            sorted_arr.extend(sorted(bucket))
            self.comparisons += len(bucket)
        return sorted_arr

    def radix_sort(self, arr):
        self.comparisons = 0
        max_val = max(arr)
        exp = 1
        while max_val // exp > 0:
            self.count_sort(arr, exp)
            exp *= 10
        return arr

    def count_sort(self, arr, exp):
        n = len(arr)
        output = [0] * n
        count = [0] * 10
        for i in range(n):
            index = arr[i] // exp
            count[index % 10] += 1
        for i in range(1, 10):
            count[i] += count[i - 1]
        i = n - 1
        while i >= 0:
            index = arr[i] // exp
            output[count[index % 10] - 1] = arr[i]
            count[index % 10] -= 1
            i -= 1
        for i in range(n):
            arr[i] = output[i]
        self.comparisons += n

def measure_time_and_comparisons(sort_function, arr):
    start_time = time.time()
    sort_alg = SortingAlgorithms()
    sort_alg.comparisons = 0
    sorted_arr = sort_function(sort_alg, arr.copy())
    return time.time() - start_time, sort_alg.comparisons

sizes = [100, 1000, 10000]
results = {"Algorithm": ["Shell Sort", "Merge Sort", "Selection Sort", "Quick Sort", "Bucket Sort", "Radix Sort"]}

for size in sizes:
    results[f"{size} Size (Time)"] = []
    results[f"{size} Size (Comparisons)"] = []

sorting_algorithms = [
    lambda sort_alg, arr: sort_alg.shell_sort(arr),
    lambda sort_alg, arr: sort_alg.merge_sort(arr),
    lambda sort_alg, arr: sort_alg.selection_sort(arr),
    lambda sort_alg, arr: sort_alg.quick_sort(arr, 0, len(arr) - 1),
    lambda sort_alg, arr: sort_alg.bucket_sort(arr),
    lambda sort_alg, arr: sort_alg.radix_sort(arr),
]

for size in sizes:
    arr = np.random.randint(0, size * 10, size).tolist()
    for sort_function in sorting_algorithms:
        time_taken, comparisons = measure_time_and_comparisons(sort_function, arr)
        results[f"{size} Size (Time)"].append(time_taken)
        results[f"{size} Size (Comparisons)"].append(comparisons)

print(f"{'Algorithm':<20} {'100 Size (Time)':<20} {'100 Size (Comparisons)':<25} {'1000 Size (Time)':<20} {'1000 Size (Comparisons)':<25} {'10000 Size (Time)':<20} {'10000 Size (Comparisons)':<25}")
for i in range(len(results["Algorithm"])):
    print(f"{results['Algorithm'][i]:<20} {results['100 Size (Time)'][i]:<20.10f} {results['100 Size (Comparisons)'][i]:<25} {results['1000 Size (Time)'][i]:<20.10f} {results['1000 Size (Comparisons)'][i]:<25} {results['10000 Size (Time)'][i]:<20.10f} {results['10000 Size (Comparisons)'][i]:<25}")
