package forkjoinpool;

import java.util.concurrent.ForkJoinPool;

/**
 * @author EAverkin
 */
public class ForkJoinPoolResearch {

    public static void main(String[] args) {

        ForkJoinPool forkJoinPool = new ForkJoinPool();

        var array = new int[]{1, 2, 3, 4, 5, 6, 7};
        ComputerTask computerTask = new ComputerTask(array);

        int sum = forkJoinPool.invoke(computerTask);
        System.out.println(sum);
    }
}
