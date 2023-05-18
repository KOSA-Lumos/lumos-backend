package com.start.backend.searchMap.api;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KinderApiClassAreaResponse {

    private String status;
    private Set<KinderApiClassArea> kinderInfo;
    
}
