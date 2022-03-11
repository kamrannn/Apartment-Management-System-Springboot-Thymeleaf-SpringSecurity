package edu.fra.uas.service;

import edu.fra.uas.model.Wohnung;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public interface wohnungService {

    List<Wohnung> getAllwohnung();

    void saveWohnung(Wohnung wohnung);

    Wohnung getWohnungById(long id);

    void deleteWohnungById(long id);

    List<Wohnung> findAllByPriceContaining(String price);
}
