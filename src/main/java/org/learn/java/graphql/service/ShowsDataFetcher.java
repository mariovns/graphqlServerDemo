package org.learn.java.graphql.service;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;

import java.util.List;
import java.util.stream.Collectors;

/**
 * codegen didn't worked with error
 The org.gradle.api.plugins.Convention type has been deprecated.`com.netflix.dgs.codegen`
 This is scheduled to be removed in Gradle 9.0.
 The org.gradle.api.plugins.Convention type has been deprecated.
 - The org.gradle.api.plugins.Convention type has been deprecated.`the-org-gradle-api-plugins-convention-type-has-been-deprecated`
 */
@DgsComponent
public class ShowsDataFetcher {

    private final List<Show> shows = List.of(
            new Show("Stranger Things", 2016),
            new Show("Ozark", 2017),
            new Show("The Crown", 2016),
            new Show("Dead to Me", 2019),
            new Show("Orange is the New Black", 2013)
    );

    @DgsQuery
    public List<Show> shows(@InputArgument String title) {
        if(title == null) {
            return shows;
        }

        return shows.stream().filter(s -> s.title().contains(title)).collect(Collectors.toList());
    }

    @DgsQuery
    public List<Show> showsByYear(@InputArgument Integer releaseYear) {
        if(releaseYear == null) {
            return shows;
        }

        return shows.stream().filter(s -> s.releaseYear().equals(releaseYear)).collect(Collectors.toList());
    }
}

record Show(String title, Integer releaseYear) {}
