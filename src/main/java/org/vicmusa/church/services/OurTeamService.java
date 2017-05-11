package org.vicmusa.church.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.vicmusa.church.dto.OurTeamForm;
import org.vicmusa.church.entities.OurTeam;

public interface OurTeamService {

	public abstract void createTeam(OurTeamForm ourTeamForm, MultipartFile multipartFile);

	public abstract List<OurTeam> getTeamList();

	public abstract void updateTeamMember(long ourTeamId, OurTeamForm ourTeamForm, MultipartFile multipartFile);

	public abstract OurTeam findOneTeamMember(long ourTeamId);

}
