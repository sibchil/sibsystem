package org.sibsystem.config;

import java.io.*;

public class Options {
	public int windowWidth;
	public int windowHeight;
	public int frameCap;
	private static final int defaultWindowWidth = 640;
	private static final int defaultWindowHeight = 480;
	private static final int defaultFrameCap = 60;
	
	public Options(String fileName) {
		File optionsFile = new File(fileName);
		BufferedReader reader = null;
		String[] parts = null;
		
		try {
			reader = new BufferedReader(new FileReader(optionsFile));
			String text = null;
			/*
			 * Notice that the options must be written in the file (by the user) in
			 * the OptionFields enum order since we are iterating the enum values once.
			 */
			while ((text = reader.readLine()) != null) {
				parts = text.trim().toLowerCase().split("=");
				for (OptionFields p : OptionFields.values()) {
					if (p.str.equals(parts[0])) {
						switch (p) {
						case W_WIDTH:
							windowWidth = Integer.parseInt(parts[1]);
							break;
						case W_HEIGHT:
							windowHeight = Integer.parseInt(parts[1]);
							break;
						case FRAME_CAP:
							frameCap = Integer.parseInt(parts[1]);
							if (frameCap <= 0) frameCap = 1000;
							break;
							default:
								System.err.println("Unknown option field \"" + parts[0] + "\"");
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.err.println("Unable to open options file " + fileName);
			System.err.println("Generating default options file");
			this.writeDefaultOptionsToFile(fileName);
		} catch (IOException e) {
			System.err.println("Unable to perform read operation on file");
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				System.err.println("Unable to close reader object");
				e.printStackTrace();
			}
		}
	}
	public Options(int windowWidth, int windowHeight, int frameCap) {
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;
		this.frameCap = frameCap;
	}
	public void writeOptionsToFile(String fileName) {
		FileWriter writer = null;
		try {
			writer = new FileWriter(fileName, false);
			
			writer.write(OptionFields.W_WIDTH.str + "=" + windowWidth + "\n");
			writer.write(OptionFields.W_HEIGHT.str + "=" + windowHeight + "\n");
			writer.write(OptionFields.FRAME_CAP.str + "=" + frameCap + "\n");
			
		} catch (IOException e) {
			System.err.println("Unable to open file for write operation");
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				System.err.println("Unable to close writer obejct");
				e.printStackTrace();
			}
		}
	}
	public void writeDefaultOptionsToFile(String fileName) {
		this.windowWidth = Options.defaultWindowWidth;
		this.windowHeight = Options.defaultWindowHeight;
		this.frameCap = Options.defaultFrameCap;
		writeOptionsToFile(fileName);
	}
}
