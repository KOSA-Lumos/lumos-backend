package com.start.backend.searchMap.api;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KakaoApiAddressResponse {

	private KakaoApiAddressMeta meta;
    private Set<KakaoApiAddressDocuments> documents;
    
}
