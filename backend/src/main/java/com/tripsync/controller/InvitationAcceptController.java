package com.tripsync.controller;
import com.tripsync.service.InvitationService;import org.springframework.web.bind.annotation.*;import java.util.Map;
@RestController @RequestMapping("/api/invitations") public class InvitationAcceptController{private final InvitationService service;public InvitationAcceptController(InvitationService s){service=s;}@PostMapping("/{token}/accept") public Map<String,String> accept(@PathVariable String token,@RequestHeader(value="X-User-Id",defaultValue="demo-user")String user){service.accept(token,user);return Map.of("status","accepted");}}
