package com.tripsync.entity;
import jakarta.persistence.*; import java.time.Instant; import java.util.UUID;
@Entity @Table(name="media_assets")
public class MediaAsset {
 @Id private UUID id=UUID.randomUUID(); @Column(nullable=false) private UUID groupId; @Column(nullable=false) private String uploaderId;
 @Column(nullable=false) private String objectKey; @Column(nullable=false) private String fileName; @Column(nullable=false) private String contentType;
 private Long sizeBytes; @Column(nullable=false) private String status="PENDING"; private Instant capturedAt; private Double latitude; private Double longitude;
 private String cameraMake; private String cameraModel; private Double aperture; private String shutterSpeed; private Integer iso; private Double focalLength;
 @Column(nullable=false) private Instant uploadedAt=Instant.now();
 public UUID getId(){return id;} public UUID getGroupId(){return groupId;} public void setGroupId(UUID v){groupId=v;}
 public String getUploaderId(){return uploaderId;} public void setUploaderId(String v){uploaderId=v;}
 public String getObjectKey(){return objectKey;} public void setObjectKey(String v){objectKey=v;}
 public String getFileName(){return fileName;} public void setFileName(String v){fileName=v;}
 public String getContentType(){return contentType;} public void setContentType(String v){contentType=v;}
 public Long getSizeBytes(){return sizeBytes;} public void setSizeBytes(Long v){sizeBytes=v;}
 public String getStatus(){return status;} public void setStatus(String v){status=v;}
 public Instant getCapturedAt(){return capturedAt;} public void setCapturedAt(Instant v){capturedAt=v;}
 public Double getLatitude(){return latitude;} public void setLatitude(Double v){latitude=v;}
 public Double getLongitude(){return longitude;} public void setLongitude(Double v){longitude=v;}
 public String getCameraMake(){return cameraMake;} public void setCameraMake(String v){cameraMake=v;}
 public String getCameraModel(){return cameraModel;} public void setCameraModel(String v){cameraModel=v;}
 public Double getAperture(){return aperture;} public void setAperture(Double v){aperture=v;}
 public String getShutterSpeed(){return shutterSpeed;} public void setShutterSpeed(String v){shutterSpeed=v;}
 public Integer getIso(){return iso;} public void setIso(Integer v){iso=v;}
 public Double getFocalLength(){return focalLength;} public void setFocalLength(Double v){focalLength=v;}
 public Instant getUploadedAt(){return uploadedAt;}
}
