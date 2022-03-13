package edu.fra.uas.controller;

import edu.fra.uas.model.Wohnung;
import edu.fra.uas.model.WohnungPicture;
import edu.fra.uas.service.wohnungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class wohnungController {

    @Autowired

    private wohnungService wohnungService;

    /**
     * This method is getting used to view all the apartments that we have in our database.
     * This method is also getting used to show the statistics too.
     */
    @GetMapping("/")
    public String seeWohnung(Model model) {
        int totalPrice = 0;
        List<Wohnung> wohnungList = wohnungService.getAllwohnung();
        if (wohnungList.size() > 0) {
            for (Wohnung wohnung : wohnungList
            ) {
                totalPrice = totalPrice + Integer.parseInt(wohnung.getPrice());
            }
        }
        model.addAttribute("totalApartments", wohnungList.size());
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("listWohnung", wohnungService.getAllwohnung());
        return "index";
    }

    /**
     * This the controller which is getting used to add an apartment in the database.
     * So, this method is returning a view to add a new apartment
     */
    @GetMapping("/addWohnung")
    public String showNewWohnungForm(Model model) {
        // create model attribute to bind form data
        Wohnung wohnung = new Wohnung();
        model.addAttribute("Wohnung", wohnung);
        return "addWohnung";
    }

    /**
     * This the controller which is getting used to add an apartment in the database.
     * So, this method is accepting the submission of the data from the form of adding an apartment.
     * and then saving it to the database with its pictures.
     */
    @PostMapping("/addWohnung")
    public String saveEmployee(@ModelAttribute("Wohnung") Wohnung wohnung, @RequestParam("files") MultipartFile[] multipartFile) throws IOException {
        // save employee to database
        for (int i = 0; i < multipartFile.length; i++) {
            WohnungPicture picture = new WohnungPicture();
            picture.setType(multipartFile[i].getContentType());
            picture.setPicture(multipartFile[i].getBytes());
            wohnung.getWonhungPictures().add(picture);
            wohnungService.saveWohnung(wohnung);
        }

        return "redirect:/";
    }

    @GetMapping("/update_Wohnung/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {

        Wohnung wohnung = wohnungService.getWohnungById(id);
        model.addAttribute("Wohnung", wohnung);
        return "update_Wohnung";
    }

    @GetMapping("/deleteWohnung/{id}")
    public String deleteWohnung(@PathVariable(value = "id") long id) {
        this.wohnungService.deleteWohnungById(id);
        return "redirect:/";
    }

    @GetMapping("/search")
    public String search(@RequestParam(value = "price") String price, Model model) {
        model.addAttribute("listWohnung", wohnungService.findAllByPriceContaining(price));
        return "index";
    }

}
