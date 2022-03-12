package edu.fra.uas.controller;

import edu.fra.uas.model.ResponseFile;
import edu.fra.uas.model.Wohnung;
import edu.fra.uas.model.WohnungPicture;
import edu.fra.uas.repository.WohnungPictureRepository;
import edu.fra.uas.repository.WohnungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class WohnungPictureController {
    @Autowired
    WohnungRepository wohnungRepository;

    @Autowired
    WohnungPictureRepository wohnungPictureRepository;

    /**
     * This controller is getting used to download the picture
     * This is accepting the picture ID that we want to download.
     * Every picture have a unique id in the database.
     * So we are finding that picture of that id.
     */
    @RequestMapping("/apartment/picture/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable(name = "id") Long id) {
        WohnungPicture fileDB = wohnungPictureRepository.getById(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("image/jpeg"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "\"")
                .body(fileDB.getPicture());
    }


    /** This controller is showing all the pictures of a specific apartments
     * In this we are taking apartment id and finding that apartment and then getting
     * all the pictures from the database.
     * */
    @GetMapping("/apartment/pictures/{apartmentId}")
    public String getListFiles(@PathVariable(name = "apartmentId") Long id, Model model) {

        Optional<Wohnung> apartment = wohnungRepository.findById(id);
        model.addAttribute("apartmentname", apartment.get().getId());
        List<ResponseFile> responseFilesList = new ArrayList<>();
        if (apartment.isPresent() && apartment.get().getWonhungPictures().size() > 0) {
            for (WohnungPicture file : apartment.get().getWonhungPictures()
            ) {
                ResponseFile responseFile = new ResponseFile();
                responseFile.setId(file.getId());
                responseFile.setType(file.getType());
                responseFilesList.add(responseFile);
            }
        }
        model.addAttribute("pictures", responseFilesList);
        return "apartment-pictures";
    }


}
