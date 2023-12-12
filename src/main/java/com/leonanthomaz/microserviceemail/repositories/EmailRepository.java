package com.leonanthomaz.microserviceemail.repositories;

import com.leonanthomaz.microserviceemail.models.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Long> {
}
