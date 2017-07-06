package com.soint.app.service;


import com.soint.app.domain.Facebook;
import com.soint.app.repository.FacebookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;



@Service
public class FacebookServiceImpl implements FacebookService {

    private final Logger log = LoggerFactory.getLogger(FacebookServiceImpl.class);

    @Autowired
    FacebookRepository facebookRepository;

    /**
     * Java 8 HashMap complexity for search operations: best case O(1), worst case O(log n)
     */
    @Override
    public Facebook findById(String id) throws NotFoundException {
        if (facebookRepository.getFbProfiles().containsKey(id)){
            return facebookRepository.getFbProfiles().get(id);
        } else {
            throw new NotFoundException("User with id " + id +" does not exist");
        }
    }

    @Override
    public Map<String, Long> findMostCommonWords() {
        Map<String, Long> wordsCounter = new HashMap<>();
        facebookRepository.getPosts()
                .stream()
                .map(post -> post.getMessage())
                .flatMap(message -> Arrays.stream(message.split("[^a-zA-Z]+")))
                .map(word -> word.toLowerCase())
                .forEach((word) -> {
                        if (wordsCounter.containsKey(word)){
                            long val = wordsCounter.get(word) + 1;
                            wordsCounter.put(word, val);
                        } else {
                            wordsCounter.put(word, 1L);
                        }
                });

        return wordsCounter
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e2, LinkedHashMap::new));
    }

    @Override
    public Set<String> findPostIdsByKeyword(String word) {
        return facebookRepository.getPosts()
                .stream()
                .filter(post -> post.getMessage()
                        .toLowerCase()
                        .matches(".*\\b" + word.toLowerCase() + "\\b.*"))
                .map(post -> post.getId())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Facebook> findAll() {
        Comparator firstLastNameComparator = Comparator.comparing(Facebook::getFirstname)
                        .thenComparing(Comparator.comparing(Facebook::getLastname));
        Supplier<TreeSet<Facebook>> supplier = () -> new TreeSet<Facebook>(firstLastNameComparator);
        return facebookRepository.getFbProfiles()
                .values()
                .stream()
                .collect(Collectors.toCollection(supplier));
    }

    @PostConstruct
    public void loadProfilesFromJSON() {
       facebookRepository.loadDirectory();
       log.debug(facebookRepository.getFbProfiles().size() + "");
    }
}
