package com.teammatch.tournament.domain.repository;

import com.teammatch.tournament.domain.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
