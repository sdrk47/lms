package com.said.lms.controller;

import com.said.lms.model.Basket;
import com.said.lms.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/basket")
public class BasketController {
    private final BasketService basketService;

    @GetMapping("/show-all-courses")
    public Basket getBaskets(@AuthenticationPrincipal UserDetails userDetails) {
        return basketService.findAllOwnCourses(userDetails);
    }

    @PostMapping("/add-course/{courseId}")
    public Basket addCourseToBasket(@PathVariable Long courseId,
                                    @AuthenticationPrincipal UserDetails userDetails
    ) {
        return basketService.addCourseToBasket(courseId, userDetails);
    }
}
