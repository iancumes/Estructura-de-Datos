import cProfile
import random

def heapify(arr, n, i, ascending=True):
    largest = i
    l = 2 * i + 1
    r = 2 * i + 2

    if l < n and ((ascending and arr[l] > arr[largest]) or (not ascending and arr[l] < arr[largest])):
        largest = l

    if r < n and ((ascending and arr[r] > arr[largest]) or (not ascending and arr[r] < arr[largest])):
        largest = r

    if largest != i:
        arr[i], arr[largest] = arr[largest], arr[i]
        heapify(arr, n, largest, ascending)

def heap_sort(arr, ascending=True):
    n = len(arr)

    # Construir el max-heap.
    for i in range(n // 2 - 1, -1, -1):
        heapify(arr, n, i, ascending)

    # Extraer elementos uno por uno del heap
    for i in range(n - 1, 0, -1):
        arr[i], arr[0] = arr[0], arr[i]
        heapify(arr, i, 0, ascending)
    
    # Imprimir la lista ordenada
    print("Lista ordenada:", arr)

# Generar una lista aleatoria de longitud 10
random_list = [random.randint(0, 10000) for _ in range(7500)]

print("Lista original:", random_list)

# Ordenar de forma ascendente con cProfile
cProfile.run("heap_sort(random_list.copy(), True)")

# Ordenar de forma descendente con cProfile
cProfile.run("heap_sort(random_list.copy(), False)")
