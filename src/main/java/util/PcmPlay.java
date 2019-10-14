package util;

import java.io.FileInputStream;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class PcmPlay {
	
	public void playPcm(String url) {

		try {
			System.out.println("读取的路径："+ url);
			FileInputStream fis = new FileInputStream(url);
			AudioFormat.Encoding encoding =  new AudioFormat.Encoding("PCM_SIGNED");
			AudioFormat format = new AudioFormat(encoding,16000, 16, 1, 2, 8000 ,false);//编码格式，采样率，每个样本的位数，声道，帧长（字节），帧数，是否按big-endian字节顺序存储
			SourceDataLine auline = null;
			DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
 
			try {
				auline = (SourceDataLine) AudioSystem.getLine(info);
				auline.open(format);
			} catch (LineUnavailableException e) {
				e.printStackTrace();
				return;
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
			auline.start();
			byte[] b = new byte[256];
			try {
				while(fis.read(b)>0) {
					auline.write(b, 0, b.length);
				}
				auline.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
