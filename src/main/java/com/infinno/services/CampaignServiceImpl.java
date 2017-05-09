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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CampaignServiceImpl implements CampaignService {

    private final ModelMapper modelMapper;

    private final CampaignRepository campaignRepository;


    @Autowired
    public CampaignServiceImpl(ModelMapper modelMapper, CampaignRepository campaignRepository) {
        this.modelMapper = modelMapper;
        this.campaignRepository = campaignRepository;

    }

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

    switch (campaign.getEventType()){
        case "newUser":
            model.setEventType("Доведен потребител");
            break;
        case "income":
            model.setEventType("Парични постъпления");
            break;

    }

}
//
//    @Override
//    public List<CampaignViewModel> findAllCampaigns() {
//
//        List<CampaignViewModel> models = new ArrayList<>();
//        Iterable<Campaign> campaigns;
//        campaigns =  this.campaignRepository.findAll();
//        for (Campaign campaign : campaigns) {
//
//            CampaignViewModel model = this.modelMapper.map(campaign, CampaignViewModel.class);
//            if (campaign.getDescription().length()>17){
//                model.setDescription(campaign.getDescription().substring(0,14)+"...");
//            }
//            setEventAndCampaignInView(campaign,model);
//            models.add(model);
//        }
//
//        return models;
//    }

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

    @Override
    public Page<CampaignViewModel> findAll(Pageable pageable) {
        Page<Campaign> campaigns = this.campaignRepository.findAll(pageable);
        List<CampaignViewModel> campaignViewModelList = new ArrayList<>();
        for (Campaign campaign : campaigns) {
            CampaignViewModel campaignViewModel = this.modelMapper.map(campaign,CampaignViewModel.class);
            if (campaign.getDescription().length()>17){
                campaignViewModel.setDescription(campaign.getDescription().substring(0,14)+"...");
            }
            setEventAndCampaignInView(campaign,campaignViewModel);
            campaignViewModelList.add(campaignViewModel);
        }
        Page<CampaignViewModel> models = new PageImpl<CampaignViewModel>(campaignViewModelList,pageable,campaigns.getTotalElements());


        return models;
    }

//    public List<CampaignViewModel> getAllCampaign(){
//        Iterable<Campaign> campaigns = this.campaignRepository.findAll();
//
//        List<CampaignViewModel> campaignViews = new ArrayList<>();
//        for (Campaign campaign : campaigns) {
//            CampaignViewModel campaignViewModel = new CampaignViewModel();
//
//            this.modelMapper.map(campaign, campaignViewModel);
//
//
//            campaignViews.add(campaignViewModel);
//        }
//
//        return campaignViews;
//    }



    @Override
    public void deleteById(long Id) {

    }
}
