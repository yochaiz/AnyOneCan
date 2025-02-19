//package com.anyonecan.tal.drawgame;
//
//import android.content.Context;
//import android.content.SharedPreferences;
//import android.media.MediaPlayer;
//import android.preference.PreferenceManager;
//import android.util.Log;
//import android.util.SparseArray;
//
//
//public class MusicManager {
//    private static final String TAG = "MusicManager";
//    public static final int MUSIC_PREVIOUS = -1;
//    public static final int MUSIC_MENU = 0;
//    public static final int MUSIC_GAME = 1;
//    public static final int MUSIC_END_GAME = 2;
//    private static final int PREF_DEFAULT_MUSIC_VOLUME_ITEM = 4;
//
//    private static SparseArray<MediaPlayer> players = new SparseArray<MediaPlayer>();
//    private static int currentMusic = -1;
//    private static int previousMusic = -1;
//
//    private static boolean shouldPlay = true;
//
//    /*public static float getMusicVolume(Context context) {
//        String[] volumes = context.getResources().getStringArray(
//                R.array.volume_values);
//        String volumeString = PreferenceManager.getDefaultSharedPreferences(context).getString(context.getString("key_pref_music_volume"),
//                volumes[PREF_DEFAULT_MUSIC_VOLUME_ITEM]);
//
//        SharedPreferences settings = context.getSharedPreferences(context.getString(R.string.settings_storage), 0);
//        shouldPlay = settings.getBoolean(context.getString(R.string.key_music_on), true);
//
//        return Float.valueOf(volumeString);
//    }*/
//
//    public static void start(Context context, int music) {
//        start(context, music, false);
//    }
//
//    @SuppressWarnings("unused")
//    public static void start(Context context, int music, boolean force) {
//        if (!shouldPlay) {
//            return;
//        }
//
//        if (!force && currentMusic > -1) {
//            // already playing some music and not forced to change
//            return;
//        }
//        if (music == MUSIC_PREVIOUS) {
//            Log.d(TAG, "Using previous music [" + previousMusic + "]");
//            music = previousMusic;
//        }
//        if (currentMusic == music) {
//            // already playing this music
//            return;
//        }
//        if (currentMusic != -1) {
//            previousMusic = currentMusic;
//            Log.d(TAG, "Previous music was [" + previousMusic + "]");
//            // playing some other music, pause it and change
//            pause();
//        }
//        currentMusic = music;
//        Log.d(TAG, "Current music is now [" + currentMusic + "]");
//        MediaPlayer mp = players.get(music);
//        if (mp != null) {
//            if (!mp.isPlaying()) {
//                mp.start();
//            }
//        } else {
//            if (music == MUSIC_MENU) {
//                mp = MediaPlayer.create(context, R.raw.life_of_riley);
//            } else if (music == MUSIC_GAME) {
//                mp = MediaPlayer.create(context, R.raw.daily_beetle);
//            } else if (music == MUSIC_END_GAME) {
//                mp = MediaPlayer.create(context, R.raw.life_of_riley);
//            } else {
//                return;
//            }
//            players.put(music, mp);
//                Log.e(TAG, "unsupported music number - " + music);
//            //float volume = getMusicVolume(context);
//            float volume = 1;
//            Log.d(TAG, "Setting music volume to " + volume);
//            mp.setVolume(volume, volume);
//            if (mp == null) {
//                Log.e(TAG, "player was not created successfully");
//            } else {
//                try {
//                    mp.setLooping(true);
//                    mp.start();
//                } catch (Exception e) {
//                    Log.e(TAG, e.getMessage(), e);
//                }
//            }
//        }
//    }
//
//    public static void pause() {
//        for (int i = 0; i < players.size(); i++) {
//            MediaPlayer p = players.get(i);
//            if (p.isPlaying()) {
//                p.pause();
//            }
//        }
//        // previousMusic should always be something valid
//        if (currentMusic != -1) {
//            previousMusic = currentMusic;
//            Log.d(TAG, "Previous music was [" + previousMusic + "]");
//        }
//        currentMusic = -1;
//        Log.d(TAG, "Current music is now [" + currentMusic + "]");
//    }
//
//    public static void updateVolumeFromPrefs(Context context) {
//        try {
//            //float volume = getMusicVolume(context);
//            float volume = 1;
//            Log.d(TAG, "Setting music volume to " + volume);
//            for (int i = 0; i < players.size(); i++) {
//                MediaPlayer p = players.get(i);
//                if (p.isPlaying()) {
//                    p.pause();
//                }
//            }
//        } catch (Exception e) {
//            Log.e(TAG, e.getMessage(), e);
//        }
//    }
//
//    public static void release() {
//        Log.d(TAG, "Releasing media players");
//
//        for (int i = 0; i < players.size(); i++) {
//            MediaPlayer mp = players.get(i);
//            try {
//                if (mp != null) {
//                    if (mp.isPlaying()) {
//                        mp.stop();
//                    }
//                    mp.release();
//                }
//            } catch (Exception e) {
//                Log.e(TAG, e.getMessage(), e);
//            }
//        }
//        if (currentMusic != -1) {
//            previousMusic = currentMusic;
//            Log.d(TAG, "Previous music was [" + previousMusic + "]");
//        }
//        currentMusic = -1;
//        Log.d(TAG, "Current music is now [" + currentMusic + "]");
//    }
//
//    public static void toggleMusic(Context context, boolean state, int music) {
//        shouldPlay = state;
//
//        if (shouldPlay) {
//            start(context, music);
//        } else {
//            pause();
//        }
//    }
//}
