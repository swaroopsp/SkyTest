package com.sky.parentalcontrol.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum ParentalControlLevel hold the different Parental control levels of movies.
 *
 * @author spanigrahi1
 */
@Getter
@AllArgsConstructor
public enum ParentalControlLevel
{
	U("U", 1),
    PG("PG", 2),
    TWELVE("TWELVE", 3),
    FIFTEEN("FIFTEEN", 4),
    EIGHTEEN("EIGHTEEN" , 5);

    private final String parentalLevelCode;
    
    private final int rating;
}
