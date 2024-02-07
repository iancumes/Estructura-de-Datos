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

# Generar una lista aleatoria de longitud 10
random_list = [random.randint(0, 1000) for _ in range(10)]

print("Lista original:", random_list)

# Ordenar de forma ascendente
cProfile.run("selection_sort(random_list.copy(), True)")
print("Lista ordenada de forma ascendente:", random_list)

# Ordenar de forma descendente
cProfile.run("selection_sort(random_list.copy(), False)")
print("Lista ordenada de forma descendente:", random_list)
