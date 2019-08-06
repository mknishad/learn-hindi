package com.ideafactorybd.learnhindi;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NumbersFragment extends Fragment {

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

    public NumbersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("শূন্য ", "शून्य", R.raw.number_zero));
        words.add(new Word("এক", "एक", R.raw.number_one));
        words.add(new Word("দুই", "दो", R.raw.number_two));
        words.add(new Word("তিন", "तीन", R.raw.number_three));
        words.add(new Word("চার", "चार", R.raw.number_four));
        words.add(new Word("পাঁচ", "पांच", R.raw.number_five));
        words.add(new Word("ছয়", "छै", R.raw.number_six));
        words.add(new Word("সাত", "सात", R.raw.number_seven));
        words.add(new Word("আট", "आठ", R.raw.number_eight));
        words.add(new Word("নয়", "नौ", R.raw.number_nine));
        words.add(new Word("দশ", "दस", R.raw.number_ten));
        words.add(new Word("এগার", "ग्यारह", R.raw.number_eleven));
        words.add(new Word("বারো", "बारह", R.raw.number_twelve));
        words.add(new Word("তের", "तेरह", R.raw.number_thirteen));
        words.add(new Word("চৌদ্দ ", "चौदह", R.raw.number_fourteen));
        words.add(new Word("পনের", "पंद्रह", R.raw.number_fifteen));
        words.add(new Word("ষোল", "सोलह", R.raw.number_sixteen));
        words.add(new Word("সতের", "सत्रह", R.raw.number_seventeen));
        words.add(new Word("আঠার", "अट्ठारह", R.raw.number_eighteen));
        words.add(new Word("ঊনিশ", "उन्नीस", R.raw.number_nineteen));
        words.add(new Word("বিশ", "बीस", R.raw.number_twenty));
        words.add(new Word("একুশ", "इक्कीस", R.raw.number_twenty_one));
        words.add(new Word("বাইশ", "बाईस", R.raw.number_twenty_two));
        words.add(new Word("তেইশ", "तेईस", R.raw.number_twenty_three));
        words.add(new Word("চব্বিশ", "चौबिस", R.raw.number_twenty_four));
        words.add(new Word("পঁচিশ", "पच्चीस", R.raw.number_twenty_five));
        words.add(new Word("ছাব্বিশ", "छब्बीस", R.raw.number_twenty_six));
        words.add(new Word("সাতাশ", "सत्ताईस", R.raw.number_twenty_seven));
        words.add(new Word("আঠাশ", "अट्ठाईस", R.raw.number_twenty_eight));
        words.add(new Word("ঊনত্রিশ", "उनतीस", R.raw.number_twenty_nine));
        words.add(new Word("ত্রিশ", "तीस", R.raw.number_thirty));
        words.add(new Word("একত্রিশ", "इकतीस", R.raw.number_thirty_one));
        words.add(new Word("বত্রিশ", "बत्तीस", R.raw.number_thirty_two));
        words.add(new Word("তেত্রিশ", "तैंतीस", R.raw.number_thirty_three));
        words.add(new Word("চৌত্রিশ", "चौंतीस", R.raw.number_thirty_four));
        words.add(new Word("পঁয়ত্রিশ", "पैंतीस", R.raw.number_thirty_five));
        words.add(new Word("ছত্রিশ", "छत्तीस", R.raw.number_thirty_six));
        words.add(new Word("সাঁইত্রিশ", "सैंतीस", R.raw.number_thirty_seven));
        words.add(new Word("আটত্রিশ", "अड़तीस", R.raw.number_thirty_eight));
        words.add(new Word("ঊনচল্লিশ", "उनतालीस", R.raw.number_thirty_nine));
        words.add(new Word("চল্লিশ", "चालीस", R.raw.number_forty));
        words.add(new Word("একচল্লিশ", "इकतालीस", R.raw.number_forty_one));
        words.add(new Word("বিয়াল্লিশ", "बयालीस", R.raw.number_forty_two));
        words.add(new Word("তেতাল্লিশ", "तैंतालीस", R.raw.number_forty_three));
        words.add(new Word("চুয়াল্লিশ", "चौंतालीस", R.raw.number_forty_four));
        words.add(new Word("পঁয়তাল্লিশ", "पैंतालीस", R.raw.number_forty_five));
        words.add(new Word("ছেচল্লিশ", "छयालीस", R.raw.number_forty_six));
        words.add(new Word("সাতচল্লিশ", "सैंतालीस", R.raw.number_forty_seven));
        words.add(new Word("আটচল্লিশ", "अड़तालीस", R.raw.number_forty_eight));
        words.add(new Word("ঊনপঞ্চাশ", "उनचास", R.raw.number_forty_nine));
        words.add(new Word("পঞ্চাশ", "पचास", R.raw.number_fifty));
        words.add(new Word("একান্ন", "इक्यावन", R.raw.number_fifty_one));
        words.add(new Word("বাহান্ন", "बावन", R.raw.number_fifty_two));
        words.add(new Word("তিপ্পান্ন", "तिरेपन", R.raw.number_fifty_three));
        words.add(new Word("চুয়ান্ন", "चौवन", R.raw.number_fifty_four));
        words.add(new Word("পঞ্চান্ন", "पचपन", R.raw.number_fifty_five));
        words.add(new Word("ছাপ্পান্ন", "छप्पन", R.raw.number_fifty_six));
        words.add(new Word("সাতান্ন", "सत्तावन", R.raw.number_fifty_seven));
        words.add(new Word("আটান্ন", "अट्ठावन", R.raw.number_fifty_eight));
        words.add(new Word("ঊনষাট", "उनसठ", R.raw.number_fifty_nine));
        words.add(new Word("ষাট", "साठ", R.raw.number_sixty));
        words.add(new Word("একষট্টি", "इकसठ", R.raw.number_sixty_one));
        words.add(new Word("বাষট্টি", "बासठ", R.raw.number_sixty_two));
        words.add(new Word("তেষট্টি", "तिरेसठ", R.raw.number_sixty_three));
        words.add(new Word("চৌষট্টি", "चौंसठ", R.raw.number_sixty_four));
        words.add(new Word("পঁয়ষট্টি", "पैंसठ", R.raw.number_sixty_five));
        words.add(new Word("ছেষট্টি", "छयासठ", R.raw.number_sixty_six));
        words.add(new Word("সাতষট্টি", "सरसठ", R.raw.number_sixty_seven));
        words.add(new Word("আটষট্টি", "अड़सठ", R.raw.number_sixty_eight));
        words.add(new Word("ঊনসত্তর", "उनहत्तर", R.raw.number_sixty_nine));
        words.add(new Word("সত্তর", "सत्तर", R.raw.number_seventy));
        words.add(new Word("একাত্তর", "इकहत्तर", R.raw.number_seventy_one));
        words.add(new Word("বাহাত্তর", "बहत्तर", R.raw.number_seventy_two));
        words.add(new Word("তিয়াত্তর", "तिहत्तर", R.raw.number_seventy_three));
        words.add(new Word("চুয়াত্তর", "चौहत्तर", R.raw.number_seventy_four));
        words.add(new Word("পঁচাত্তর", "पचहत्तर", R.raw.number_seventy_five));
        words.add(new Word("ছিয়াত্তর", "छिहत्तर", R.raw.number_seventy_six));
        words.add(new Word("সাতাত্তর", "सतहत्तर", R.raw.number_seventy_seven));
        words.add(new Word("আটাত্তর", "अठहत्तर", R.raw.number_seventy_eight));
        words.add(new Word("ঊন-আশি", "उन्यासी", R.raw.number_seventy_nine));
        words.add(new Word("আশি", "अस्सी", R.raw.number_eighty));
        words.add(new Word("একাশি", "इक्यासी", R.raw.number_eighty_one));
        words.add(new Word("বিরাশি", "बयासी", R.raw.number_eighty_two));
        words.add(new Word("তিরাশি", "तिरासी", R.raw.number_eighty_three));
        words.add(new Word("চুরাশি", "चौरासी", R.raw.number_eighty_four));
        words.add(new Word("পঁচাশি", "पचासी", R.raw.number_eighty_five));
        words.add(new Word("ছিয়াশি", "छियासी", R.raw.number_eighty_six));
        words.add(new Word("সাতাশি", "सत्तासी", R.raw.number_eighty_seven));
        words.add(new Word("আটাশি", "अठासी", R.raw.number_eighty_eight));
        words.add(new Word("ঊননব্বই", "नवासी", R.raw.number_eighty_nine));
        words.add(new Word("নব্বই", "नब्बे", R.raw.number_ninety));
        words.add(new Word("একানব্বই", "इक्यानवे", R.raw.number_ninety_one));
        words.add(new Word("বিরানব্বই", "बानवे", R.raw.number_ninety_two));
        words.add(new Word("তিরানব্বই", "तिरानवे", R.raw.number_ninety_three));
        words.add(new Word("চুরানব্বই", "चौरानवे", R.raw.number_ninety_four));
        words.add(new Word("পঁচানব্বই", "पचानवे", R.raw.number_ninety_five));
        words.add(new Word("ছিয়ানব্বই", "छियानवे", R.raw.number_ninety_six));
        words.add(new Word("সাতানব্বই", "सत्तानवे", R.raw.number_ninety_seven));
        words.add(new Word("আটানব্বই", "अट्ठानवे", R.raw.number_ninety_eight));
        words.add(new Word("নিরানব্বই", "निन्यानवे", R.raw.number_ninety_nine));
        words.add(new Word("একশ", "एक सौ", R.raw.number_one_hundred));
        words.add(new Word("এক হাজার", "एक हज़ार", R.raw.number_one_thousand));
        words.add(new Word("এক লক্ষ", "एक लाख", R.raw.number_one_lac));
        words.add(new Word("এক কোটি", "एक करोड़", R.raw.number_one_crore));

        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_numbers);
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
