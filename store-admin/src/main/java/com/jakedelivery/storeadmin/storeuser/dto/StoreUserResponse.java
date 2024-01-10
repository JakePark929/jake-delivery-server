package com.jakedelivery.storeadmin.storeuser.dto;

import com.jakedelivery.db._common.constant.StoreUserRole;
import com.jakedelivery.db._common.constant.StoreUserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreUserResponse {
    private UserResponse user;
    private StoreResponse store;

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserResponse {
        private Long id;
        private String email;
        private StoreUserStatus status;
        private StoreUserRole role;
        private LocalDateTime registeredAt;
        private LocalDateTime unRegisteredAt;
        private LocalDateTime lastLoginAt;
    }

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StoreResponse {
        private Long id;
        private  String name;
    }
}
