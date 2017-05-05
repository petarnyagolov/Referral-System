package com.infinno.services;

import com.infinno.entities.campaigns.*;
import com.infinno.entities.events.IncomeUserEvent;
import com.infinno.entities.events.NewUserEvent;
import com.infinno.entities.events.UserEvent;
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
        UserEvent userEvent = null;
        String event = addCampaignBindingModel.getUserEvent();
        System.out.println(event);

        switch (event){
            case "newUser":
                userEvent = new NewUserEvent();
                break;
            case "income":
                userEvent = new IncomeUserEvent();
                break;

        }
        this.eventRepository.save(userEvent);
       switch (addCampaignBindingModel.getCampaignType()){
           case "topup":
               TopupCampaign topupCampaign = this.modelMapper.map(addCampaignBindingModel,TopupCampaign.class);
               topupCampaign.addEvent(userEvent);
               campaignRepository.save(topupCampaign);
               break;
           case "discountTopup":
               DiscountTopupCampaign discountTopupCampaign = this.modelMapper.map(addCampaignBindingModel,DiscountTopupCampaign.class);
               discountTopupCampaign.addEvent(userEvent);
               campaignRepository.save(discountTopupCampaign);
               break;
           case "percentTopup":
               PercentTopupCampaign percentTopupCampaign = this.modelMapper.map(addCampaignBindingModel,PercentTopupCampaign.class);
               percentTopupCampaign.addEvent(userEvent);
               campaignRepository.save(percentTopupCampaign);
               break;
           case "discountPercentTopup":
               DiscountPercentTopupCampaign discountPercentTopupCampaign = this.modelMapper.map(addCampaignBindingModel,DiscountPercentTopupCampaign.class);
               discountPercentTopupCampaign.addEvent(userEvent);
               campaignRepository.save(discountPercentTopupCampaign);
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
            models.add(model);
        }

        return models;
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
