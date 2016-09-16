package hotel;
import easyaccept.EasyAccept;

public class HotelFachada {
	
	
	public static void main(String[] args) {
		args = new String[] { "hotel.HotelFachada", "acceptance_test/testes_us1.txt"};
		System.out.println();
		EasyAccept.main(args);
	}
}
