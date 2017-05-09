package com.infinno.services;

import com.infinno.models.bindingModels.AddCampaignBindingModel;
import com.infinno.models.viewModels.CampaignViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CampaignService {
    void save(AddCampaignBindingModel addCampaignBindingModel);

//    List<CampaignViewModel> findAllCampaigns();

    CampaignViewModel findById(long id);
    Page<CampaignViewModel> findAll(Pageable pageable);

    void deleteById(long virusId);
}
