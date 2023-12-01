import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int[] array = generateRandomArray(100);
        int numThreads = 10;

        MaxFinderThread[] threads = new MaxFinderThread[numThreads];
        int startIndex, finishIndex;

        // Inicializamos y ejecutamos los hilos
        for (int i = 0; i < numThreads; i++) {
            startIndex = i * 10;
            finishIndex = (i + 1) * 10 - 1;
            threads[i] = new MaxFinderThread(array, startIndex, finishIndex);
            threads[i].start();
        }

        // Esperamos a que todos los hilos terminen
        try {
            for (int i = 0; i < numThreads; i++) {
                threads[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Encontramos el máximo global a partir de los resultados parciales
        int globalMax = Integer.MIN_VALUE;
        for (int i = 0; i < numThreads; i++) {
            int maxPartial = threads[i].getMaxPartial();
            if (maxPartial > globalMax) {
                globalMax = maxPartial;
            }
        }

        System.out.println("El máximo valor en el array es: " + globalMax);
    }

    // Método para generar un array de números aleatorios
    private static int[] generateRandomArray(int size) {
        int[] array = new int[size];
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(100);  // Números aleatorios entre 0 y 99
        }

        return array;
    }
}
