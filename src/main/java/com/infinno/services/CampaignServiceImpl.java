package com.infinno.services;

import com.infinno.entities.campaigns.*;
import com.infinno.entities.events.IncomeUserEvent;
import com.infinno.entities.events.NewUserEvent;
import com.infinno.entities.events.UserEvent;
import com.infinno.exception.CampaignNotFoundException;
import com.infinno.models.bindingModels.AddCampaignBindingModel;
import com.infinno.models.viewModels.CampaignViewModel;
import com.infinno.repositories.CampaignRepository;
import com.infinno.repositories.EventRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CampaignServiceImpl implements CampaignService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private EventRepository eventRepository;

    @Override
    public void save(AddCampaignBindingModel addCampaignBindingModel) {

        String event = addCampaignBindingModel.getEventType();
        System.out.println(event);

       switch (addCampaignBindingModel.getCampaignType()){
           case "topup":
               TopupCampaign topupCampaign = this.modelMapper.map(addCampaignBindingModel,TopupCampaign.class);

               campaignRepository.save(topupCampaign);
               break;
           case "discountTopup":
               DiscountTopupCampaign discountTopupCampaign = this.modelMapper.map(addCampaignBindingModel,DiscountTopupCampaign.class);

               campaignRepository.save(discountTopupCampaign);
               break;
           case "percentTopup":
               PercentTopupCampaign percentTopupCampaign = this.modelMapper.map(addCampaignBindingModel,PercentTopupCampaign.class);

               campaignRepository.save(percentTopupCampaign);
               break;
           case "discountPercentTopup":
               DiscountPercentTopupCampaign discountPercentTopupCampaign = this.modelMapper.map(addCampaignBindingModel,DiscountPercentTopupCampaign.class);

               campaignRepository.save(discountPercentTopupCampaign);
               break;

       }

    }

private   void setEventAndCampaignInView(Campaign campaign,CampaignViewModel model){

    switch (campaign.getClass().getSimpleName()){
        case "TopupCampaign":
            model.setTypeCampaign("Получаване на твърда сума");
            break;
        case "PercentTopupCampaign":
            model.setTypeCampaign("Получаване на процент от сума");
            break;
        case "DiscountTopupCampaign":
            model.setTypeCampaign("Отстъпка с твърда сума за следващо плащане");
            break;
        case "DiscountPercentTopupCampaign":
            model.setTypeCampaign("Отстъпка с процент за следващо плащане");
            break;
    }
    switch (campaign.getUserEvent().iterator().next().getClass().getSimpleName()){
        case "NewUserEvent":
            model.setTypeEvent("Доведен потребител");
            break;
        case "IncomeUserEvent":
            model.setTypeEvent("Парични постъпления");
            break;

    }

}

    @Override
    public List<CampaignViewModel> findAllCampaigns() {

        List<CampaignViewModel> models = new ArrayList<>();
        Iterable<Campaign> campaigns;
        campaigns =  this.campaignRepository.findAll();
        for (Campaign campaign : campaigns) {

            CampaignViewModel model = this.modelMapper.map(campaign, CampaignViewModel.class);
            if (campaign.getDescription().length()>17){
                model.setDescription(campaign.getDescription().substring(0,14)+"...");
            }
            setEventAndCampaignInView(campaign,model);
            models.add(model);
        }

        return models;
    }

    @Override
    public CampaignViewModel findById(long id) {
        Campaign campaign = this.campaignRepository.findOne(id);
        if (campaign==null){
            throw  new CampaignNotFoundException();
        }
        CampaignViewModel model = this.modelMapper.map(campaign, CampaignViewModel.class);

        setEventAndCampaignInView(campaign,model);
        return model;
    }

    public List<CampaignViewModel> getAllCampaign(){
        Iterable<Campaign> campaigns = this.campaignRepository.findAll();

        List<CampaignViewModel> campaignViews = new ArrayList<>();
        for (Campaign campaign : campaigns) {
            CampaignViewModel campaignViewModel = new CampaignViewModel();

            this.modelMapper.map(campaign, campaignViewModel);


            campaignViews.add(campaignViewModel);
        }

        return campaignViews;
    }



    @Override
    public void deleteById(long Id) {

    }
}
