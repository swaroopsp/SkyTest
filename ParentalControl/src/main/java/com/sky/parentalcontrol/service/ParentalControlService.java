package com.sky.parentalcontrol.service;

import com.sky.parentalcontrol.constant.ParentalControlLevel;
import com.sky.parentalcontrol.exception.TechnicalFailureException;
import com.sky.parentalcontrol.exception.TitleNotFoundException;

/**
 * The Interface ParentalControlService.
 */
public interface ParentalControlService
{
	public boolean canSeeMovie(ParentalControlLevel parentalControlLevel, String movieID) throws TitleNotFoundException, TechnicalFailureException;
	public void setMovieService(MovieService movieService);
}
