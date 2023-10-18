package com.geohunter.geohunter_api.models;

import java.util.UUID;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_job_category")
@Embeddable
public class CategoryModel {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private UUID id;

    @NotBlank(message = "Category is required")
    private String category;
}
