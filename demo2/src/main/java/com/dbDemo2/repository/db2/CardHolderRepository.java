package com.dbDemo2.repository.db2;

import com.dbDemo2.model.db2.HdMem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardHolderRepository extends JpaRepository<HdMem, Long> {
}
