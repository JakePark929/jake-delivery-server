package com.jakedelivery.storeadmin.sse.controller;

import com.jakedelivery.storeadmin._core.authorization.UserSession;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/sse")
@RestController
public class SseApiController {
    private static final Map<String, SseEmitter> USER_CONNECTION_POOL = new ConcurrentHashMap<>();

    @GetMapping(path= "/connect", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseBodyEmitter connect(@Parameter(hidden = true) @AuthenticationPrincipal UserSession userSession) {
        log.info("login user {}", userSession);

        var emitter = new SseEmitter(1000L * 60); // ms
        USER_CONNECTION_POOL.put(userSession.getUserId().toString(), emitter);

        // 클라이언트와 타임 아웃이 일어났을 때
        emitter.onTimeout(() -> {
            log.info("on timeout");
            emitter.complete(); // 종료됨
        });

        emitter.onCompletion(() -> {
            log.info("on completion");
            // 클라이언트와 연결이 종료됬을 때 하는 작업
            USER_CONNECTION_POOL.remove(userSession.getUserId().toString());
        });

        // 최초 연결 시 응답 전송
        var event = SseEmitter
                .event()
                .name("onopen");

        try {
            emitter.send(event);
        } catch (IOException e) {
            emitter.completeWithError(e);
        }

        return emitter;
    }

    @GetMapping("/push-event")
    public void pushEvent(@Parameter(hidden = true) @AuthenticationPrincipal UserSession userSession) {
        // 기존 연결된 유저 찾기
        var emitter = USER_CONNECTION_POOL.get(userSession.getUserId().toString());

        var event = SseEmitter
                .event()
                .data("hello"); // onmessage

        try {
            emitter.send(event);
        } catch (IOException e) {
            emitter.completeWithError(e);
        }
    }
}
