package com.infinno.repositories;

import com.infinno.entities.campaigns.Campaign;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CampaignRepository extends CrudRepository<Campaign,Long> {

}
