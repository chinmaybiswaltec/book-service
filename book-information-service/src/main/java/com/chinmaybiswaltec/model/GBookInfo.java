package com.chinmaybiswaltec.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GBookInfo {
	private VolumeInfo volumeInfo;
	private String description;
}
