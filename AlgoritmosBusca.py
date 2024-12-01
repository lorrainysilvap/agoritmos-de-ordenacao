#Construa uma tabela comparativa dos tempos de execução de Binary Search, Interpolation Search, 
#Jump Search e Exponential Search em listas de tamanhos diferentes.

import time
import random

def binary_search(arr, x):
    low = 0
    high = len(arr) - 1
    while low <= high:
        mid = (low + high) // 2
        if arr[mid] < x:
            low = mid + 1
        elif arr[mid] > x:
            high = mid - 1
        else:
            return mid
    return -1

def interpolation_search(arr, x):
    low = 0
    high = len(arr) - 1
    while low <= high and x >= arr[low] and x <= arr[high]:
        if low == high:
            if arr[low] == x:
                return low
            return -1

        pos = low + ((x - arr[low]) * (high - low) // (arr[high] - arr[low]))

        if arr[pos] == x:
            return pos
        if arr[pos] < x:
            low = pos + 1
        else:
            high = pos - 1
    return -1

def jump_search(arr, x):
    n = len(arr)
    step = int(n ** 0.5)
    prev = 0
    while arr[min(step, n)-1] < x:
        prev = step
        step += int(n ** 0.5)
        if prev >= n:
            return -1
    while arr[prev] < x:
        prev += 1
        if prev == min(step, n):
            return -1
    if arr[prev] == x:
        return prev
    return -1

def exponential_search(arr, x):
    if arr[0] == x:
        return 0
    i = 1
    while i < len(arr) and arr[i] <= x:
        i = i * 2
    return binary_search(arr[:min(i, len(arr))], x)

def measure_time(search_function, arr, x):
    start_time = time.time()
    search_function(arr, x)
    return time.time() - start_time

sizes = [100, 1000, 10000, 100000]
search_functions = [binary_search, interpolation_search, jump_search, exponential_search]
results = {func.__name__: [] for func in search_functions}

for size in sizes:
    arr = sorted(random.sample(range(size * 10), size))
    x = random.choice(arr)
    for func in search_functions:
        time_taken = measure_time(func, arr, x)
        results[func.__name__].append(time_taken)

print(f"{'Tamanho da Lista':<15} {'Binary Search':<15} {'Interpolation Search':<20} {'Jump Search':<15} {'Exponential Search':<20}")
for i, size in enumerate(sizes):
    print(f"{size:<15} {results['binary_search'][i]:<15.10f} {results['interpolation_search'][i]:<20.10f} {results['jump_search'][i]:<15.10f} {results['exponential_search'][i]:<20.10f}")
