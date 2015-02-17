package org.sibsystem.config;
/*
 * When reading from an option file, the options must be placed in the order
 * of the enum OptionFields, from top to bottom.
 */
public enum OptionFields {
	W_WIDTH("w_width"), 
	W_HEIGHT("w_height"),
	FRAME_CAP("frame_cap"); /* 0 or less converted to 1000 (1ms delay) */
	
	public final String str;
	
	OptionFields(String str) {
		this.str = str;
	}
}
