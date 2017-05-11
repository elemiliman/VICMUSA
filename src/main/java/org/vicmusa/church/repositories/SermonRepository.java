package org.vicmusa.church.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.vicmusa.church.entities.Sermon;
import org.vicmusa.church.entities.Sermon.SermonState;
import org.vicmusa.church.entities.User;

public interface SermonRepository extends JpaRepository<Sermon, Long> {

	Page<Sermon> findByUserAndPublishedOrderBySermonIdDesc(User user, SermonState state, Pageable pageable);

	@Query("SELECT s FROM Sermon s WHERE s.sermonPublished = 'PUBLISHED' ORDER BY studyDate DESC")
	Page<Sermon> findBySermonPublishedOrderByStudyDateDesc(Pageable pageable);
}
