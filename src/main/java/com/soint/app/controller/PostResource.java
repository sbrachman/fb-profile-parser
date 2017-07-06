package com.soint.app.controller;

import com.soint.app.service.FacebookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("api")
public class PostResource {

    private final Logger log = LoggerFactory.getLogger(PostResource.class);

    @Autowired
    FacebookService facebookService;

    /**
     * GET /posts : get most common words (from all posts) with the number of their occurrences
     *
     * @return the ResponseEntity with status 200 (OK) and with the body all words
     */
    @GetMapping("/posts/words")
    public ResponseEntity<Map<String, Long>> findMostCommonWords() {
        log.debug("REST request to find most common words");
        Map<String, Long> mostCommonWords = facebookService.findMostCommonWords();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(mostCommonWords.size()));
        return new ResponseEntity<>(mostCommonWords, headers, HttpStatus.OK);
    }

    /**
     * GET /posts : get all posts ids that contain keyword
     *
     * @param keyword the word included in posts to find
     * @return the ResponseEntity with status 200 (OK) and with the body posts ids,
     * or with status 404 (Not Found)
     */
    @GetMapping("/posts")
    public ResponseEntity<Set<String>> findPostsIdsByKeyword(@RequestParam("keyword") String keyword) {
        log.debug("REST request to find posts with word " + keyword);
        Set<String> postIdsWithWord = facebookService.findPostIdsByKeyword(keyword);
        if (postIdsWithWord.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(postIdsWithWord.size()));
        return new ResponseEntity<>(postIdsWithWord, headers, HttpStatus.OK);
    }

}
