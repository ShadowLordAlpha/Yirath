package yirath.settings;

/**
 * @author Josh "ShadowLordAlpha"
 *
 */
public final class SoundSettings {

	// Volume Settings
	private static float masterVolume;
	private static float musicVolume;
	private static float sfxVolume;
	private static float speechVolume;
	
	// Other Sound Related Settings
	private static boolean closedCaptions;
	
	// Load the initial config file
	static {
		 
	}

	/**
	 * Get the master volume
	 * @return The master volume
	 */
	public static float getMasterVolume() {
		return masterVolume;
	}

	/**
	 * 
	 * @param masterVolume
	 */
	public static void setMasterVolume(float masterVolume) {
		SoundSettings.masterVolume = masterVolume;
	}

	/**
	 * 
	 * @return
	 */
	public static float getMusicVolume() {
		return musicVolume;
	}
	
	/**
	 * 
	 * @return
	 */
	public static float getSoundMusicVolume() {
		return musicVolume * (masterVolume / 100.0f);
	}

	/**
	 * 
	 * @param musicVolume
	 */
	public static void setMusicVolume(float musicVolume) {
		SoundSettings.musicVolume = musicVolume;
	}
	
	/**
	 * 
	 * @return
	 */
	public static float getSfxVolume() {
		return sfxVolume;
	}
	
	/**
	 * 
	 * @return
	 */
	public static float getSoundSfxVolume() {
		return sfxVolume * (masterVolume / 100.0f);
	}

	/**
	 * 
	 * @param sfxVolume
	 */
	public static void setSfxVolume(float sfxVolume) {
		SoundSettings.sfxVolume = sfxVolume;
	}

	/**
	 * 
	 * @return
	 */
	public static float getSpeechVolume() {
		return speechVolume;
	}
	
	/**
	 * 
	 * @return
	 */
	public static float getSoundSpeechVolume() {
		return speechVolume * (masterVolume / 100.0f);
	}

	/**
	 * 
	 * @param speechVolume
	 */
	public static void setSpeechVolume(float speechVolume) {
		SoundSettings.speechVolume = speechVolume;
	}

	/**
	 * Get if Closed Captions are Enabled
	 * @return Are Closed Captions Enabled
	 */
	public static boolean isClosedCaptions() {
		return closedCaptions;
	}

	/**
	 * 
	 * @param closedCaptions
	 */
	public static void setClosedCaptions(boolean closedCaptions) {
		SoundSettings.closedCaptions = closedCaptions;
	}
}
