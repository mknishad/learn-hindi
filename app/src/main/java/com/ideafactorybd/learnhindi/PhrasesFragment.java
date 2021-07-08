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
public class PhrasesFragment extends Fragment {

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

  public PhrasesFragment() {
    // Required empty public constructor
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.word_list, container, false);

    mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

    final ArrayList<Word> words = new ArrayList<>();
    words.add(new Word("আপনি কেমন আছেন?", "क्या हाल है?", R.raw.phr_apni_kemon_achen));
    words.add(new Word("আমি ভাল আছি", "मैं ठीक हूँ", R.raw.phr_ami_valo_achi));
    words.add(new Word("আপনার নাম কি?", "आपका नाम क्या है?", R.raw.phr_apnar_nam_ki));
    words.add(new Word("আমার নাম হাসান", "मेरा नाम हसन है", R.raw.phr_amar_nam_hasan));
    words.add(new Word("এখন কয়টা বাজে?", "क्या समय हुआ है?", R.raw.phr_ekhon_koyta_baje));
    words.add(new Word("এখন দশটা বাজে", "दस बज रहे हैं", R.raw.phr_ekhon_doshta_baje));
    words.add(new Word("আপনি কোথা থেকে এসেছেন?", "आप कहाँ से आए हैं", R.raw.phr_apni_kotha_theke_esechen));
    words.add(new Word("আমি ঢাকা থেকে এসেছি", "मैं ढाका से आया", R.raw.phr_ami_dhaka_theke_esechi));
    words.add(new Word("আপনি কোথায় যাচ্ছেন?", "आप कहाँ जा रहे हैं?", R.raw.phr_apni_kothay_jacchen));
    words.add(new Word("আমি নয়া দিল্লী যাচ্ছি", "मैं नई दिल्ली जा रहा हूँ", R.raw.phr_ami_noya_delhi_jacchi));
    words.add(new Word("আপনি কোথায় থাকেন?", "आप कहाँ रहते हैं?", R.raw.phr_apni_kothay_thaken));
    words.add(new Word("আমি ঢাকাতে থাকি", "मैं ढाका में रहते हैं", R.raw.phr_ami_dhakate_thaki));
    words.add(new Word("এখানে আসুন", "यहाँ आओ", R.raw.phr_ekhane_ashun));
    words.add(new Word("ওখানে যান", "वहा जाओ", R.raw.phr_okhane_jan));
    words.add(new Word("এই জামাটির দাম কত?", "इस शर्ट की कीमत कितनी है?", R.raw.phr_ei_shirttir_dam_koto));
    words.add(new Word("আমাকে বিরক্ত করবেন না", "मुझे परेशान मत करो", R.raw.phr_amake_birokto_korben_na));
    words.add(new Word("আমি ক্রিকেট পছন্দ করি", "मैं क्रिकेट की तरह", R.raw.phr_ami_cricket_pochondo_kori));
    words.add(new Word("আমি ফুটবল পছন্দ করি না", "मैं फुटबॉल पसंद नहीं है", R.raw.phr_ami_football_pochondo_kori_na));
    words.add(new Word("আপনি কি ইংরেজী বলতে পারেন?", "क्या आप अंग्रेजी बोलते हैं?", R.raw.phr_apni_ki_english_bolte_paren));
    words.add(new Word("হ্যা, আমি ইংরেজী বলতে পারি", "हां, मुझे अंग्रेजी बोलनी आती है", R.raw.phr_ha_ami_english_bolte_pari));
    words.add(new Word("আপনি কি করেন?", "आप क्या करते हैं?", R.raw.phr_apni_ki_koren));
    words.add(new Word("আমি চাকরি করি", "मैं काम करने", R.raw.phr_ami_chakri_kori));
    words.add(new Word("আপনার প্রিয় গায়ক কে?", "आपके पसंदीदा गायक कौन है", R.raw.phr_apnar_priyo_gayok_k));
    words.add(new Word("আমার প্রিয় গায়ক মাইকেল জ্যাকসন", "मेरे पसंदीदा गायक माइकल जैक्सन है", R.raw.phr_amar_priyo_gayok_michael_jackson));
    words.add(new Word("আপনার প্রিয় খেলোয়াড় কে?", "आपका प्रिय खिलाड़ी कौन है", R.raw.phr_apnar_priyo_kheloyar_k));
    words.add(new Word("আমার প্রিয় খেলোয়াড় মাশরাফি মুর্তজা", "मेरे पसंदीदा खिलाड़ी मशरफे मुर्तजा है", R.raw.phr_amar_priyo_kheloyar_mashrafe_mortaza));
    words.add(new Word("আপনি অবসরে কি করেন?", "क्या आप अवकाश में क्या करते हैं?", R.raw.phr_apni_obosore_ki_koren));
    words.add(new Word("আমি অবসরে সিনেমা দেখি", "मैं अवकाश में सिनेमा देखना", R.raw.phr_ami_obosore_cinema_dekhi));
    words.add(new Word("আপনি কি খেতে পছন্দ করেন?", "आपको क्या खाना पसंद है?", R.raw.phr_apni_ki_khete_pochondo_koren));
    words.add(new Word("আমি পাস্তা খেতে পছন্দ করি", "मैं पास्ता खाना पसंद", R.raw.phr_ami_pasta_khete_pochondo_kori));
    words.add(new Word("আপনার সবচেয়ে ভাল বন্ধুর নাম কি?", "तुम्हारा सबसे अच्छा दोस्त का नाम क्या है?", R.raw.phr_apnar_sobcheye_valo_bondhur_nam_ki));
    words.add(new Word("আমার সবচেয়ে ভাল বন্ধুর নাম নিশাদ", "मेरे सबसे अच्छे दोस्त के नाम निषाद है", R.raw.phr_amar_sobcheye_valo_bondhur_nam_nishad));
    words.add(new Word("আমি কি আপনার ফোন নম্বরটি পেতে পারি?", "क्या मुझे आपका फोन नंबर मिल सकता है?", R.raw.phr_ami_ki_apnar_phone_number_pete_pari));
    words.add(new Word("আপনি কি অবিবাহিত?", "क्या आप अविवाहित हैं?", R.raw.phr_apni_ki_obibahito));
    words.add(new Word("হ্যা, আমি অবিবাহিত", "हाँ, मैं अविवाहित हूं", R.raw.phr_ha_ami_obibahito));
    words.add(new Word("আমি তোমাকে ভালবাসি", "मैं तुमसे प्यार करता हूँ", R.raw.phr_ami_tomake_valo_bashi));
    words.add(new Word("আমি তোমাকে ঘৃণা করি ", "मैं तुमसे नफ़रत करता हूं", R.raw.phr_ami_tomake_ghrina_kori));
    words.add(new Word("বিদায়", "अलविदा", R.raw.phr_biday));
    words.add(new Word("সমাপ্ত", "समाप्त", R.raw.phr_somapto));

    WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_phrases);
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
