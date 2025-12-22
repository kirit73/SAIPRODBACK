package com.sai.inventory.util;

import java.io.*;
import java.util.Base64;

public class UtilBase64Image {

	public static String encoder(String imagePath) {
		File file = new File(imagePath);
		try (FileInputStream imageInFile = new FileInputStream(file)) {
			String base64Image = "";
			byte imageData[] = new byte[(int) file.length()];
			imageInFile.read(imageData);
			base64Image = Base64.getEncoder().encodeToString(imageData);
			return base64Image;
		} catch (FileNotFoundException e) {
			System.out.println("Image not found " + e);
		} catch (IOException ioe) {
			System.out.println(" Exception while reading the image " + ioe);
		}
		return null;
	}

	public static void decoder(String base64Image, String pathFile) {
		try (FileOutputStream imageOutFile = new FileOutputStream(pathFile)) {
			byte[] imageByteArray = Base64.getDecoder().decode(base64Image);
			imageOutFile.write(imageByteArray);
		} catch (FileNotFoundException e) {
			System.out.println("Image not found " + e);
		} catch (IOException ioe) {
			System.out.println(" Exception while reading the image " + ioe);
		}
	}
}
