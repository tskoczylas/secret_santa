package com.tomsproject.secret_santa.repo;


import com.tomsproject.secret_santa.dto.SantaUsersPairDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SantaUserPairRepo extends JpaRepository<SantaUsersPairDto,Long> {
}
