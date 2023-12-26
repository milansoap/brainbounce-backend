package com.example.brainbounce.repositories;
import com.example.brainbounce.models.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {

}