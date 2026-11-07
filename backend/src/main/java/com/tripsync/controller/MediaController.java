package com.tripsync.controller;
import com.tripsync.dto.ApiDtos.*; import com.tripsync.entity.MediaAsset; import com.tripsync.service.MediaService; import jakarta.validation.Valid; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController @RequestMapping("/api") public class MediaController {private final MediaService service; public MediaController(MediaService s){service=s;}
 @PostMapping("/groups/{groupId}/media/upload-url") public UploadResponse upload(@PathVariable UUID groupId,@Valid @RequestBody UploadRequest r,@RequestHeader(value="X-User-Id",defaultValue="demo-user") String u){return service.createUpload(groupId,r,u);}
 @PostMapping("/media/{id}/complete") public MediaAsset complete(@PathVariable UUID id,@RequestHeader(value="X-User-Id",defaultValue="demo-user") String u){return service.complete(id,u);}
 @GetMapping("/groups/{groupId}/media") public List<MediaAsset> list(@PathVariable UUID groupId,@RequestHeader(value="X-User-Id",defaultValue="demo-user") String u){return service.list(groupId,u);}
 @GetMapping("/media/{id}/download-url") public Map<String,String> download(@PathVariable UUID id,@RequestHeader(value="X-User-Id",defaultValue="demo-user") String u){return Map.of("url",service.downloadUrl(id,u));}
 @PostMapping("/media/{id}/share-link") public ShareResponse share(@PathVariable UUID id,@RequestHeader(value="X-User-Id",defaultValue="demo-user") String u){return service.share(id,u);}
 @GetMapping("/share/{token}") public ShareView resolve(@PathVariable String token){return service.resolveShare(token);}
}
