class MaxFinderThread extends Thread {
    private int[] array;
    private int startIndex;
    private int finishIndex;
    private int maxPartial;

    public MaxFinderThread(int[] array, int startIndex, int finishIndex) {
        this.array = array;
        this.startIndex = startIndex;
        this.finishIndex = finishIndex;
        this.maxPartial = Integer.MIN_VALUE;  // Inicializamos con el valor mínimo posible
    }

    public int getMaxPartial() {
        return maxPartial;
    }

    @Override
    public void run() {
        for (int i = startIndex; i <= finishIndex; i++) {
            if (array[i] > maxPartial) {
                maxPartial = array[i];
            }
        }
    }
}

