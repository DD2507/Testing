package com.example.demo.controller;

import com.example.demo.entity.Game;
import com.example.demo.entity.Gamingmembers;
import com.example.demo.entity.Recharge;
import com.example.demo.entity.Transaction;
import com.example.demo.service.GameService;
import com.example.demo.service.MemberService;
import com.example.demo.service.RechargeService;
import com.example.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/gamingclub")
public class Gamingclubcontroller {

    @Autowired
    private MemberService memberService;

    @Autowired
    private GameService gameService;

    @Autowired
    private RechargeService rechargeService;

    @Autowired
    private TransactionService transactionService;

    // MEMBER ENDPOINTS
    @PostMapping("/members")
    public Gamingmembers createMember(@RequestBody Gamingmembers member) {
        return memberService.createMember(member);
    }
    
    @GetMapping("/members")
    public List<Gamingmembers> getAllMembers() {
        return memberService.getAllMembers();
    }
    
    @GetMapping("/members/{id}")
    public ResponseEntity<Gamingmembers> getMemberById(@PathVariable String id) {
        Gamingmembers member = memberService.getMemberById(id);
        if (member != null) {
            return ResponseEntity.ok(member);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // GAME ENDPOINTS
    @PostMapping("/games")
    public Game createGame(@RequestBody Game game) {
        return gameService.createGame(game);
    }

    @GetMapping("/games")
    public List<Game> getAllGames() {
        return gameService.getAllGames();
    }
    
    // RECHARGE ENDPOINTS
    @PostMapping("/recharges")
    public Recharge addRecharge(@RequestBody Recharge recharge) {
        return rechargeService.addRecharge(recharge);
    }

    // TRANSACTION ENDPOINTS
    @PostMapping("/transactions")
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
        try {
            Transaction newTransaction = transactionService.createTransaction(transaction);
            return ResponseEntity.ok(newTransaction);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}