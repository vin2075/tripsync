package com.tripsync.service;
import com.tripsync.dto.ApiDtos.*; import com.tripsync.entity.*; import com.tripsync.repository.*; import org.springframework.stereotype.Service; import org.springframework.web.server.ResponseStatusException; import org.springframework.http.HttpStatus; import java.util.*;
@Service public class TripService {
 private final TripGroupRepository groups; private final GroupMemberRepository members;
 public TripService(TripGroupRepository g,GroupMemberRepository m){groups=g;members=m;}
 public TripGroup create(CreateGroup req,String user){TripGroup g=new TripGroup();g.setName(req.name());g.setDescription(req.description());g.setOwnerId(user);g.setStartsAt(req.startsAt());g.setEndsAt(req.endsAt());g.setExpiresAt(req.expiresAt());g=groups.save(g);members.save(new GroupMember(g.getId(),user,"OWNER"));return g;}
 public List<TripGroup> list(String user){var ids=members.findByUserId(user).stream().map(GroupMember::getGroupId).toList();return groups.findDistinctByOwnerIdOrIdIn(user,ids);}
 public TripGroup requireMember(UUID id,String user){if(!members.existsByGroupIdAndUserId(id,user))throw new ResponseStatusException(HttpStatus.FORBIDDEN,"You are not a member of this trip");return groups.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Trip not found"));}
}
