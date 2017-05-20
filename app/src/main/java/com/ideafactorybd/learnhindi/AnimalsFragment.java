package com.ideafactorybd.learnhindi;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AnimalsFragment extends Fragment {

    private MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;

    private AudioManager.OnAudioFocusChangeListener mAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS ||
                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(0);
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        mMediaPlayer.start();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        releaseMediaPlayer();
                    }
                }
            };

    MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    public AnimalsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("পিঁপড়া", "चींटी", R.raw.ani_pipra));
        words.add(new Word("বাদুড়", "बल्ला", R.raw.ani_badur));
        words.add(new Word("ভালুক", "भालू", R.raw.ani_valuk));
        words.add(new Word("মৌমাছি", "मधुमक्खी", R.raw.ani_moumachi));
        words.add(new Word("মহিষ", "भेंस", R.raw.ani_mohish));
        words.add(new Word("প্রজাপতি", "तितली", R.raw.ani_projapoti));
        words.add(new Word("উট", "ऊंट", R.raw.ani_ut));
        words.add(new Word("বিড়াল", "बिल्ली", R.raw.ani_biral));
        words.add(new Word("মোরগ", "मुर्गा", R.raw.ani_morog));
        words.add(new Word("তেলাপোকা", "तिलचट्टा", R.raw.ani_telapoka));
        words.add(new Word("কাঁকড়া", "केकड़ा", R.raw.ani_kakra));
        words.add(new Word("কুমির", "मगरमच्छ", R.raw.ani_kumir));
        words.add(new Word("কাঁক", "कौआ", R.raw.ani_kak));
        words.add(new Word("হরিণ", "हिरन", R.raw.ani_horin));
        words.add(new Word("কুকুর", "कुत्ता", R.raw.ani_kukur));
        words.add(new Word("গাধা", "गधा", R.raw.ani_gadha));
        words.add(new Word("হাঁস", "बत्तख", R.raw.ani_hash));
        words.add(new Word("হাতি", "हाथी", R.raw.ani_hati));
        words.add(new Word("শেয়াল", "लोमड़ी", R.raw.ani_sheyal));
        words.add(new Word("ব্যাং", "मेढक", R.raw.ani_bagh));
        words.add(new Word("জিরাফ", "जिराफ़", R.raw.ani_giraffe));
        words.add(new Word("ছাগল", "बकरा", R.raw.ani_sagol));
        words.add(new Word("মুরগী", "मुर्गी", R.raw.ani_murgi));
        words.add(new Word("সিংহ", "शेर", R.raw.ani_shingho));
        words.add(new Word("বানর", "बंदर", R.raw.ani_banor));
        words.add(new Word("মশা", "मच्छर", R.raw.ani_mosha));
        words.add(new Word("পেঁচা", "उल्लू", R.raw.ani_pecha));
        words.add(new Word("ময়ূর", "मोर", R.raw.ani_moyur));
        words.add(new Word("কবুতর", "कबूतर", R.raw.ani_kobutor));
        words.add(new Word("অজগর", "अजगर", R.raw.ani_ojogor));
        words.add(new Word("খরগোশ", "खरगोश", R.raw.ani_khorgosh));
        words.add(new Word("ইঁদুর", "चूहा", R.raw.ani_idur));
        words.add(new Word("ভেড়া", "भेड़", R.raw.ani_vera));
        words.add(new Word("সাপ", "साँप", R.raw.ani_sap));
        words.add(new Word("বাঘ", "बाघ", R.raw.ani_bagh));
        words.add(new Word("কচ্ছপ", "कछुए", R.raw.ani_kocchop));
        words.add(new Word("নেকড়ে", "भेड़िया", R.raw.ani_nekre));
        words.add(new Word("জেব্রা", "ज़ेबरा", R.raw.ani_zebra));

        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_animals);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = words.get(position);
                releaseMediaPlayer();

                // Request audio focus for playback
                int result = mAudioManager.requestAudioFocus(mAudioFocusChangeListener,
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // Request permanent focus.
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // Now we have audio focus
                    mMediaPlayer = MediaPlayer.create(getActivity(), word.getAudioResourceId());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });

        // Enable scrolling in the list view
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            listView.setNestedScrollingEnabled(true);
        }

        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        // When the activity is stopped, release the media player resources because we won't
        // be playing any more sounds.
        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;

            // Abandon audio focus when playback complete
            mAudioManager.abandonAudioFocus(mAudioFocusChangeListener);
        }
    }

}
