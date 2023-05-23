package com.start.backend.searchMap.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KakaoApiAddressMeta {

	private boolean is_end;
	private int pageable_count;
	private int total_count;
	
}
