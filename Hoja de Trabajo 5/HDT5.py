import simpy
import random
import statistics
import matplotlib.pyplot as plt

# Semilla para reproducibilidad
RANDOM_SEED = 42
random.seed(RANDOM_SEED)

# Parámetros de la simulación
INTERVALO = 10  # Intervalo de llegada de procesos (en unidades de tiempo)
MEMORIA_RAM = 100  # Memoria RAM disponible
VELOCIDAD_CPU = 3  # Instrucciones que puede ejecutar el CPU en una unidad de tiempo

# Variables para almacenar resultados
resultados = []

class SistemaOperativo:
    def __init__(self, env, memoria, cpu):
        self.env = env
        self.memoria = memoria
        self.cpu = cpu

    def run(self, num_procesos):
        for i in range(num_procesos):
            yield self.env.process(self.proceso(i))

    def proceso(self, id_proceso):
        memoria_requerida = random.randint(1, 10)
        yield self.memoria.get(memoria_requerida)

        tiempo_inicio = self.env.now  # Capturamos el tiempo de inicio
        instrucciones_totales = random.randint(1, 10)
        while instrucciones_totales > 0:
            with self.cpu.request() as req:
                yield req
                yield self.env.timeout(1 / VELOCIDAD_CPU)
                instrucciones_totales -= min(VELOCIDAD_CPU, instrucciones_totales)
                if instrucciones_totales == 0:
                    break
                elif random.randint(1, 21) == 1:
                    yield self.env.timeout(random.randint(1, 2))
        # Calculamos el tiempo de ejecución y lo agregamos a la lista
        tiempo_fin = self.env.now
        tiempo_ejecucion = tiempo_fin - tiempo_inicio
        resultados.append(tiempo_ejecucion)


def ejecutar_simulacion(num_procesos, intervalo_llegada):
    env = simpy.Environment()
    memoria = simpy.Container(env, init=MEMORIA_RAM, capacity=MEMORIA_RAM)
    cpu = simpy.Resource(env, capacity=1)
    sistema = SistemaOperativo(env, memoria, cpu)
    env.process(sistema.run(num_procesos))
    env.run(until=intervalo_llegada * num_procesos)

def calcular_promedio_y_desviacion(resultados):
    promedio = statistics.mean(resultados)
    desviacion = statistics.stdev(resultados)
    return promedio, desviacion

def generar_grafica(datos):
    plt.plot(datos.keys(), datos.values(), marker='o')
    plt.xlabel('Número de procesos')
    plt.ylabel('Tiempo promedio')
    plt.title('Tiempo promedio vs. Número de procesos')
    plt.grid(True)
    plt.show()

# a. Ejecutar simulación con diferentes cantidades de procesos (25, 50, 100, 150, 200) y calcular promedio y desviación
cantidades_procesos = [25, 50, 100, 150, 200]
resultados_a = {}
for cantidad in cantidades_procesos:
    resultados.clear()
    ejecutar_simulacion(cantidad, INTERVALO)
    promedio, desviacion = calcular_promedio_y_desviacion(resultados)
    resultados_a[cantidad] = promedio
print("Resultados (a):", resultados_a)
generar_grafica(resultados_a)

# b. Volver a correr simulación con intervalos de llegada más rápidos (5, 1)
intervalos_llegada = [5, 1]
resultados_b = {}
for intervalo in intervalos_llegada:
    resultados.clear()
    ejecutar_simulacion(cantidades_procesos[-1], intervalo)
    promedio, desviacion = calcular_promedio_y_desviacion(resultados)
    resultados_b[intervalo] = promedio
print("Resultados (b):", resultados_b)
generar_grafica(resultados_b)

# c. Pruebas para reducir el tiempo promedio
# i. Incrementar la memoria a 200
resultados_c_i = {}
MEMORIA_RAM = 200
for cantidad in cantidades_procesos:
    resultados.clear()
    ejecutar_simulacion(cantidad, INTERVALO)
    promedio, desviacion = calcular_promedio_y_desviacion(resultados)
    resultados_c_i[cantidad] = promedio
print("Resultados (c-i):", resultados_c_i)
generar_grafica(resultados_c_i)

# ii. Procesador más rápido (6 instrucciones por unidad de tiempo)
resultados_c_ii = {}
VELOCIDAD_CPU = 6
for cantidad in cantidades_procesos:
    resultados.clear()
    ejecutar_simulacion(cantidad, INTERVALO)
    promedio, desviacion = calcular_promedio_y_desviacion(resultados)
    resultados_c_ii[cantidad] = promedio
print("Resultados (c-ii):", resultados_c_ii)
generar_grafica(resultados_c_ii)

# iii. Emplear 2 procesadores
resultados_c_iii = {}
VELOCIDAD_CPU = 3  # Restaurar la velocidad del CPU
for cantidad in cantidades_procesos:
    env = simpy.Environment()  # Definir un nuevo entorno para cada ejecución
    cpu = simpy.Resource(env, capacity=2)  # Crear dos recursos de CPU
    memoria = simpy.Container(env, init=MEMORIA_RAM, capacity=MEMORIA_RAM)
    sistema = SistemaOperativo(env, memoria, cpu)
    env.process(sistema.run(cantidad))
    env.run(until=INTERVALO * cantidad)  # Ejecutar la simulación
    promedio, desviacion = calcular_promedio_y_desviacion(resultados)
    resultados_c_iii[cantidad] = promedio
print("Resultados (c-iii):", resultados_c_iii)
generar_grafica(resultados_c_iii)