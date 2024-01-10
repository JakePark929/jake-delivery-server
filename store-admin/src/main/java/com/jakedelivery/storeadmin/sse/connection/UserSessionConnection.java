package com.jakedelivery.storeadmin.sse.connection;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jakedelivery.storeadmin.sse.connection.ifs.ConnectionPoolIfs;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@ToString
@Getter
@EqualsAndHashCode
public class UserSessionConnection {
    private final String uniqueKey;
    private final SseEmitter sseEmitter;
    private final ConnectionPoolIfs<String, UserSessionConnection> connectionPoolIfs;
    private final ObjectMapper objectMapper;

    private UserSessionConnection(
            String uniqueKey,
            ConnectionPoolIfs<String, UserSessionConnection> connectionPoolIfs,
            ObjectMapper objectMapper
    ) {
        this.uniqueKey = uniqueKey; // key 초기화
        this.sseEmitter = new SseEmitter(60 * 1000L); // sse 초기화
        this.connectionPoolIfs = connectionPoolIfs; // call back 초기화
        this.objectMapper = objectMapper; // object mapper 초기화

        // on completion
        this.sseEmitter.onCompletion(() -> {
            // connection pool remove
            this.connectionPoolIfs.onCompletionCallback(this);
        });

        // on timeout
        this.sseEmitter.onTimeout(() -> {
            this.sseEmitter.complete();
        });

        // onopen 메세지
        sendMessage("onopen", "connected with server");
    }

    public static UserSessionConnection connect(
            String uniqueKey,
            ConnectionPoolIfs<String, UserSessionConnection> connectionPoolIfs,
            ObjectMapper objectMapper
    ) {
        return new UserSessionConnection(uniqueKey, connectionPoolIfs, objectMapper);
    }

    public void sendMessage(String eventName, Object data) {
        try {
            var json = this.objectMapper.writeValueAsString(data);
            var event = SseEmitter.event()
                    .name(eventName)
                    .data(json);

            this.sseEmitter.send(event);
        } catch (IOException e) {
            this.sseEmitter.completeWithError(e);
        }
    }

    public void sendMessage(Object data) {
        try {
            var json = this.objectMapper.writeValueAsString(data);
            var event = SseEmitter.event()
                    .data(json);

            this.sseEmitter.send(event);
        } catch (IOException e) {
            this.sseEmitter.completeWithError(e);
        }
    }
}
