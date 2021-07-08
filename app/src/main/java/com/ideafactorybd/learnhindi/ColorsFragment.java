package com.ideafactorybd.learnhindi;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ColorsFragment extends Fragment {

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

  public ColorsFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.word_list, container, false);

    mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

    final ArrayList<Word> words = new ArrayList<>();
    words.add(new Word("সাদা", "सफेद", R.raw.col_sada));
    words.add(new Word("কালো", "काली", R.raw.col_kalo));
    words.add(new Word("বেগুনি", "बैंगनी", R.raw.col_beguni));
    words.add(new Word("নীল", "नीला", R.raw.col_neel));
    words.add(new Word("আকাশি", "आसमानी", R.raw.col_akashi));
    words.add(new Word("সবুজ", "हरा", R.raw.col_sabuj));
    words.add(new Word("হলুদ", "पीला", R.raw.col_holud));
    words.add(new Word("লাল", "लाल", R.raw.col_lal));
    words.add(new Word("গোলাপী", "गुलाबी", R.raw.col_golapi));
    words.add(new Word("বাদামি", "भूरा", R.raw.col_badami));
    words.add(new Word("জলপাই", "जैतून", R.raw.col_jolpai));
    words.add(new Word("রুপালি", "चांदी", R.raw.col_rupali));
    words.add(new Word("সোনালি", "स्वर्ण", R.raw.col_sonali));

    WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_colors);
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
