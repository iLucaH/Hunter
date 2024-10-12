package me.ilucah.hunter.display.render;

import java.util.concurrent.*;
import java.util.stream.Stream;

public class RenderFactory {

    private static final ExecutorService threadPoolExecutor = new ThreadPoolExecutor(50, 50,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>());

    private static final LinkedTransferQueue<RenderingObject> renders = new LinkedTransferQueue<>();

    public static void clear() {
        renders.clear();
    }

    public static void add(RenderingObject object) {
        object.setId(renders.size() + 1F);
        renders.add(object);
    }

    public static boolean remove(RenderingObject object) {
        return renders.remove(object);
    }

    public static Stream<RenderingObject> getRenders() {
        return renders.stream();
    }

    public static ExecutorService getThreadPool() {
        return threadPoolExecutor;
    }
}
