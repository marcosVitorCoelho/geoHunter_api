package com.geohunter.geohunter_api.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.geohunter.geohunter_api.models.RegularUserModel;

@Repository
public interface RegularUserRepository extends JpaRepository<RegularUserModel, UUID>{
    boolean existsByCpf(String cpf);
    boolean existsByRg(String rg);
    boolean existsByPhoneNumber(String phoneNumber);  
}
