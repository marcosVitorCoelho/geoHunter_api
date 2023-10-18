package com.geohunter.geohunter_api.controllers;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geohunter.geohunter_api.dto.usersDto.RegularUserDto;
import com.geohunter.geohunter_api.models.RegularUserModel;
import com.geohunter.geohunter_api.services.RegularUserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/regularUser")
public class RegularUserController {
    final RegularUserService regularUserService;

    public RegularUserController(RegularUserService regularUserService){
        this.regularUserService = regularUserService;
    }

    @PostMapping
    public ResponseEntity<Object> saveRegularUser(@RequestBody @Valid RegularUserDto regularUserDto){
        try {
            if(regularUserService.existsByCpf(regularUserDto.getCpf())){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("CONFLICT: This user has alteready been registered");
            }

            if(regularUserService.existsByPhoneNumber(regularUserDto.getPhoneNumber())){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("CONFLICT: This phone has alteready been registered");
            }

            var regularUserModel = new RegularUserModel();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            Date birthday = formatter.parse(regularUserDto.getBirthday());
            regularUserModel.setRegistrationDate((LocalDateTime.now(ZoneId.of("UTC"))));
            regularUserModel.setBirthday(birthday);

            BeanUtils.copyProperties(regularUserDto, regularUserModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(regularUserService.save(regularUserModel));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something go wrong");
        }
    }

    @GetMapping
    public ResponseEntity<List<RegularUserModel>> getAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(regularUserService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateRegularUser(@PathVariable(value = "id")UUID id, @RequestBody @Valid RegularUserDto regularUserDto){
        try{
            Optional<RegularUserModel> regularUserModelOptional = regularUserService.findById(id);
            if(!regularUserModelOptional.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }

            var regularUserModel = regularUserModelOptional.get();

            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            Date birthday = formatter.parse(regularUserDto.getBirthday());

            regularUserModel.setFirstName(regularUserDto.getFirstName());
            regularUserModel.setLastName(regularUserDto.getLastName());
            regularUserModel.setRg(regularUserDto.getRg());
            regularUserModel.setCpf(regularUserDto.getCpf());
            regularUserModel.setEmail(regularUserDto.getEmail());
            regularUserModel.setPhoneNumber(regularUserDto.getPhoneNumber());
            regularUserModel.setAddress(regularUserDto.getAddress());
            regularUserModel.setBirthday(birthday);

            return ResponseEntity.status(HttpStatus.OK).body(regularUserService.save(regularUserModel));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("It was not possible to update this user");
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRegularUser(@PathVariable(value = "id") UUID id){
        try{
            Optional<RegularUserModel> regularUserModelOptional = regularUserService.findById(id);
            if(!regularUserModelOptional.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }

            regularUserService.delete(regularUserModelOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body("User deleted");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something go wrong");
        }
    }
}
