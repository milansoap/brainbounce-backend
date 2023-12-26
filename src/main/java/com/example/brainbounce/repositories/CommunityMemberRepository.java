package com.example.brainbounce.repositories;
import com.example.brainbounce.models.Community;
import com.example.brainbounce.models.CommunityMember;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommunityMemberRepository  extends BaseRepository<CommunityMember, Long>{
    List<CommunityMember> findByCommunityId(Long communityId);
    Long countByCommunityId(Long communityId);

    Optional<CommunityMember> findByUserIdAndCommunityId(Long userId, Long communityId);

    @Query("SELECT cm.community FROM CommunityMember cm WHERE cm.user.id = :userId")
    List<Community> findCommunitiesByUserId(@Param("userId") Long userId);


}
