import cProfile
import random

def quick_sort(arr, ascending=True):
    if len(arr) <= 1:
        return arr
    else:
        pivot = arr[0]
        less_than_pivot = [x for x in arr[1:] if x <= pivot]
        greater_than_pivot = [x for x in arr[1:] if x > pivot]
        
        if ascending:
            return quick_sort(less_than_pivot, ascending) + [pivot] + quick_sort(greater_than_pivot, ascending)
        else:
            return quick_sort(greater_than_pivot, ascending) + [pivot] + quick_sort(less_than_pivot, ascending)

# Generar una lista aleatoria de longitud 10
random_list = [random.randint(0, 100) for _ in range(10)]

print("Lista original:", random_list)

# Ordenar de forma ascendente
cProfile.run("sorted_list = quick_sort(random_list.copy(), True)")
print("Lista ordenada de forma ascendente:", sorted_list)

# Ordenar de forma descendente
cProfile.run("sorted_list_desc = quick_sort(random_list.copy(), False)")
print("Lista ordenada de forma descendente:", sorted_list_desc)
