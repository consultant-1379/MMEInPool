package com.ericsson.mmepool.handler.generic;

import com.ericsson.oss.itpf.common.event.handler.AbstractEventHandler;
import com.ericsson.oss.itpf.common.event.handler.EventInputHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenericComponent extends AbstractEventHandler implements EventInputHandler {

    private static boolean initialized = false;
    private static boolean destroyed = false;
    private final Logger log = LoggerFactory.getLogger(getClass());

    public static void clear() {
        initialized = false;
        destroyed = false;
    }

    public static boolean isInitialized() {
        return initialized;
    }

    public static boolean isDestroyed() {
        return destroyed;
    }

    @Override
    public void destroy() {
        destroyed = true;
    }

    @Override
    public void onEvent(final Object inputEvent) {
        log.info(inputEvent.getClass().getSimpleName());
        sendToAllSubscribers(inputEvent);
    }

    @Override
    protected void doInit() {
        initialized = true;
    }

}
