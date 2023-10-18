package com.geohunter.geohunter_api.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.geohunter.geohunter_api.models.RegularUserModel;
import com.geohunter.geohunter_api.repositories.RegularUserRepository;

import jakarta.transaction.Transactional;

@Service
public class RegularUserService {
    final RegularUserRepository regularUserRepository;

    public RegularUserService(RegularUserRepository regularUserRepository){
        this.regularUserRepository = regularUserRepository;
    }

    public RegularUserModel save(RegularUserModel regularUserModel){
        return regularUserRepository.save(regularUserModel);
    }

    public boolean existsByCpf(String cpf){
        return regularUserRepository.existsByCpf(cpf);
    }

    public boolean existsByPhoneNumber(String phoneNumber){
        return regularUserRepository.existsByPhoneNumber(phoneNumber);
    }

    public List<RegularUserModel> findAll(){
        return regularUserRepository.findAll();
    }

    public Optional<RegularUserModel> findById(UUID id){
        return regularUserRepository.findById(id);
    }

    @Transactional
    public void delete(RegularUserModel regularUserModel){
        regularUserRepository.delete(regularUserModel);
    }
}
