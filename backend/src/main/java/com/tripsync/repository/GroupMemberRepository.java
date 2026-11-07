package com.tripsync.repository; import com.tripsync.entity.GroupMember; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface GroupMemberRepository extends JpaRepository<GroupMember,UUID>{ boolean existsByGroupIdAndUserId(UUID groupId,String userId); List<GroupMember> findByUserId(String userId); List<GroupMember> findByGroupId(UUID groupId); }
