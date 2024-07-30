package com.example.serchrepo.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProduactTypeRiquest {
    private Long id;
    private String productTypeName;
    private String ProductTypeDescription;
    private LocalDateTime createdDate;
}
