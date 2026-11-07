package com.tripsync.controller;
import com.tripsync.dto.ApiDtos.*; import com.tripsync.entity.TripGroup; import com.tripsync.service.TripService; import com.tripsync.repository.GroupMemberRepository; import jakarta.validation.Valid; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController @RequestMapping("/api/groups") public class TripController {private final TripService service; private final GroupMemberRepository members; public TripController(TripService s,GroupMemberRepository m){service=s;members=m;}
 @PostMapping public TripGroup create(@Valid @RequestBody CreateGroup r,@RequestHeader(value="X-User-Id",defaultValue="demo-user") String user){return service.create(r,user);}
 @GetMapping public List<TripGroup> list(@RequestHeader(value="X-User-Id",defaultValue="demo-user") String user){return service.list(user);}
 @GetMapping("/{id}/members") public List<Map<String,String>> members(@PathVariable UUID id,@RequestHeader(value="X-User-Id",defaultValue="demo-user") String user){service.requireMember(id,user);return members.findByGroupId(id).stream().map(m->Map.of("userId",m.getUserId(),"role",m.getRole())).toList();}
 @GetMapping("/{id}") public TripGroup get(@PathVariable UUID id,@RequestHeader(value="X-User-Id",defaultValue="demo-user") String user){return service.requireMember(id,user);}
}
