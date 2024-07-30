package com.example.serchrepo.Response;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProduactTypeResponce {

    private Long id;
    private String productTypeName;
    private String ProductTypeDescription;
    private LocalDateTime createdDate;
}
