package edu.fra.uas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import edu.fra.uas.model.Wohnung;

@Repository
public interface WohnungRepository extends JpaRepository<Wohnung, Long> {

}
