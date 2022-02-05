package com.tomsproject.secret_santa.repo;


import com.tomsproject.secret_santa.entity.SantaUsersPairEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SantaUserPairRepo extends JpaRepository<SantaUsersPairEntity,Long> {
}
