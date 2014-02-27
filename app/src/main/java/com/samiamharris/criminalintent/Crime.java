package com.samiamharris.criminalintent;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * Created by samharris on 2/23/14.
 */
public class Crime {

    private UUID mId;
    private String mTitle;
    private Date mDate;
    private int mHour;
    private int mMinute;
    private boolean mSolved;
    public Crime() {
        //Generate unique identifier
        mId = UUID.randomUUID();
        mDate = new Date();
        Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR);
        mMinute = c.get(Calendar.MINUTE);
    }

    @Override
    public String toString() {
        return mTitle;
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date mDate) {
        this.mDate = mDate;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean mSolved) {
        this.mSolved = mSolved;
    }

    public int getHour() {return mHour;}

    public void setHour(int mHour) {this.mHour = mHour;}

    public int getMinute() {return mMinute;}

    public void setMinute(int mMinute) {this.mMinute = mMinute;}

}

