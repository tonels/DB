package com.dbDemo2.repository.db1;

import com.dbDemo2.model.db1.Office;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Office, Long> {
}
