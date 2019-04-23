package vaeke.restcountries.v0;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import vaeke.restcountries.v0.domain.Country;
import vaeke.restcountries.v0.rest.CountryService;

import com.eclipsesource.restfuse.HttpJUnitRunner;

@RunWith(HttpJUnitRunner.class)
public class CountryServiceTest {
	
	@Test
	public void getAll() throws Exception {
		List<Country> countries = CountryService.getInstance().getAll();
		Assert.assertNotNull(countries);
		Assert.assertFalse(countries.isEmpty());
		Assert.assertTrue(countries.size() >= 250);
	}
	
	@Test
	public void getByAlpha2() throws Exception {
		Country country = CountryService.getInstance().getByAlpha("CO");
		Assert.assertNotNull(country);
		Assert.assertEquals("CO", country.getCca2());
	}
	
	@Test
	public void getByAlpha3() throws Exception {
		Country country = CountryService.getInstance().getByAlpha("COL");
		Assert.assertNotNull(country);
		Assert.assertEquals("COL", country.getCca3());
	}
	
	@Test
	public void getByCodeList() throws Exception {
		List<Country> countries = CountryService.getInstance().getByCodeList("CO;NOR;EE");
		Assert.assertNotNull(countries);
		Assert.assertFalse(countries.isEmpty());
		Assert.assertEquals(3, countries.size());
		
		for(Country country : countries) {
			Assert.assertTrue(country.getCca2().equals("CO") || country.getCca2().equals("NO") || country.getCca2().equals("EE"));
		}
	}
	
	@Test
	public void getByCurrency() throws Exception {
		List<Country> countries = CountryService.getInstance().getByCurrency("EUR");
		Assert.assertNotNull(countries);
		Assert.assertFalse(countries.isEmpty());
		for(Country country : countries) {
			Assert.assertTrue(country.getCurrency().contains("EUR"));
		}
	}
	
	@Test
	public void getByName() throws Exception {
		List<Country> countries = CountryService.getInstance().getByName("Norway");
		Assert.assertNotNull(countries);
		Assert.assertFalse(countries.isEmpty());
		Assert.assertEquals("Norway", countries.get(0).getName());
	}
	
	@Test
	public void getByNameAlt() throws Exception {
		List<Country> countries = CountryService.getInstance().getByName("Norge");
		Assert.assertNotNull(countries);
		Assert.assertFalse(countries.isEmpty());
		Assert.assertEquals("Norway", countries.get(0).getName());
	}
	
	@Test
	public void getByCallingCode() throws Exception {
		List<Country> countries = CountryService.getInstance().getByCallingcode("57");
		Assert.assertNotNull(countries);
		Assert.assertFalse(countries.isEmpty());
		Assert.assertEquals(1, countries.size());
		Assert.assertEquals("CO", countries.get(0).getCca2());
	}
	
	@Test
	public void getByCapital() throws Exception {
		List<Country> countries = CountryService.getInstance().getByCapital("Tallinn");
		Assert.assertNotNull(countries);
		Assert.assertFalse(countries.isEmpty());
		Assert.assertEquals(1, countries.size());
		Assert.assertEquals("EE", countries.get(0).getCca2());
	}
	
	@Test
	public void getByRegion() throws Exception {
		List<Country> countries = CountryService.getInstance().getByRegion("Europe");
		Assert.assertNotNull(countries);
		Assert.assertFalse(countries.isEmpty());
		for(Country country : countries) {
			Assert.assertEquals("Europe", country.getRegion());
		}
	}
	
	@Test
	public void getBySubregion() throws Exception {
		List<Country> countries = CountryService.getInstance().getBySubregion("Northern Europe");
		Assert.assertNotNull(countries);
		Assert.assertFalse(countries.isEmpty());
		for(Country country : countries) {
			Assert.assertEquals("Northern Europe", country.getSubregion());
		}
	}
	
	@Test
	public void getByLanguageCode() throws Exception {
		List<Country> countries = CountryService.getInstance().getByLanguage("en");
		Assert.assertNotNull(countries);
		Assert.assertFalse(countries.isEmpty());
		for(Country country : countries) {
			Assert.assertTrue(country.getLanguages().contains("en"));
		}
	}

}
