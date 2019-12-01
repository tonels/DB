package com.dbDemo2.repository.db3;

import com.dbDemo2.model.db3.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
