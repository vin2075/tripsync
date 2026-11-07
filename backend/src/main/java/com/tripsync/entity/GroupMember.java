package com.tripsync.entity;
import jakarta.persistence.*; import java.time.Instant; import java.util.UUID;
@Entity @Table(name="group_members", uniqueConstraints=@UniqueConstraint(columnNames={"group_id","user_id"}))
public class GroupMember {
 @Id private UUID id=UUID.randomUUID(); @Column(name="group_id",nullable=false) private UUID groupId;
 @Column(name="user_id",nullable=false) private String userId; @Column(nullable=false) private String role="MEMBER";
 private Instant joinedAt=Instant.now();
 protected GroupMember(){} public GroupMember(UUID g,String u,String r){groupId=g;userId=u;role=r;}
 public UUID getId(){return id;} public UUID getGroupId(){return groupId;} public String getUserId(){return userId;} public String getRole(){return role;}
}
