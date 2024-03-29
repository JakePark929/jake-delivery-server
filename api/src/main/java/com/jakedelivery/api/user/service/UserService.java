package com.jakedelivery.api.user.service;

import com.jakedelivery.common.error.ErrorCode;
import com.jakedelivery.common.error.UserErrorCode;
import com.jakedelivery.common.exception.ApiException;
import com.jakedelivery.db._common.constant.UserStatus;
import com.jakedelivery.db.user.UserEntity;
import com.jakedelivery.db.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserEntity register(UserEntity userEntity) {
        return Optional.ofNullable(userEntity)
                .map(it -> {
                    userEntity.setStatus(UserStatus.REGISTERED);
                    userEntity.setRegisteredAt(LocalDateTime.now());
                    return userRepository.save(userEntity);
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "UserEntity is null."));
    }

    public UserEntity login(String email, String password) {
        var entity = getUserWithThrow(email, password);
        return entity;
    }

    public UserEntity getUserWithThrow(String email, String password) {
        return Optional.ofNullable(
                    userRepository.findFirstByEmailAndPasswordAndStatusOrderByIdDesc(
                            email,
                            password,
                            UserStatus.REGISTERED
                    )
                )
                .orElseThrow(() -> new ApiException(UserErrorCode.USER_NOT_FOUND));
    }

    public UserEntity getUserWithThrow(Long userId) {
        return Optional.ofNullable(
                    userRepository.findFirstByIdAndStatusOrderByIdDesc(
                            userId,
                            UserStatus.REGISTERED
                    )
                )
                .orElseThrow(() -> new ApiException(UserErrorCode.USER_NOT_FOUND));
    }
}
