package com.yq.other;

import com.yq.controller.PersonController;
import com.yq.domain.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * Created by yangqian on 2018/4/21.
 */
@Component
public class MyEventListener {
    private static Logger log =  LoggerFactory.getLogger(MyEventListener.class);

//    @TransactionalEventListener(condition = "#creationEvent.awesome")
//    public void handleOrderCreatedEvent(CreationEvent<Movie> creationEvent) {
//        log.info("CreatedEvent:" + );
//    }
}

