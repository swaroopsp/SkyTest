package com.sky.parentalcontrol.service;

import java.util.Optional;

import com.sky.parentalcontrol.constant.MovieMetaData;
import com.sky.parentalcontrol.constant.ParentalControlLevel;
import com.sky.parentalcontrol.exception.TechnicalFailureException;
import com.sky.parentalcontrol.exception.TitleNotFoundException;

/**
 * The Interface MovieService.
 */
public interface MovieService {
	public ParentalControlLevel getParentalLevelCode(String movieID) throws TitleNotFoundException, TechnicalFailureException;;
	public Optional<MovieMetaData> getMovie(String movieName);
}