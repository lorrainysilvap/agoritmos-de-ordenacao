import matplotlib.pyplot as plt
import numpy as np

def plot(arr, stage, title):
    plt.figure(figsize=(10, 3))
    plt.bar(range(len(arr)), arr, color="skyblue")
    plt.title(f"{title} - Etapa {stage}")
    plt.xlabel("Índice")
    plt.ylabel("Valor")
    plt.show()

def merge_sort(arr, stage=[1]):
    if len(arr) > 1:
        mid = len(arr) // 2
        L = arr[:mid]
        R = arr[mid:]

        merge_sort(L, stage)
        merge_sort(R, stage)

        i = j = k = 0

        while i < len(L) and j < len(R):
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

        plot(arr, stage[0], "Merge Sort")
        stage[0] += 1

def quick_sort(arr, low, high, stage=[1]):
    if low < high:
        pi = partition(arr, low, high)
        plot(arr, stage[0], "Quick Sort")
        stage[0] += 1

        quick_sort(arr, low, pi - 1, stage)
        quick_sort(arr, pi + 1, high, stage)

def partition(arr, low, high):
    pivot = arr[high]
    i = low - 1
    for j in range(low, high):
        if arr[j] <= pivot:
            i += 1
            arr[i], arr[j] = arr[j], arr[i]
    arr[i + 1], arr[high] = arr[high], arr[i + 1]
    return i + 1

def selection_sort(arr):
    stage = [1]
    n = len(arr)
    for i in range(n):
        min_idx = i
        for j in range(i + 1, n):
            if arr[j] < arr[min_idx]:
                min_idx = j
        arr[i], arr[min_idx] = arr[min_idx], arr[i]
        plot(arr, stage[0], "Selection Sort")
        stage[0] += 1

if __name__ == "__main__":
    arr = np.random.randint(0, 100, 10).tolist()

    print("Merge Sort")
    merge_sort(arr.copy())

    print("Quick Sort")
    quick_sort(arr.copy(), 0, len(arr) - 1)

    print("Selection Sort")
    selection_sort(arr.copy())
