package com.example.demo.service;

import com.example.demo.entity.Game;
import com.example.demo.repository.GamingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GameService {
    @Autowired
    private GamingRepository gamingRepository;

    public Game createGame(Game game) {
        return gamingRepository.save(game);
    }

    public List<Game> getAllGames() {
        return gamingRepository.findAll();
    }
}