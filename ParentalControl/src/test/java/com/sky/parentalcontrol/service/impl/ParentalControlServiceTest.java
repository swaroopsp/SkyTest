package com.sky.parentalcontrol.service.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sky.parentalcontrol.constant.ApplicationConfiguration;
import com.sky.parentalcontrol.constant.MovieMetaData;
import com.sky.parentalcontrol.constant.ParentalControlLevel;
import com.sky.parentalcontrol.exception.TechnicalFailureException;
import com.sky.parentalcontrol.exception.TitleNotFoundException;
import com.sky.parentalcontrol.service.MovieService;
import com.sky.parentalcontrol.service.ParentalControlService;

@RunWith(SpringJUnit4ClassRunner.class)
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(classes=ApplicationConfiguration.class)
public class ParentalControlServiceTest
{
	@InjectMocks
	private ParentalControlService parentalControlService;
	
	@Autowired
	private MovieService movieService;
	
	@Before
	public void setup(){
		parentalControlService = new ParentalControlServiceImpl();
		parentalControlService.setMovieService(movieService);
	}
	
	@After
	public void tearDown(){
		parentalControlService = null;
	}
	
	@Test
	public void testCanSeeMovieWithCustomerEligibleToSeeMovieTITANIC_And_ParentalControlLevel_EIGHTEEN() throws TitleNotFoundException, TechnicalFailureException
	{
		boolean result = parentalControlService.canSeeMovie(ParentalControlLevel.EIGHTEEN, MovieMetaData.TITANIC.getMovieID());
		assertTrue(result);
	}

	@Test
	public void testCanSeeMovieWithCustomerEligibleToSeeMovieUP_And_ParentalControlLevel_PG() throws TitleNotFoundException, TechnicalFailureException
	{
		boolean result = parentalControlService.canSeeMovie(ParentalControlLevel.PG, MovieMetaData.UP.getMovieID());
		assertTrue(result);
	}

	@Test
	public void testCanSeeMovieWithCustomerEligibleToSeeMovieEVEREST_And_ParentalControlLevel_FIFTEEN() throws TitleNotFoundException, TechnicalFailureException
	{
		boolean result = parentalControlService.canSeeMovie(ParentalControlLevel.FIFTEEN, MovieMetaData.EVEREST.getMovieID());
		assertTrue(result);
	}

	@Test
	public void testCanSeeMovieWithCustomerEligibleToSeeMovieUP_And_ParentalControlLevel_FIFTEEN() throws TitleNotFoundException, TechnicalFailureException
	{
		boolean result = parentalControlService.canSeeMovie(ParentalControlLevel.FIFTEEN, MovieMetaData.UP.getMovieID());
		assertTrue(result);
	}

	@Test
	public void testCanSeeMovieWithCustomerNotEligibleToSeeMovieTITANIC_And_ParentalControlLevel_FIFTEEN() throws TitleNotFoundException, TechnicalFailureException
	{
		boolean result = parentalControlService.canSeeMovie(ParentalControlLevel.FIFTEEN, MovieMetaData.TITANIC.getMovieID());
		assertFalse(result);
	}

	@Test
	public void testCanSeeMovieWithCustomerNotEligibleToSeeMovieBOYS_And_ParentalControlLevel_U() throws TitleNotFoundException, TechnicalFailureException
	{
		boolean result = parentalControlService.canSeeMovie(ParentalControlLevel.U, MovieMetaData.BOYS.getMovieID());
		assertFalse(result);
	}

	@Test
	public void testCanSeeMovieWithCustomerNotEligibleToSeeMovieEVEREST_And_ParentalControlLevel_PG() throws TitleNotFoundException, TechnicalFailureException
	{
		boolean result = parentalControlService.canSeeMovie(ParentalControlLevel.PG, MovieMetaData.EVEREST.getMovieID());
		assertFalse(result);
	}

	@Test
	public void testCanSeeMovieWithCustomerNotEligibleToSeeMovieTITANIC_And_ParentalControlLevel_U() throws TitleNotFoundException, TechnicalFailureException
	{
		boolean result = parentalControlService.canSeeMovie(ParentalControlLevel.U, MovieMetaData.TITANIC.getMovieID());
		assertFalse(result);
	}

	@Test
	public void testCanSeeMovieWithNullParentalControlLevel() throws TitleNotFoundException, TechnicalFailureException
	{
		boolean result = parentalControlService.canSeeMovie(null, MovieMetaData.TITANIC.getMovieID());
		assertFalse(result);
	}

	@Test
	public void testCanSeeMovieWithNullMovieName() throws TitleNotFoundException, TechnicalFailureException
	{
		boolean result = parentalControlService.canSeeMovie(ParentalControlLevel.FIFTEEN, null);
		assertFalse(result);
	}

	@Test
	public void testCanSeeMovieWithNullParentalControlLevelAndEmptyMovieName() throws TitleNotFoundException, TechnicalFailureException
	{
		boolean result = parentalControlService.canSeeMovie(null, "");
		assertFalse(result);
	}

	@Test(expected = TitleNotFoundException.class)
	public void testCanSeeMovieWithInvalidMovieName() throws TitleNotFoundException, TechnicalFailureException
	{
		parentalControlService.canSeeMovie(ParentalControlLevel.FIFTEEN, "XXX");
	}

	@Test(expected = TechnicalFailureException.class)
	public void testCanSeeMovieWithMovieServiceHasError() throws TitleNotFoundException, TechnicalFailureException
	{
		MovieService mockedMovieService = mock(MovieService.class);
		when(mockedMovieService.getMovie(any(String.class))).thenReturn(null);
		parentalControlService.setMovieService(mockedMovieService);
		parentalControlService.canSeeMovie(ParentalControlLevel.FIFTEEN, "XXX");
	}
}
