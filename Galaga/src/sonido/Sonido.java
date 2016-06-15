package sonido;

import java.io.BufferedInputStream;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sonido {
	
	
	
	public static void sonidoGeneral(String TipoSonido) throws Exception, IOException, UnsupportedAudioFileException{
		Clip sonido = AudioSystem.getClip();
		sonido.open(AudioSystem.getAudioInputStream(BufferedInputStream.class.getResourceAsStream(TipoSonido)));
		sonido.open();
		sonido.start();
		
			
	}	
	
	
	
	//diparo jugador
	public static void disparoJugador() throws Exception, IOException, UnsupportedAudioFileException{
		Clip sonido = AudioSystem.getClip();
		sonido.open(AudioSystem.getAudioInputStream(BufferedInputStream.class.getResourceAsStream("/sonido/sonidos/missile.wav")));
		sonido.open();
		sonido.start();
	}	
	
	//diparo enemigo
	public static void disparoEnemigo() throws Exception, IOException, UnsupportedAudioFileException{
		Clip sonido = AudioSystem.getClip();
		sonido.open(AudioSystem.getAudioInputStream(BufferedInputStream.class.getResourceAsStream("/sonido/sonidos/photon.wav")));
		sonido.open();
		sonido.start();
	}	
	public static void disparoEnemigo1() throws Exception, IOException, UnsupportedAudioFileException{
		Clip sonido = AudioSystem.getClip();
		sonido.open(AudioSystem.getAudioInputStream(BufferedInputStream.class.getResourceAsStream("/sonido/sonidos/laser_enemigo.wav")));
		sonido.open();
		sonido.start();
	}	
	//pausa
	public static void pausa() throws Exception, IOException, UnsupportedAudioFileException{
		Clip sonido = AudioSystem.getClip();
		sonido.open(AudioSystem.getAudioInputStream(BufferedInputStream.class.getResourceAsStream("/sonido/sonidos/crystalcastles.wav")));
		sonido.open();
		sonido.start();
	}

	public static void explosion() throws Exception, IOException, UnsupportedAudioFileException{
		Clip sonido = AudioSystem.getClip();
		sonido.open(AudioSystem.getAudioInputStream(BufferedInputStream.class.getResourceAsStream("/sonido/sonidos/explosion.wav")));
		sonido.open();
		sonido.start();
		
	}	
	
	public static void explosion1() throws Exception, IOException, UnsupportedAudioFileException{
		Clip sonido = AudioSystem.getClip();
		sonido.open(AudioSystem.getAudioInputStream(BufferedInputStream.class.getResourceAsStream("/sonido/sonidos/explosion2.wav")));
		sonido.open();
		sonido.start();
		
	}	
	
	public static void muerteObjeto() throws Exception, IOException, UnsupportedAudioFileException{
		Clip sonido = AudioSystem.getClip();
		sonido.open(AudioSystem.getAudioInputStream(BufferedInputStream.class.getResourceAsStream("/sonido/sonidos/enemy1death.wav")));
		sonido.open();
		sonido.start();
		
	}	
	public static void muerteObjeto1() throws Exception, IOException, UnsupportedAudioFileException{
		Clip sonido = AudioSystem.getClip();
		sonido.open(AudioSystem.getAudioInputStream(BufferedInputStream.class.getResourceAsStream("/sonido/sonidos/enemy2death.wav")));
		sonido.open();
		sonido.start();
		
	}	
	
	public static void masEnergia() throws Exception, IOException, UnsupportedAudioFileException{
		Clip sonido = AudioSystem.getClip();
		sonido.open(AudioSystem.getAudioInputStream(BufferedInputStream.class.getResourceAsStream("/sonido/sonidos/mas_energia.wav")));
		sonido.open();
		sonido.start();
		
	}
	
	public static void vidaExtra() throws Exception, IOException, UnsupportedAudioFileException{
		Clip sonido = AudioSystem.getClip();
		sonido.open(AudioSystem.getAudioInputStream(BufferedInputStream.class.getResourceAsStream("/sonido/sonidos/coin_credit.wav")));
		sonido.open();
		sonido.start();
		
	}
	
	
	
	
	
	
	
	

}
