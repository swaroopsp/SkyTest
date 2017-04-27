package com.sky.parentalcontrol.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.sky.parentalcontrol.constant.ParentalControlLevel;
import com.sky.parentalcontrol.exception.TechnicalFailureException;
import com.sky.parentalcontrol.exception.TitleNotFoundException;
import com.sky.parentalcontrol.service.MovieService;
import com.sky.parentalcontrol.service.ParentalControlService;

/**
 * The Class ParentalControlServiceImpl.
 */
@Service
public class ParentalControlServiceImpl implements ParentalControlService
{
	private static final Log LOG = LogFactory.getLog(ParentalControlServiceImpl.class);

	@Autowired
	private MovieService movieService;

	/**
	 * returns if a customer can see the movie or not
	 * @throws TechnicalFailureException 
	 * @throws TitleNotFoundException 
	 * @throws IllegalAccessException 
	 */
	public boolean canSeeMovie(final ParentalControlLevel parentalControlLevel, final String movieID) throws TitleNotFoundException, TechnicalFailureException
	{
		if (!StringUtils.isEmpty(movieID) && parentalControlLevel != null)
		{
			final ParentalControlLevel controlLevel = movieService.getParentalLevelCode(movieID);
			if (controlLevel == null)
			{
				String message = "Failed to connect to MovieService";
				LOG.error(message);
				throw new TechnicalFailureException(message);
			}
			if (parentalControlLevel.getRating() >= controlLevel.getRating())
			{
				return true;
			}
		}else{
			String message = "ParentalControlLevel and movieID can not be empty";
			LOG.error(message);
		}
		return false;
	}

	public void setMovieService(final MovieService movieService)
	{
		this.movieService = movieService;
	}
}