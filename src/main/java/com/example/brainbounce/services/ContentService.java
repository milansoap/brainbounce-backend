package com.example.brainbounce.services;

import com.example.brainbounce.models.Content;
import com.example.brainbounce.repositories.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContentService {

    private final ContentRepository contentRepository;

    public ContentService(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    public Content saveContent(Content content) {
        return contentRepository.save(content);
    }


    public List<Content> findContentsByCommunityId(Long communityId) {
        return contentRepository.findByCommunityId(communityId);
    }

    public List<Content> findContentsCreatedByUserId(Long userId) {
        return contentRepository.findByCreatedBy(userId);
    }

    public Optional<Content> findContentById(Long id) {
        return contentRepository.findById(id);
    }

    public List<Content> findAllContents() {
        return contentRepository.findAll();
    }

    public void deleteContentById(Long id) {
        contentRepository.deleteById(id);
    }

    public void likeContentById(Long id) {
        Content contentToLike = contentRepository.findById(id)
                .orElseThrow();
        contentToLike.setNumOfLikes(contentToLike.getNumOfLikes() + 1);
        contentRepository.save(contentToLike);
    }

    public void dislikeContentById(Long id) {
        Content contentToLike = contentRepository.findById(id)
                .orElseThrow();
        contentToLike.setNumOfLikes(contentToLike.getNumOfLikes() - 1);
        contentRepository.save(contentToLike);
    }

    public List<Content> findContentsByCommunityIdAndCreatedBy(Long communityId, Long createdBy) {
        return contentRepository.findByCommunityIdAndCreatedBy(communityId, createdBy);
    }


    // You can add more methods here as per your requirements
}