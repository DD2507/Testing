package com.example.demo.service;

import com.example.demo.entity.Gamingmembers;
import com.example.demo.entity.Recharge;
import com.example.demo.repository.GamingClubMembersRepository;
import com.example.demo.repository.RechargeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RechargeService {
    @Autowired
    private RechargeRepository rechargeRepository;
    
    @Autowired
    private GamingClubMembersRepository gamingClubMembersRepository;

    public Recharge addRecharge(Recharge recharge) {
        Gamingmembers member = gamingClubMembersRepository.findById(recharge.getMemberId()).orElseThrow(
            () -> new RuntimeException("Member not found with ID: " + recharge.getMemberId())
        );
        
        member.setBalance(member.getBalance() + recharge.getAmount());
        gamingClubMembersRepository.save(member);
        
        return rechargeRepository.save(recharge);
    }
}