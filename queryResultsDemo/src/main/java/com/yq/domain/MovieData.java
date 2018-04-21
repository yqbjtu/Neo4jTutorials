package com.yq.domain;

import org.springframework.data.neo4j.annotation.QueryResult;

import java.util.Set;

/**
 * Created by yangqian on 2018/4/21.
 */
@QueryResult
public class MovieData {
    Movie movie;
    Set<Person> cast;

    @Override
    public String toString() {
        StringBuffer strBuf = new StringBuffer();
        strBuf.append("movie title:" + movie.getTitle() + ", cast size:" +  cast.size());
        if (cast != null ) {
            for (Person p : cast) {
                strBuf.append(", name:" + p.getFirstName());
            }
        }

        return strBuf.toString();
    }
}
