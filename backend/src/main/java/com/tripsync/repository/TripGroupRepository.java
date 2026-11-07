package com.tripsync.repository; import com.tripsync.entity.TripGroup; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface TripGroupRepository extends JpaRepository<TripGroup,UUID>{ List<TripGroup> findDistinctByOwnerIdOrIdIn(String ownerId,Collection<UUID> ids); }
