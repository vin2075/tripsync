package com.tripsync.repository; import com.tripsync.entity.MediaAsset; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface MediaAssetRepository extends JpaRepository<MediaAsset,UUID>{ List<MediaAsset> findByGroupIdAndStatusOrderByCapturedAtDesc(UUID groupId,String status); }
