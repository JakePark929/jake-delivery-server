package com.jakedelivery.storeadmin.sse.connection.model;

import com.jakedelivery.storeadmin.sse.connection.UserSessionConnection;
import com.jakedelivery.storeadmin.sse.connection.ifs.ConnectionPoolIfs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class SseConnectionPool implements ConnectionPoolIfs<String, UserSessionConnection> {
    private static final Map<String, UserSessionConnection> CONNECTION_POOL = new ConcurrentHashMap<>();

    @Override
    public void addSession(String uniqueKey, UserSessionConnection userSessionConnection) {
        CONNECTION_POOL.put(uniqueKey, userSessionConnection);
    }

    @Override
    public UserSessionConnection getSession(String uniqueKey) {
        return CONNECTION_POOL.get(uniqueKey);
    }

    @Override
    public void onCompletionCallback(UserSessionConnection userSessionConnection) {
        log.info("call back connection pool connection : {}", userSessionConnection);
        CONNECTION_POOL.remove(userSessionConnection.getUniqueKey());
    }
}
