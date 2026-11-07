package com.tripsync.repository; import com.tripsync.entity.ShareLink; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface ShareLinkRepository extends JpaRepository<ShareLink,UUID>{ Optional<ShareLink> findByToken(String token); }
