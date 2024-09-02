package com.citadini.ourcity.service;

import com.citadini.ourcity.domain.OccurrenceSupportEntity;
import com.citadini.ourcity.domain.OccurrenceSupportPK;
import com.citadini.ourcity.domain.OccurrenceEntity;
import com.citadini.ourcity.domain.UserEntity;
import com.citadini.ourcity.repositories.OccurrenceSupportRepository;
import com.citadini.ourcity.security.UserSS;
import com.citadini.ourcity.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OccurrenceSupportService {

	@Autowired
	private OccurrenceSupportRepository occurrenceSupportRepository;
	@Autowired
	private OccurrenceService occurrenceService;
	@Autowired
	private UserService userService;
	
	public List<OccurrenceSupportEntity> findByOccurrenceId(Long occurrenceId) {
		OccurrenceEntity occurrence = occurrenceService.find(occurrenceId);
		return occurrenceSupportRepository.findByIdOccurrence(occurrence);
	}

	public OccurrenceSupportEntity fromOccurrenceId(Long occurrenceId) {
		UserSS userSS = UserAuthenticateService.authenticated();
		UserEntity user = userService.find(userSS.getId());
		OccurrenceEntity occurrence = occurrenceService.find(occurrenceId);
        return new OccurrenceSupportEntity(user, occurrence, new Date());
	}

	public OccurrenceSupportEntity insert(OccurrenceSupportEntity obj) {
		return occurrenceSupportRepository.save(obj);
	}
	
	public OccurrenceSupportEntity find(OccurrenceSupportPK id) {
		Optional<OccurrenceSupportEntity> occurrenceSupport = occurrenceSupportRepository.findById(id);
		return occurrenceSupport.orElseThrow( () -> new ObjectNotFoundException(
				String.format("Object not found: OccurrenceId: %d, UserId: %d, Type: %s",
						id.getOccurrence().getId(), id.getUser().getId(), OccurrenceSupportEntity.class.getName())));
	}
	
	public void delete(Long occurrenceId) {
		UserSS userSS = UserAuthenticateService.authenticated();
		if (occurrenceId == null) {
			throw new ObjectNotFoundException("Occurrence Id cannot be blank");
		}
		OccurrenceSupportPK pk = new OccurrenceSupportPK();
		pk.setOccurrence(occurrenceService.find(occurrenceId));
		pk.setUser(userService.find(userSS.getId()));
		find(pk);
		occurrenceSupportRepository.deleteById(pk);
	}

}
