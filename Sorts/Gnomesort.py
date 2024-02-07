import cProfile
import random

def gnome_sort(arr):
    index = 0
    n = len(arr)
    while index < n:
        if index == 0:
            index += 1
        if arr[index] >= arr[index - 1]:
            index += 1
        else:
            arr[index], arr[index - 1] = arr[index - 1], arr[index]
            index -= 1

if __name__ == "__main__":
    # Generar una lista de números aleatorios
    random.seed(42)  # Fijar la semilla para obtener los mismos resultados cada vez
    lista = [random.randint(0, 10000) for _ in range(100000)]

    # Mostrar la lista desordenada
    print("Lista desordenada:")
    print(lista)

    # Medir el tiempo de ejecución del algoritmo
    profiler = cProfile.Profile()
    profiler.enable()
    
    # Ordenar la lista utilizando Gnome Sort
    gnome_sort(lista)
    
    profiler.disable()

    # Mostrar la lista ordenada
    print("\nLista ordenada:")
    print(lista)

    profiler.print_stats(sort='cumtime')
