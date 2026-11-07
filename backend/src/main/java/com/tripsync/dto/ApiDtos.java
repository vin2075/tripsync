package com.tripsync.dto;
import jakarta.validation.constraints.*; import java.time.Instant; import java.util.UUID;
public final class ApiDtos { private ApiDtos(){}
 public record CreateGroup(@NotBlank @Size(max=100) String name,@Size(max=1000) String description,Instant startsAt,Instant endsAt,Instant expiresAt){}
 public record UploadRequest(@NotBlank String fileName,@NotBlank String contentType,@NotNull @Positive Long sizeBytes,Instant capturedAt,Double latitude,Double longitude,String cameraMake,String cameraModel,Double aperture,String shutterSpeed,Integer iso,Double focalLength){}
 public record UploadResponse(UUID mediaId,String uploadUrl,String objectKey,Instant urlExpiresAt){}
 public record ShareResponse(String url,Instant expiresAt){}
 public record ShareView(UUID mediaId,String fileName,String contentType,String downloadUrl,Instant expiresAt){}
}
