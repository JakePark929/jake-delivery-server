package com.jakedelivery.storeadmin.sse.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jakedelivery.storeadmin._core.authorization.UserSession;
import com.jakedelivery.storeadmin.sse.connection.UserSessionConnection;
import com.jakedelivery.storeadmin.sse.connection.model.SseConnectionPool;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/sse")
@RestController
public class SseApiController {
    private final SseConnectionPool sseConnectionPool;
    private final ObjectMapper objectMapper;

    @GetMapping(path= "/connect", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseBodyEmitter connect(@Parameter(hidden = true) @AuthenticationPrincipal UserSession userSession) {
        log.info("login user : {}", userSession);

        var userSseConnection = UserSessionConnection.connect(userSession.getStoreId().toString(), sseConnectionPool, objectMapper);

        sseConnectionPool.addSession(userSseConnection.getUniqueKey(), userSseConnection);

        return userSseConnection.getSseEmitter();
    }

    @GetMapping("/push-event")
    public void pushEvent(@Parameter(hidden = true) @AuthenticationPrincipal UserSession userSession) {
        // 기존 연결된 유저 찾기
        var userSseConnection = sseConnectionPool.getSession(userSession.getStoreId().toString());

        Optional.ofNullable(userSseConnection)
                .ifPresent(it -> it.sendMessage("hello world"));
    }
}
