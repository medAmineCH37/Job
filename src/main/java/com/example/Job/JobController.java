package com.example.Job;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobService service;

    @GetMapping
    public List<Job> getAllJobs() {
        return service.getAllJobs();
    }

    @GetMapping("/{id}")
    public Optional<Job> getJobById(@PathVariable int id) {
        return service.getJobById(id);
    }

    @GetMapping("/service/{name}")
    public Optional<Job> getJobByService(@PathVariable String name) {
        return service.getJobByService(name);
    }

    @PutMapping("admin/{id}/etat")
    public Job updateEtat(@PathVariable int id, @RequestParam boolean etat, KeycloakAuthenticationToken auth) {
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
        KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
        boolean hasAdminRole = context.getToken().getRealmAccess().isUserInRole("admin");

        if (hasAdminRole) {
            return service.updateEtat(id, etat);
        }
        else {
            return null;
        }

    }
}
