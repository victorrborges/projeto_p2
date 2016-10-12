package hotelTest;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import hotel.HotelFacade;

public class FachadaTest {
	private HotelFacade hotel;
	@Before
	public void test(){
		this.hotel = new HotelFacade();
	}
	@Test
	public void testealgo() throws Exception{
		hotel.iniciaSistema();
		hotel.cadastraHospede("tal", "wesley@hotmail.com", "28/01/1997");
		hotel.realizaCheckin("wesley@hotmail.com", 5, "15B", "Presidencial");
		hotel.fechaSistema();
	}
	@Test
	public void te() throws Exception{
		hotel.iniciaSistema();
		assertEquals("tal",hotel.getInfoHospede("wesley@hotmail.com", "Nome"));
	}
}
