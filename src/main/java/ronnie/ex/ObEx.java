package ronnie.ex;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by ronnie on 2017. 7. 22
 */
@Slf4j
public class ObEx {

    static class IntObservable extends Observable implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                setChanged();
                notifyObservers(i); // push
            }
        }
    }

    public static void main(String[] args) {
        Observer ob = (o, arg) -> {
            log.info(arg.toString());
        };

        IntObservable io = new IntObservable();
        io.addObserver(ob);
        //io.run(); // main thread
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.execute(io); // thread pool
        es.shutdown();

        log.info("finish");
    }
}
