package com.jakedelivery.storeadmin._core.authorization;

import com.jakedelivery.db._common.constant.StoreUserRole;
import com.jakedelivery.db._common.constant.StoreUserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSession implements UserDetails {
    // user
    private Long userId;
    private String email;
    private String password;
    private StoreUserStatus status;
    private StoreUserRole role;
    private LocalDateTime registeredAt;
    private LocalDateTime unRegisteredAt;
    private LocalDateTime lastLoginAt;

    // store
    private Long storeId;
    private String storeName;

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.toString()));
    }

    @Override
    public boolean isAccountNonExpired() { return this.status == StoreUserStatus.REGISTERED; }

    @Override
    public boolean isAccountNonLocked() { return this.status == StoreUserStatus.REGISTERED; }

    @Override
    public boolean isCredentialsNonExpired() { return this.status == StoreUserStatus.REGISTERED; }

    @Override
    public boolean isEnabled() { return true; }
}
