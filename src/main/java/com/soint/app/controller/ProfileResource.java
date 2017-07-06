package com.soint.app.controller;

import com.soint.app.domain.Facebook;
import com.soint.app.service.FacebookService;
import com.soint.app.service.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("api")
public class ProfileResource {

    private final Logger log = LoggerFactory.getLogger(ProfileResource.class);

    @Autowired
    FacebookService facebookService;

    /**
     * GET /profiles : get all facebook profiles sorted by firstname, lastname
     *
     * @return the ResponseEntity with status 200 (OK) and with the body all facebook profiles
     */
    @GetMapping("/profiles")
    public ResponseEntity<Set<Facebook>> getAllProfiles() {
        log.debug("REST request to get all profiles");
        Set<Facebook> allProfiles = facebookService.findAll();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(allProfiles.size()));
        return new ResponseEntity<>(allProfiles, headers, HttpStatus.OK);
    }

    /**
     * GET /profiles/:id : get profile with id
     *
     * @param id the id of the profile to find
     * @return the ResponseEntity with status 200 (OK) and with body the "id" profile,
     * or with status 404 (Not Found)
     */
    @GetMapping("/profiles/{id}")
    public ResponseEntity<Facebook> getProfile(@PathVariable String id) {
        log.debug("REST request to get profile with id " + id);
        try {
            Facebook profile = facebookService.findById(id);
            return ResponseEntity.ok(profile);
        } catch (NotFoundException e) {
            log.error("Unable to find user", e);
            return ResponseEntity.notFound().build();
        }
    }


}
