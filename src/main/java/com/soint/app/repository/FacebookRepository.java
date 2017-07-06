package com.soint.app.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soint.app.domain.Facebook;
import com.soint.app.domain.Post;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


import java.io.File;
import java.io.IOException;
import java.util.*;

@Component
public class FacebookRepository {

    private final Logger log = LoggerFactory.getLogger(FacebookRepository.class);

    private Map<String, Facebook> fbProfiles;
    private Set<Post> posts;
    private final String directoryPath = "src/main/java/com/soint/app/json/data";
    private final String[] allowedExtensions = {"json"};

    public Map<String, Facebook> getFbProfiles() {
        return fbProfiles;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void loadDirectory() {
        fbProfiles = new HashMap<>();
        posts = new HashSet<>();
        Iterator filesIterator = FileUtils
                .iterateFiles(new File(directoryPath), allowedExtensions , false);
        while(filesIterator.hasNext()){
            File file = (File) filesIterator.next();
            Optional<Facebook> fbProfile = Optional.ofNullable(jsonToObject(file));
            fbProfile.ifPresent(this::addProfile);
        }
        log.info(fbProfiles.size() + " Facebook profiles loaded from files");
    }

    private Facebook jsonToObject(File jsonFile) {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        Facebook profile = null;
        try {
            profile = mapper.readValue(jsonFile, Facebook.class);
        } catch (IOException e) {
            log.error("Parsing file " + jsonFile.getName() + " Failed");
        }
        return profile;
    }

    private void addProfile(Facebook profile){
        fbProfiles.put(profile.getId(), profile);
        posts.addAll(profile.getPosts());
    }
}
