package com.example.demo.repository;

import com.example.demo.entity.Gamingmembers;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GamingClubMembersRepository extends MongoRepository<Gamingmembers, String> {
    Gamingmembers findByPhone(String phone);
}