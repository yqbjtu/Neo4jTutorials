package com.yq.repository;

import com.yq.domain.Movie;
import com.yq.domain.MovieData;
import com.yq.domain.Person;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

/**
 */
public interface MovieRepository extends Neo4jRepository<Movie, Long> , CustomizedMovieRepository{

	Movie findByTitle(@Param("title") String title);

    Collection<Movie> findByRevenueGreaterThan(int revenue);


    @Query("MATCH (movie:Movie)<-[r:DIRECTED]-(), (movie)<-[r2:ACTED_IN]-(p:Person) " +
            "WHERE movie.title={0} " +
            "RETURN movie as movie, COLLECT(p) AS cast")
    MovieData getMovieData(String title);

}