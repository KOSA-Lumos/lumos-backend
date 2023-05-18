package com.start.backend.searchMap.api;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KinderApiSafetyEduResponse {

    private String status;
    private Set<KinderApiSafetyEdu> kinderInfo;
    
}
