package SaveAndLoad;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import model.Game;

public class Persistance
{
	public static void save(Game game, File file) throws FileNotFoundException, IOException
	{
		FileOutputStream fo = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fo);
		oos.writeObject(game);
		fo.flush();
		fo.close();
		oos.flush();
		oos.close();
	}
	public static Game load(File file) throws FileNotFoundException, IOException, ClassNotFoundException
	{
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
		Game game = null;
		try {
		game = (Game)ois.readObject();
		} catch (EOFException e) {e.printStackTrace();}
		ois.close();
		return game;
	}
}
