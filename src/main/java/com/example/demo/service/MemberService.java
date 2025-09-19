package com.example.demo.service;

import com.example.demo.entity.Gamingmembers;
import com.example.demo.repository.GamingClubMembersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MemberService {
    @Autowired
    private GamingClubMembersRepository memberRepository;

    public Gamingmembers createMember(Gamingmembers member) {
        return memberRepository.save(member);
    }

    public Gamingmembers getMemberById(String id) {
        return memberRepository.findById(id).orElse(null);
    }
    
    public List<Gamingmembers> getAllMembers() {
        return memberRepository.findAll();
    }
}