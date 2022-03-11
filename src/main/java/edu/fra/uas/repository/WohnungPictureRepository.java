package edu.fra.uas.repository;

import edu.fra.uas.model.WohnungPicture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WohnungPictureRepository extends JpaRepository<WohnungPicture, Long> {
}
