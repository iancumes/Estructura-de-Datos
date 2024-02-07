import random
import unittest
def heapify(arr, n, i):
    largest = i
    left_child = 2 * i + 1
    right_child = 2 * i + 2

    if left_child < n and arr[left_child] > arr[largest]:
        largest = left_child

    if right_child < n and arr[right_child] > arr[largest]:
        largest = right_child

    if largest != i:
        arr[i], arr[largest] = arr[largest], arr[i]
        heapify(arr, n, largest)

def heap_sort(arr):
    n = len(arr)

    # Construir un max-heap
    for i in range(n // 2 - 1, -1, -1):
        heapify(arr, n, i)

    # Extraer elementos uno por uno del heap
    for i in range(n - 1, 0, -1):
        arr[i], arr[0] = arr[0], arr[i]  # Intercambiar el máximo con el último elemento
        heapify(arr, i, 0)

# Ejemplo de uso:
lista_de_numeros = random.sample(range(1, 50), 10)
print("Lista desordenada: ", lista_de_numeros)
heap_sort(lista_de_numeros)
print("Lista ordenada:", lista_de_numeros)



def is_sorted(arr):
    return all(arr[i] <= arr[i + 1] for i in range(len(arr) - 1))

class TestHeapSort(unittest.TestCase):

    def test_heap_sort(self):
        # Caso de prueba con una lista desordenada
        lista_desordenada = random.sample(range(1, 50), 10)
        heap_sort(lista_desordenada)
        self.assertTrue(is_sorted(lista_desordenada), "La lista no está ordenada")

        # Caso de prueba con una lista ordenada
        lista_ordenada = list(range(1, 101))
        heap_sort(lista_ordenada)
        self.assertTrue(is_sorted(lista_ordenada), "La lista ordenada se mantiene ordenada")

        # Caso de prueba con una lista inversa
        lista_inversa = list(range(100, 0, -1))
        heap_sort(lista_inversa)
        self.assertTrue(is_sorted(lista_inversa), "La lista inversa se ordena correctamente")

if __name__ == 'headsort':
    unittest.main()
