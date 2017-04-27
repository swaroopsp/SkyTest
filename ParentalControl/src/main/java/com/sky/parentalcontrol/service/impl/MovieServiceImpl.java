package com.sky.parentalcontrol.service.impl;

import java.util.Arrays;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.sky.parentalcontrol.constant.MovieMetaData;
import com.sky.parentalcontrol.constant.ParentalControlLevel;
import com.sky.parentalcontrol.exception.TitleNotFoundException;
import com.sky.parentalcontrol.service.MovieService;

/**
 * The Class MovieServiceImpl.
 */
@Service
public class MovieServiceImpl implements MovieService {
	private static final Log LOG = LogFactory.getLog(ParentalControlServiceImpl.class);

	public ParentalControlLevel getParentalLevelCode(final String movieID) throws TitleNotFoundException {
		final Optional<MovieMetaData> optional = getMovie(movieID);
		if (!optional.isPresent()) {
			String message = "Movie with Title " + movieID + " not found";
			LOG.info(message);
			throw new TitleNotFoundException(message);
		} else {
			return optional.get().getParentalControlLevel();
		}

	}

	public Optional<MovieMetaData> getMovie(final String movieName) {
		return Arrays
				.stream(MovieMetaData.values())
				.filter(e -> e.getMovieID().equals(movieName))
				.findAny();
	}
}
