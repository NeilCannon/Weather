package org.fuzzyrobot.weather;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import rx.Observable;

/**
 * Created by neil on 14/07/2016.
 */
class IdentityTransformer implements Answer<Observable.Transformer> {
    @Override
    public Observable.Transformer answer(final InvocationOnMock invocation) throws Throwable {
        return new Observable.Transformer() {

            @Override
            public Object call(final Object o) {
                return o;
            }

        };
    }
}
