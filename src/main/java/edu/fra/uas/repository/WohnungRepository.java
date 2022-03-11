package edu.fra.uas.repository;

import edu.fra.uas.model.Wohnung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WohnungRepository extends JpaRepository<Wohnung, Long> {
    List<Wohnung> findAllByPriceContaining(String price);
}
