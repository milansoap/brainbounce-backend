package com.example.brainbounce.controllers;

import com.example.brainbounce.models.Community;
import com.example.brainbounce.models.CommunityMember;
import com.example.brainbounce.services.CommunityMemberService;
import com.example.brainbounce.services.CommunityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/communities")
public class CommunityController {

    private static final Logger log = LoggerFactory.getLogger(CommunityController.class);

    private final CommunityService communityService;
    private final CommunityMemberService communityMemberService;

    @Autowired
    public CommunityController(CommunityService communityService, CommunityMemberService communityMemberService) {
        this.communityService = communityService;
        this.communityMemberService = communityMemberService;
    }

    @PostMapping
    public ResponseEntity<?> createCommunity(@RequestBody Community community) {
        log.info("Creating community {}", community.getName());
        Community savedCommunity = communityService.saveCommunity(community);
        log.info("Created community {} 200", community.getName());
        return ResponseEntity.ok(savedCommunity);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCommunityById(@PathVariable Long id) {
        log.info("Fetching community with id {}", id);
        return communityService.findCommunityById(id)
                .map(community -> {
                    log.info("Fetched community with id {} 200", id);
                    return ResponseEntity.ok(community);
                })
                .orElseGet(() -> {
                    log.info("Community with id {} not found 404", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @GetMapping
    public ResponseEntity<?> getAllCommunities() {
        log.info("Fetching all communities");
        List<Community> communities = communityService.findAllCommunities();
        log.info("Fetched all communities 200");
        return ResponseEntity.ok(communities);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCommunity(@PathVariable Long id, @RequestBody Community community) {
        log.info("Updating community with id {}", id);
        return communityService.findCommunityById(id)
                .map(existingCommunity -> {
                    community.setId(existingCommunity.getId());
                    Community updatedCommunity = communityService.saveCommunity(community);
                    log.info("Updated community with id {} 200", id);
                    return ResponseEntity.ok(updatedCommunity);
                })
                .orElseGet(() -> {
                    log.info("Community with id {} not found 404", id);
                    return ResponseEntity.notFound().build();
                });
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCommunity(@PathVariable Long id) {
        boolean success = communityService.deleteCommunityById(id);
        if (success) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/count")
    public ResponseEntity<?> countCommunities() {
        log.info("Counting all communities");
        long count = communityService.countCommunities();
        log.info("Counted all communities 200");
        return ResponseEntity.ok(count);
    }

    @GetMapping("/{communityId}/members")
    public ResponseEntity<List<CommunityMember>> getAllCommunityMembers(@PathVariable Long communityId) {
        List<CommunityMember> members = communityMemberService.findAllCommunityMembers(communityId);
        return ResponseEntity.ok(members);
    }

    @GetMapping("/{communityId}/members/count")
    public ResponseEntity<Long> getAllCommunityMembersCount(@PathVariable Long communityId) {
        Long count = communityMemberService.countByCommunityId(communityId);
        return ResponseEntity.ok(count);
    }

    @DeleteMapping("/{communityId}/members/{memberId}")
    public ResponseEntity<Void> deleteCommunityMember(@PathVariable Long communityId, @PathVariable Long memberId) {
        boolean success = communityMemberService.deleteCommunityMemberById(memberId);
        if (success) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/members/create")
    public ResponseEntity<CommunityMember> createCommunityMember(@RequestBody CommunityMember communityMember) {
        CommunityMember newMember = communityMemberService.saveCommunityMember(communityMember);
        return ResponseEntity.ok(newMember);
    }



//    @PutMapping("/{communityId}/members/{memberId}")
//    public ResponseEntity<CommunityMember> updateCommunityMember(@PathVariable Long communityId, @PathVariable Long memberId, @RequestBody CommunityMember communityMember) {
//        CommunityMember updatedMember = communityMemberService.updateCommunityMember(communityId, memberId, communityMember);
//        return ResponseEntity.ok(updatedMember);
//    }
//
//    @GetMapping("/{communityId}/members/moderators")
//    public ResponseEntity<List<CommunityMember>> getCommunityModerators(@PathVariable Long communityId) {
//        List<CommunityMember> moderators = communityMemberService.findModeratorsByCommunityId(communityId);
//        return ResponseEntity.ok(moderators);
//    }
//
//    @GetMapping("/{communityId}/members/owners")
//    public ResponseEntity<List<CommunityMember>> getCommunityOwners(@PathVariable Long communityId) {
//        List<CommunityMember> owners = communityMemberService.findOwnersByCommunityId(communityId);
//        return ResponseEntity.ok(owners);
//    }
//
//

}
