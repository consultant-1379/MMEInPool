/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2012
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package com.ericsson.mmepool.testsuite.hazelcast.listener;

import com.hazelcast.core.Message;
import com.hazelcast.core.MessageListener;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class HazelcastInputListener implements MessageListener {

    public CountDownLatch LATCH;

    private final List<Object> receivedMessages = new LinkedList<>();

    public HazelcastInputListener(final int count) {
        LATCH = new CountDownLatch(count);
    }

    @Override
    public void onMessage(final Message arg) {
        receivedMessages.add(arg.getMessageObject());
        LATCH.countDown();
    }

    public List<Object> getReceivedMessages() {
        return receivedMessages;
    }

    public void clear(final int count) {
        receivedMessages.clear();
        LATCH = new CountDownLatch(count);
    }

}
