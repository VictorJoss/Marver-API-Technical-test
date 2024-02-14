package com.test.api.marvelapi.persistence.repository;

import com.test.api.marvelapi.persistence.entity.UserInteractionLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInteractionLogRepository extends JpaRepository<UserInteractionLog, Long>{


}
