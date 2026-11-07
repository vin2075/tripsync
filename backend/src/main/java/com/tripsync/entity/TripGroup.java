package com.tripsync.entity;
import jakarta.persistence.*; import java.time.Instant; import java.util.UUID;
@Entity @Table(name="trip_groups")
public class TripGroup {
 @Id private UUID id = UUID.randomUUID();
 @Column(nullable=false) private String name;
 @Column(nullable=false) private String ownerId;
 private String description;
 private Instant startsAt; private Instant endsAt; private Instant expiresAt;
 @Column(nullable=false) private Instant createdAt = Instant.now();
 public UUID getId(){return id;} public String getName(){return name;} public void setName(String v){name=v;}
 public String getOwnerId(){return ownerId;} public void setOwnerId(String v){ownerId=v;}
 public String getDescription(){return description;} public void setDescription(String v){description=v;}
 public Instant getStartsAt(){return startsAt;} public void setStartsAt(Instant v){startsAt=v;}
 public Instant getEndsAt(){return endsAt;} public void setEndsAt(Instant v){endsAt=v;}
 public Instant getExpiresAt(){return expiresAt;} public void setExpiresAt(Instant v){expiresAt=v;}
 public Instant getCreatedAt(){return createdAt;}
}
