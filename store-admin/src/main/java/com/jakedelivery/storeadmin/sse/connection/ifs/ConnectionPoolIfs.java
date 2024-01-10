package com.jakedelivery.storeadmin.sse.connection.ifs;

public interface ConnectionPoolIfs<T, R> {
    void addSession(T uniqueKey, R userSessionConnection);
    R getSession(T uniqueKey);
    void onCompletionCallback(R userSessionConnection);
}
