package executorservice;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author EAverkin
 */
public class CompletableFutureResearch {

    public static void main(String[] args) throws InterruptedException {
//        testCompletableFuture();
//        testCompletableFutureSupplyAsync();
        testCompletableFutureWithThen();
        Thread.sleep(10000);
    }

    public static void testCompletableFuture() {
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(
                () -> {
                for (int i=0; i < 10; i++) {
                    System.out.println("Hello from completable future");
                }
                });
//        try {
//            completableFuture.get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
    }

    public static void testCompletableFutureSupplyAsync() {
        CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello supply async";
        }).thenAccept(str -> System.out.println("Message: " + str));

        try {
            completableFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void testCompletableFutureWithThen(){
        CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Supply async stage");
            return "Just string";
        }).thenApply((str) -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Then apply stage");
            return str;
        }).thenAccept(s -> System.out.println("Accepted string: " + s));


    }
}
