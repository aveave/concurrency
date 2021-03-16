package secondsection;

import java.util.List;

public class MultiExecutor {

    List<Runnable> tasks;

    public MultiExecutor(List<Runnable> tasks) {
        this.tasks = tasks;
    }

    public void executeAll() {
        tasks.stream().map(Thread::new).forEach(Thread::start);
    }
}
