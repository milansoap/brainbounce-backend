package com.example.brainbounce.controllers;


import com.example.brainbounce.models.Content;
import com.example.brainbounce.services.ContentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contents")
public class ContentController {

    private static final Logger log = LoggerFactory.getLogger(ContentController.class);

    private final ContentService contentService;

    @Autowired
    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    @PostMapping
    public ResponseEntity<Content> createContent(@RequestBody Content content) {
        log.info("Creating content {}", content.getName());
        Content savedContent = contentService.saveContent(content);
        log.info("Created content {} with ID {}", content.getName(), savedContent.getId());
        return ResponseEntity.ok(savedContent);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Content> getContentById(@PathVariable Long id) {
        log.info("Fetching content with id {}", id);
        return contentService.findContentById(id)
                .map(content -> {
                    log.info("Fetched content with id {}", id);
                    return ResponseEntity.ok(content);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Content>> getAllContents() {
        log.info("Fetching all contents");
        List<Content> contents = contentService.findAllContents();
        log.info("Fetched all contents");
        return ResponseEntity.ok(contents);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContent(@PathVariable Long id) {
        log.info("Deleting content with id {}", id);
        contentService.deleteContentById(id);
        log.info("Deleted content with id {}", id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/community/{communityId}")
    public ResponseEntity<List<Content>> getContentsByCommunityId(@PathVariable Long communityId) {
        List<Content> contents = contentService.findContentsByCommunityId(communityId);
        return ResponseEntity.ok(contents);
    }

    @GetMapping("/created-by/{userId}")
    public ResponseEntity<List<Content>> getContentsCreatedByUserId(@PathVariable Long userId) {
        List<Content> contents = contentService.findContentsCreatedByUserId(userId);
        return ResponseEntity.ok(contents);
    }

    @GetMapping("/community/{communityId}/created-by/{userId}")
    public ResponseEntity<List<Content>> getContentsByCommunityIdAndCreatedBy(
            @PathVariable Long communityId,
            @PathVariable Long userId) {
        List<Content> contents = contentService.findContentsByCommunityIdAndCreatedBy(communityId, userId);
        return ResponseEntity.ok(contents);
    }


}
