package edu.fra.uas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.fra.uas.model.Wohnung;
import edu.fra.uas.repository.WohnungRepository;

@Component
public class wohnungServiceimpl implements wohnungService {

	@Autowired
	private WohnungRepository wohnungRepo;

	@Override
	public List<Wohnung> getAllwohnung() {

		return wohnungRepo.findAll();
	}

	@Override
	public void saveWohnung(Wohnung wohnung) {
		this.wohnungRepo.save(wohnung);
	}

	@Override
	public Wohnung getWohnungById(long id) {
		Optional < Wohnung > optional = wohnungRepo.findById(id);
	    Wohnung wohnung = null;
	    if (optional.isPresent()) {
	        wohnung = optional.get();
	    } else {
	        throw new RuntimeException(" Wohnung not found for id :: " + id);
	    }
	    return wohnung;
	}
	
	
	@Override
    public void deleteWohnungById(long id) {
        this.wohnungRepo.deleteById(id);
    }

}
