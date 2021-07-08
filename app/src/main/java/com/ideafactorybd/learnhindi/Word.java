package com.ideafactorybd.learnhindi;

/**
 * Created by Nishad on 12/21/2016.
 */

public class Word {

  /**
   * Default translation of the word
   */
  private String mDefaultTranslation;
  /**
   * Miwok translation of the word
   */
  private String mHindiTranslation;
  /**
   * Audio resource id of the word
   */
  private int mAudioResourceId;

  public Word(String defaultTranslation, String hindiTranslation, int audioResourceId) {
    this.mDefaultTranslation = defaultTranslation;
    this.mHindiTranslation = hindiTranslation;
    this.mAudioResourceId = audioResourceId;
  }

  public String getDefaultTranslation() {
    return mDefaultTranslation;
  }

  public String getHindiTranslation() {
    return mHindiTranslation;
  }

  public int getAudioResourceId() {
    return mAudioResourceId;
  }
}
