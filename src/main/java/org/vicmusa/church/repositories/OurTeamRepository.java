package org.vicmusa.church.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vicmusa.church.entities.OurTeam;

public interface OurTeamRepository extends JpaRepository<OurTeam, Long>{

	List<OurTeam> findFirst10ByOrderByOurTeamId();

	
}
