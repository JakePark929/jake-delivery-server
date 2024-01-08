package com.jakedelivery.db.store;

import com.jakedelivery.db._common.constant.StoreCategory;
import com.jakedelivery.db._common.constant.StoreStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<StoreEntity, Long> {
    // 유효한 스토어
    // select * from store where id = ? and status = ? order by id desc limit 1
    Optional<StoreEntity> findFirstByIdAndStatusOrderByIdDesc(Long id, StoreStatus status);

    // 유효한 스토어 리스트
    // select * from store where status = ? order by id desc
    List<StoreEntity> findAllByStatusOrderByIdDesc(StoreStatus status);

    // 유효한 특정 카테고리 스토어 리스트
    List<StoreEntity> findAllByStatusAndCategoryOrderByStarDesc(StoreStatus storeStatus, StoreCategory storeCategory);
}
