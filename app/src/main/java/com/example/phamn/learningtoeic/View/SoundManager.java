package com.example.phamn.learningtoeic.View;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import com.example.phamn.learningtoeic.R;

import java.util.HashMap;

public class SoundManager {
    private SoundPool mSoundPool;
    private HashMap<Integer, Integer> mSoundPoolMap;
    private AudioManager mAudioManager;
    private Context mContext;
    public SoundManager() {



    }

    public void initSounds(Context theContext) {
        mContext = theContext;
        mSoundPool = new SoundPool(12, AudioManager.STREAM_MUSIC, 0);
        mSoundPoolMap = new HashMap<Integer, Integer>();
        mAudioManager = (AudioManager) mContext
                .getSystemService(Context.AUDIO_SERVICE);
    }
    //them sound
    public void addSound(int Index, int SoundID) {
        mSoundPoolMap.put(Index, mSoundPool.load(mContext, SoundID, 1));
    }

    public void playSound(int index) {

        int streamVolume = mAudioManager
                .getStreamVolume(AudioManager.STREAM_MUSIC);
        mSoundPool.play(mSoundPoolMap.get(index), streamVolume, streamVolume,
                1, 0, 0f);
    }




}
