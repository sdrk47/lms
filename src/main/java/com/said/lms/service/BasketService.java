package com.said.lms.service;

import com.said.lms.model.Basket;
import com.said.lms.model.User;
import com.said.lms.repository.BasketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.CopyOnWriteArrayList;

@Service
@RequiredArgsConstructor
public class BasketService {

    private final BasketRepository basketRepository;

    public void create(User user) {
        Basket basket = Basket.builder()
                .courses(new CopyOnWriteArrayList<>())
                .user(user)
                .build();
        basketRepository.save(basket);
    }
}
