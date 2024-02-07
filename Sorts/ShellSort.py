import cProfile
import random

def shell_sort(arr, ascending=True):
    n = len(arr)
    gap = n // 2
    while gap > 0:
        for i in range(gap, n):
            temp = arr[i]
            j = i
            while j >= gap and ((ascending and arr[j - gap] > temp) or (not ascending and arr[j - gap] < temp)):
                arr[j] = arr[j - gap]
                j -= gap
            arr[j] = temp
        gap //= 2
    
    # Imprimir la lista ordenada
    print("Lista ordenada:", arr)

# Generar una lista aleatoria de longitud 10
random_list = [random.randint(0, 10000) for _ in range(20000)]

print("Lista original:", random_list)

# Ordenar de forma ascendente con cProfile
cProfile.run("shell_sort(random_list.copy(), True)")

# Ordenar de forma descendente con cProfile
cProfile.run("shell_sort(random_list.copy(), False)")
