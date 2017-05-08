package com.infinno.controllers;

import com.infinno.exception.CampaignNotFoundException;
import com.infinno.models.bindingModels.AddCampaignBindingModel;
import com.infinno.models.viewModels.CampaignViewModel;
import com.infinno.services.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/campaigns")
public class CampaignController {

    private final CampaignService campaignService;

    @Autowired
    public CampaignController(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @GetMapping("/add")
    public String getAddCampaignHomePage(@ModelAttribute AddCampaignBindingModel addCampaignBindingModel) {
        return "add";
    }

    @PostMapping("/add")
    public String AddCampaign(@Valid @ModelAttribute AddCampaignBindingModel addCampaignBindingModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "add";
        }

        this.campaignService.save(addCampaignBindingModel);

        return "redirect:/";

    }

    @GetMapping("/all")
    public String index(Model model) {

        List<CampaignViewModel> views = this.campaignService.findAllCampaigns();

        model.addAttribute("view",views);
        return "all";
    }

    @GetMapping("/show/{id}")
    public String showCampaign(Model model,@PathVariable long id){
        CampaignViewModel campaignViewModel = this.campaignService.findById(id);
        model.addAttribute("view",campaignViewModel);
        return "index";
    }

    @ExceptionHandler(CampaignNotFoundException.class)
    public String campaignNotFound(){
        return "exceptions/campaign-not-found";
    }


}
