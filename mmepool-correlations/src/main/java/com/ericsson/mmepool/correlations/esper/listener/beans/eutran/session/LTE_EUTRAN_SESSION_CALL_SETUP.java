/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2-112
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package com.ericsson.mmepool.correlations.esper.listener.beans.eutran.session;

import com.ericsson.xstream.apeventbeans.correlation.CALL_SETUP;

public class LTE_EUTRAN_SESSION_CALL_SETUP extends CALL_SETUP implements Cloneable {

    private static final long serialVersionUID = 1L;

    @Override
    public Object clone() {
        try {
            final LTE_EUTRAN_SESSION_CALL_SETUP cloned = (LTE_EUTRAN_SESSION_CALL_SETUP) super.clone();
            return cloned;
        } catch (final CloneNotSupportedException e) {
            e.printStackTrace();
        } catch (final Throwable t) {
            t.printStackTrace();
        }
        return null;
    }

    public LTE_EUTRAN_SESSION_CALL_SETUP cloneMe() {
        final LTE_EUTRAN_SESSION_CALL_SETUP source = (LTE_EUTRAN_SESSION_CALL_SETUP) this.clone();
        return source;
    }

}
