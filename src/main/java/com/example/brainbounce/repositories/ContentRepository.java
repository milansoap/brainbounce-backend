package com.example.brainbounce.repositories;

import com.example.brainbounce.models.CommunityMember;
import com.example.brainbounce.models.Content;

import java.util.List;

public interface ContentRepository extends BaseRepository<Content, Long>{
    List<Content> findByCommunityId(Long communityId);
    List<Content> findByCreatedBy(Long userId);
    List<Content> findByCommunityIdAndCreatedBy(Long communityId, Long createdBy);

}
