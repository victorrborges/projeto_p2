package hotel;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class BancoDeDados {
	public HotelController iniciaSistema() throws IOException, ClassNotFoundException{
		FileInputStream fis;
		try{
			fis = new FileInputStream("arquivos_sistema/hug.dat");
		}catch (Exception e) {
			new FileOutputStream("arquivos_sistema/hug.dat");
		}
		fis = new FileInputStream("arquivos_sistema/hug.dat");
		GZIPInputStream giz = new GZIPInputStream(fis);
		ObjectInputStream ois = new ObjectInputStream(giz);
		HotelController saida = (HotelController) ois.readObject();
		ois.close();
		return saida;
	}
	public void fechasistema(HotelController hotel) throws IOException{
		
		FileOutputStream fos = new FileOutputStream("arquivos_sistema/hug.dat");
		GZIPOutputStream goz = new GZIPOutputStream(fos);
		ObjectOutputStream oos = new ObjectOutputStream(goz);
		oos.writeObject(hotel);
		oos.close();
	}
}
