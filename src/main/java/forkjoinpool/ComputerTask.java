package forkjoinpool;

import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

/**
 * @author EAverkin
 */
public class ComputerTask extends RecursiveTask<Integer> {

    private int[] array;

    public ComputerTask(int[] array) {
        this.array = array;
    }

    @Override
    protected Integer compute() {

        if (array.length <= 2) {
            return Arrays.stream(array).sum();
        }
        var first = Arrays.copyOfRange(array, 0, array.length / 2);
        var second = Arrays.copyOfRange(array, array.length / 2, array.length);

        ComputerTask firstComputerTask = new ComputerTask(first);
        ComputerTask secondComputerTask = new ComputerTask(second);
        firstComputerTask.fork();
        secondComputerTask.fork();
        return firstComputerTask.join() + secondComputerTask.join();
    }
}
