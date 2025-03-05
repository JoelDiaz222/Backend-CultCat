package com.cultcat.backend.controller;

import com.cultcat.backend.config.JwtTokenUtil;
import com.cultcat.backend.model.Usuari;
import com.cultcat.backend.repository.UsuariRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private static final int STARTING_POINTS = 100;
    private static final int MAX_NAME_INT_BOUND = 9999;
    private static final String googleClientId = Dotenv.load().get("CLIENT_ID");
    private static final Random RANDOM = new Random();

    private final UsuariRepository usuariRepository;

    private AuthController(UsuariRepository usuariRepository) {
        this.usuariRepository = usuariRepository;
    }

    @PostMapping("/google")
    public ResponseEntity<?> authenticateWithGoogle(@RequestBody String requestBody) throws GeneralSecurityException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> body = objectMapper.readValue(requestBody, Map.class);
        String idTokenString = body.get("id_token");

        if (idTokenString == null || idTokenString.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falta el token ID.");

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
                .setAudience(Collections.singletonList(googleClientId))
                .build();

        GoogleIdToken idToken = verifier.verify(idTokenString);
        if (idToken != null) {
            GoogleIdToken.Payload payload = idToken.getPayload();
            final String googleId = payload.getSubject();
            final String email = payload.getEmail();

            Optional<Usuari> usuari = usuariRepository.findByGoogleId(googleId);
            if (usuari.isEmpty()) {
                final String name = payload.get("name").toString();

                Usuari newUsuari = new Usuari(
                        googleId,
                        email,
                        generateUsername(name),
                        name,
                        payload.get("picture").toString(),
                        STARTING_POINTS,
                        new HashSet<>()
                );

                usuariRepository.save(newUsuari);
            }

            final String jwtToken = JwtTokenUtil.generateToken(googleId, email);
            return ResponseEntity.ok(Collections.singletonMap("jwt", jwtToken));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("El token ID de Google no és vàlid.");
        }
    }

    public static String generateUsername(String name) {
        final String base = name.trim().toLowerCase().replaceAll("[^\\p{L}]", "");
        final int randomNum = 1000 + RANDOM.nextInt(MAX_NAME_INT_BOUND);
        return base + randomNum;
    }
}
