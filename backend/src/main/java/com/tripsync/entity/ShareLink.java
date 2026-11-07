package com.tripsync.entity;
import jakarta.persistence.*; import java.time.Instant; import java.util.UUID;
@Entity @Table(name="share_links")
public class ShareLink { @Id private UUID id=UUID.randomUUID(); @Column(nullable=false,unique=true) private String token; @Column(nullable=false) private UUID mediaId; @Column(nullable=false) private Instant expiresAt; private boolean revoked=false;
 protected ShareLink(){} public ShareLink(String t,UUID m,Instant e){token=t;mediaId=m;expiresAt=e;} public String getToken(){return token;} public UUID getMediaId(){return mediaId;} public Instant getExpiresAt(){return expiresAt;} public boolean isRevoked(){return revoked;} }
