package com.infinno.repositories;

import com.infinno.entities.ReferralIdentifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReferralIdentifierRepository extends CrudRepository<ReferralIdentifier,Long> {

}
