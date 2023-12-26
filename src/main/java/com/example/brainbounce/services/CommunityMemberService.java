package com.example.brainbounce.services;

import com.example.brainbounce.models.Community;
import com.example.brainbounce.models.CommunityMember;
import com.example.brainbounce.repositories.CommunityMemberRepository;
import com.example.brainbounce.repositories.CommunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommunityMemberService {

    private final CommunityMemberRepository communityMemberRepository;

    @Autowired
    public CommunityMemberService(CommunityMemberRepository communityMemberRepository) {
        this.communityMemberRepository = communityMemberRepository;
    }


    public List<CommunityMember> findAllCommunityMembers(Long communityId) {
        return communityMemberRepository.findByCommunityId(communityId);
    }

    // In your CommunityMemberService class
    public Long countByCommunityId(Long communityId) {
        return communityMemberRepository.countByCommunityId(communityId);
    }

    public CommunityMember saveCommunityMember(CommunityMember communityMember) {
        // Check if the user is already a member of the community
        Optional<CommunityMember> existingMember =
                communityMemberRepository.findByUserIdAndCommunityId(
                        communityMember.getUser().getId(),
                        communityMember.getCommunity().getId()
                );

        if (existingMember.isPresent()) {
            throw new IllegalStateException("User is already a member of this community.");
        }

        return communityMemberRepository.save(communityMember);
    }


    public Optional<CommunityMember> findCommunityMemberById(Long id) {
        return communityMemberRepository.findById(id);
    }


    public boolean deleteCommunityMemberById(Long id) {
        if (communityMemberRepository.existsById(id)) {
            try {
                communityMemberRepository.deleteById(id);
                return true;
            } catch (EmptyResultDataAccessException e) {
                // Log the exception and handle it as needed
                return false;
            }
        } else {
            // Community member does not exist
            return false;
        }
    }

    public void deleteCommunityMember(CommunityMember communityMember) {
        communityMemberRepository.delete(communityMember);
    }

    public long countCommunityMembers() {
        return communityMemberRepository.count();
    }

    public List<Community> findAllCommunitiesByUserId(Long userId) {
        return communityMemberRepository.findCommunitiesByUserId(userId);
    }

}
