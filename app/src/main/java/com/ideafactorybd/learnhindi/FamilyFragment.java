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
public class FamilyFragment extends Fragment {

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

    public FamilyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("বাবা", "पिता", R.raw.rel_baba));
        words.add(new Word("মা", "मां", R.raw.rel_ma));
        words.add(new Word("ভাই", "भाई", R.raw.rel_vai));
        words.add(new Word("বোন", "बहन", R.raw.rel_bon));
        words.add(new Word("দাদা", "दादा", R.raw.rel_dada));
        words.add(new Word("দাদী", "दादी मा", R.raw.rel_dadi));
        words.add(new Word("নানা", "नाना", R.raw.rel_nana));
        words.add(new Word("নানী", "नानी", R.raw.rel_nani));
        words.add(new Word("ছেলে", "बेटा", R.raw.rel_chele));
        words.add(new Word("মেয়ে", "बेटी", R.raw.rel_meye));
        words.add(new Word("চাচা", "चाचा", R.raw.rel_chacha));
        words.add(new Word("চাচী", "चाची", R.raw.rel_chachi));
        words.add(new Word("মামা", "मामा", R.raw.rel_mama));
        words.add(new Word("মামী", "मामी", R.raw.rel_mami));
        words.add(new Word("স্বামী", "पति", R.raw.rel_shami));
        words.add(new Word("স্ত্রী ", "पत्नी", R.raw.rel_stri));
        words.add(new Word("প্রেমিক", "प्रेमी", R.raw.rel_premik));
        words.add(new Word("প্রেমিকা", "प्रेमीचा", R.raw.rel_premika));
        words.add(new Word("শ্যালক", "जेठ", R.raw.rel_shyalok));
        words.add(new Word("শ্যালিকা", "बहन", R.raw.rel_shyalika));
        words.add(new Word("ভাতিজা", "भतीजा", R.raw.rel_vatija));
        words.add(new Word("ভাতিজী", "भांजी", R.raw.rel_vatiji));

        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_family);
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
