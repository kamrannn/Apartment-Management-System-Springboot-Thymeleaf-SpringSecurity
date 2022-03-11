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

@Controller
public class wohnungController {

    @Autowired

    private wohnungService wohnungService;

    @GetMapping("/")
    public String seeWohnung(Model model) {
        model.addAttribute("listWohnung", wohnungService.getAllwohnung());
        return "index";
    }

    @GetMapping("/addWohnung")
    public String showNewWohnungForm(Model model) {
        // create model attribute to bind form data
        Wohnung wohnung = new Wohnung();
        model.addAttribute("Wohnung", wohnung);
        return "addWohnung";
    }

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
