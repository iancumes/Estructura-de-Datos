import cProfile
import random

def selection_sort(arr, ascending=True):
    n = len(arr)
    for i in range(n):
        min_idx = i
        for j in range(i + 1, n):
            if ascending:
                if arr[j] < arr[min_idx]:
                    min_idx = j
            else:
                if arr[j] > arr[min_idx]:
                    min_idx = j
        arr[i], arr[min_idx] = arr[min_idx], arr[i]
    
    # Imprimir la lista ordenada
    print("Lista ordenada:", arr)

# Generar una lista aleatoria de longitud 10
random_list = [random.randint(0, 10000) for _ in range(25000)]

print("Lista original:", random_list)

# Ordenar de forma ascendente con cProfile
#cProfile.run("selection_sort(random_list.copy(), True)")

# Ordenar de forma descendente con cProfile
cProfile.run("selection_sort(random_list.copy(), False)")
