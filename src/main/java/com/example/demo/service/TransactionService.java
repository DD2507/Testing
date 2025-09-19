package com.example.demo.service;

import com.example.demo.entity.Game;
import com.example.demo.entity.Gamingmembers;
import com.example.demo.entity.Transaction;
import com.example.demo.repository.GamingRepository;
import com.example.demo.repository.GamingClubMembersRepository;
import com.example.demo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    
    @Autowired
    private GamingClubMembersRepository gamingClubMembersRepository;
    
    @Autowired
    private GamingRepository gamingRepository;

    public Transaction createTransaction(Transaction transaction) {
        Gamingmembers member = gamingClubMembersRepository.findById(transaction.getMemberId()).orElseThrow(
            () -> new RuntimeException("Member not found with ID: " + transaction.getMemberId())
        );
        
        Game game = gamingRepository.findById(transaction.getGameId()).orElseThrow(
            () -> new RuntimeException("Game not found with ID: " + transaction.getGameId())
        );
        
        if (member.getBalance() < game.getPrice()) {
            throw new IllegalStateException("Insufficient balance to play this game.");
        }
        
        member.setBalance(member.getBalance() - game.getPrice());
        gamingClubMembersRepository.save(member);
        
        transaction.setAmount(game.getPrice());
        return transactionRepository.save(transaction);
    }
}