import cProfile
import random

def counting_sort(arr, exp, ascending=True):
    n = len(arr)
    output = [0] * n
    count = [0] * 10
    
    for i in range(n):
        index = arr[i] // exp
        count[index % 10] += 1
    
    if ascending:
        for i in range(1, 10):
            count[i] += count[i - 1]
    else:
        for i in range(8, -1, -1):
            count[i] += count[i + 1]
    
    i = n - 1
    while i >= 0:
        index = arr[i] // exp
        output[count[index % 10] - 1] = arr[i]
        count[index % 10] -= 1
        i -= 1
    
    for i in range(n):
        arr[i] = output[i]

def radix_sort(arr, ascending=True):
    max_val = max(arr)
    exp = 1
    while max_val // exp > 0:
        counting_sort(arr, exp, ascending)
        exp *= 10

# Generar una lista aleatoria de longitud 10
random_list = [random.randint(0, 1000) for _ in range(10)]

print("Lista original:", random_list)

# Ordenar de forma ascendente
cProfile.run("radix_sort(random_list.copy(), True)")
print("Lista ordenada de forma ascendente:", random_list)

# Ordenar de forma descendente
cProfile.run("radix_sort(random_list.copy(), False)")
print("Lista ordenada de forma descendente:", random_list)
