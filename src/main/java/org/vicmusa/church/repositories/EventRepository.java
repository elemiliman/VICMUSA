package org.vicmusa.church.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.vicmusa.church.entities.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

	Page<Event> findAllByOrderByEventStartDateDesc(Pageable pageable);

	List<Event> findLast6ByOrderByEventStartDateDesc();

}
