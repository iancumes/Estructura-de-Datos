import cProfile
import random
import unittest

def merge_sort(arr, reverse=False):
    if len(arr) <= 1:
        return arr
    else:
        mid = len(arr) // 2
        left_half = arr[:mid]
        right_half = arr[mid:]

        left_half = merge_sort(left_half, reverse)
        right_half = merge_sort(right_half, reverse)

        return merge(left_half, right_half, reverse)

def merge(left, right, reverse):
    result = []
    left_idx, right_idx = 0, 0

    if reverse:
        while left_idx < len(left) and right_idx < len(right):
            if left[left_idx] > right[right_idx]:
                result.append(left[left_idx])
                left_idx += 1
            else:
                result.append(right[right_idx])
                right_idx += 1
    else:
        while left_idx < len(left) and right_idx < len(right):
            if left[left_idx] < right[right_idx]:
                result.append(left[left_idx])
                left_idx += 1
            else:
                result.append(right[right_idx])
                right_idx += 1

    result.extend(left[left_idx:])
    result.extend(right[right_idx:])
    return result

class TestMergeSort(unittest.TestCase):
    def test_merge_sort_ascending(self):
        arr = [3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5]
        expected = [1, 1, 2, 3, 3, 4, 5, 5, 5, 6, 9]
        self.assertEqual(merge_sort(arr), expected)

    def test_merge_sort_descending(self):
        arr = [3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5]
        expected = [9, 6, 5, 5, 5, 4, 3, 3, 2, 1, 1]
        self.assertEqual(merge_sort(arr, reverse=True), expected)

if __name__ == "__main__":
    # Generar una lista de números aleatorios
    random.seed(42)  # Fijar la semilla para obtener los mismos resultados cada vez
    lista = [random.randint(0, 10000) for _ in range(10000)]

    # Mostrar la lista desordenada
    print("Lista desordenada:")
    print(lista)

    # Medir el tiempo de ejecución del algoritmo
    profiler = cProfile.Profile()
    profiler.enable()
    
    # Ordenar la lista utilizando Merge Sort
    sorted_list = merge_sort(lista)
    
    profiler.disable()

    # Mostrar la lista ordenada
    print("\nLista ordenada de forma ascendente:")
    print(sorted_list)

    # Pruebas unitarias
    unittest.main(argv=[''], exit=False)
    
    profiler.print_stats(sort='cumtime')
