package com.example.brainbounce.services;

import com.example.brainbounce.models.Community;
import com.example.brainbounce.repositories.CommunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommunityService {

    private final CommunityRepository communityRepository;

    @Autowired
    public CommunityService(CommunityRepository communityRepository) {
        this.communityRepository = communityRepository;
    }

    public Community saveCommunity(Community community) {
        return communityRepository.save(community);
    }

    public Optional<Community> findCommunityById(Long id) {
        return communityRepository.findById(id);
    }

    public List<Community> findAllCommunities() {
        return communityRepository.findAll();
    }

    public boolean deleteCommunityById(Long id) {
        if (communityRepository.existsById(id)) {
            try {
                communityRepository.deleteById(id);
                return true;
            } catch (EmptyResultDataAccessException e) {
                // Log the exception and handle it as needed
                return false;
            }
        } else {
            // Community does not exist
            return false;
        }
    }




    public void deleteCommunity(Community community) {
        communityRepository.delete(community);
    }

    public long countCommunities() {
        return communityRepository.count();
    }

}
