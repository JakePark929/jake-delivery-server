package com.jakedelivery.storeadmin._core.authorization;

import com.jakedelivery.db._common.constant.StoreStatus;
import com.jakedelivery.db.store.StoreRepository;
import com.jakedelivery.storeadmin.storeuser.service.StoreUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthorizationService implements UserDetailsService {
    private final StoreUserService storeUserService;
    private final StoreRepository storeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var storeUserEntity = storeUserService.getRegisteredUser(username);
        var storeEntity = Optional.ofNullable(
                storeRepository.findFirstByIdAndStatusOrderByIdDesc(
                        storeUserEntity.get().getStoreId(),
                        StoreStatus.REGISTERED
                )
        );

        return storeUserEntity.map(it -> UserSession.builder()
                        .userId(it.getId())
                        .email(it.getEmail())
                        .password(it.getPassword())
                        .status(it.getStatus())
                        .role(it.getRole())
                        .registeredAt(it.getRegisteredAt())
                        .unRegisteredAt(it.getUnRegisteredAt())
                        .lastLoginAt(it.getLastLoginAt())
                        .storeId(storeEntity.get().getId())
                        .storeName(storeEntity.get().getName())
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
