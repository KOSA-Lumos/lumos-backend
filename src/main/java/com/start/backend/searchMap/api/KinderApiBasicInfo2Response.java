package com.start.backend.searchMap.api;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KinderApiBasicInfo2Response {

    private String status;
    private Set<KinderApiBasicInfo2> kinderInfo;
    
}
