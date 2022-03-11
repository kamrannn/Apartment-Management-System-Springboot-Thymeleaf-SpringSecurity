package edu.fra.uas.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import edu.fra.uas.model.Wohnung;

@Service
@Component
public interface wohnungService {

	List<Wohnung> getAllwohnung();

	void saveWohnung(Wohnung wohnung);

	Wohnung getWohnungById(long id);

	void deleteWohnungById(long id);

	List<Wohnung> findAllByPriceContaining(String price);
}
