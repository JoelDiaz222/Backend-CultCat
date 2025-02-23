package com.cultcat.backend.service;

import com.cultcat.backend.model.Usuari;
import com.cultcat.backend.repository.UsuariRepository;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.Random;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    final static int STARTING_POINTS = 100;
    final static int MAX_NAME_INT_BOUND = 9999;
    final static Random RANDOM = new Random();
    private final UsuariRepository usuariRepository;

    public CustomOAuth2UserService(UsuariRepository usuariRepository) {
        this.usuariRepository = usuariRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        final OAuth2User oAuth2User = new DefaultOAuth2UserService().loadUser(userRequest);

        final String sub = oAuth2User.getAttribute("sub");
        final String name = oAuth2User.getAttribute("name");
        final String email = oAuth2User.getAttribute("email");
        final String picture = oAuth2User.getAttribute("picture");

        usuariRepository.findByGoogleId(sub).orElseGet(() -> {
            final Usuari newUsuari = new Usuari(sub, email, generateUsername(name), name, picture, STARTING_POINTS);
            return usuariRepository.save(newUsuari);
        });

        return new org.springframework.security.oauth2.core.user.DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("USER")),
                oAuth2User.getAttributes(),
                "sub"
        );
    }

    public static String generateUsername(String name) {
        final String base = name.trim().toLowerCase().replaceAll("[^\\p{L}]", "");
        final int randomNum = 1000 + RANDOM.nextInt(MAX_NAME_INT_BOUND);
        return base + randomNum;
    }
}
