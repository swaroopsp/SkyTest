package com.sky.parentalcontrol.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MovieMetaData
{
	TITANIC("TITANIC", ParentalControlLevel.EIGHTEEN),
	EVEREST("EVEREST", ParentalControlLevel.FIFTEEN),
	BOYS("BOYS", ParentalControlLevel.PG),
	UP("UP", ParentalControlLevel.U);	

	private final String movieID;

	private final ParentalControlLevel parentalControlLevel;
}