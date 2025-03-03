package com.cultcat.backend.controller;

import com.cultcat.backend.model.Usuari;
import com.cultcat.backend.service.UsuariService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UsuariController {
    private final UsuariService usuariService;

    private UsuariController(UsuariService usuariService) {
        this.usuariService = usuariService;
    }

    @GetMapping("/me")
    public Map<String, Object> getUser(@AuthenticationPrincipal OAuth2User user) {
        return user.getAttributes();
    }

    @GetMapping("/myEntity")
    public Optional<Usuari> getUserEntity(@AuthenticationPrincipal OAuth2User user) {
        return usuariService.findByGoogleId(user.getAttribute("sub"));
    }
}
