package com.infinno.controllers;

import com.infinno.models.bindingModels.AddCampaignBindingModel;
import com.infinno.models.viewModels.CampaignViewModel;
import com.infinno.services.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping("/")
    public String index(Model model) {
        List<CampaignViewModel> viewModels = this.campaignService.getAllHomePage();

        model.addAttribute("view", "home/index");
//        model.addAttribute("title", "Home");
        model.addAttribute("games", viewModels);

        return "base-layout";
    }


}
