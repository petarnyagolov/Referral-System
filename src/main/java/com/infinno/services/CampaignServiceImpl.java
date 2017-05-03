package com.infinno.services;

import com.infinno.entities.campaigns.Campaign;
import com.infinno.models.bindingModels.AddCampaignBindingModel;
import com.infinno.models.viewModels.CampaignViewModel;
import com.infinno.repositories.CampaignRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CampaignServiceImpl implements CampaignService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CampaignRepository campaignRepository;

    @Override
    public void save(AddCampaignBindingModel addCampaignBindingModel) {

        Campaign campaign = this.modelMapper.map(addCampaignBindingModel,Campaign.class);
        campaign.setDescription(addCampaignBindingModel.getDescription());
        campaign.setStartDate(addCampaignBindingModel.getStartDate());
        campaign.setEndDate(addCampaignBindingModel.getEndDate());
        campaign.setUserEvent(addCampaignBindingModel.getUserEvents());
        campaignRepository.save(campaign);
    }

    @Override
    public List<CampaignViewModel> findAllCampaigns() {

        List<CampaignViewModel> models = new ArrayList<>();
        List<Campaign> campaigns = (List<Campaign>) this.campaignRepository.findAll();
        for (Campaign campaign : campaigns) {

            CampaignViewModel model = this.modelMapper.map(campaign, CampaignViewModel.class);

            models.add(model);
        }

        return models;
    }

    @Override
    public void deleteById(long virusId) {

    }
}
