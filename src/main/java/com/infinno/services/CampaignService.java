package com.infinno.services;

import com.infinno.models.bindingModels.AddCampaignBindingModel;
import com.infinno.models.viewModels.CampaignViewModel;

import java.util.List;

public interface CampaignService {
    void save(AddCampaignBindingModel addCampaignBindingModel);

    List<CampaignViewModel> findAllCampaigns();

    void deleteById(long virusId);
}
