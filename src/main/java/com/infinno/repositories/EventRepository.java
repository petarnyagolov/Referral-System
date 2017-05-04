package com.infinno.repositories;

import com.infinno.entities.campaigns.Campaign;
import com.infinno.entities.events.UserEvent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EventRepository extends CrudRepository<UserEvent,Long> {
    UserEvent findOneById(long id);
}
