package com.samiamharris.criminalintent;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * Created by samharris on 2/23/14.
 */
public class Crime {

    private static final String JSON_ID = "id";
    private static final String JSON_TITLE = "title";
    private static final String JSON_SOLVED = "solved";
    private static final String JSON_DATE = "date";
    private static final String JSON_TIME = "time";
    private static final String JSON_PHOTO = "photo";
    private static final String JSON_SUSPECT = "suspect";


    private UUID mId;
    private String mTitle;
    private Date mDate;
    private Photo mPhoto;
    private int mHour;
    private int mMinute;
    private boolean mSolved;
    private String mSuspect;

    public Crime() {
        //Generate unique identifier
        mId = UUID.randomUUID();
        mTitle = "Title";
        mDate = new Date();
        Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR);
        mMinute = c.get(Calendar.MINUTE);
    }

    public Crime(JSONObject json) throws JSONException {
        mId = UUID.fromString(json.getString(JSON_ID));
        mTitle = json.getString(JSON_TITLE);
        mSolved = json.getBoolean(JSON_SOLVED);
        mDate = new Date(json.getLong(JSON_DATE));
        if (json.has(JSON_PHOTO))
            mPhoto = new Photo(json.getJSONObject(JSON_PHOTO));
        if(json.has(JSON_SUSPECT))
            mSuspect = json.getString(JSON_SUSPECT);

    }

    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(JSON_ID, mId.toString());
        json.put(JSON_TITLE, mTitle);
        json.put(JSON_SOLVED, mSolved);
        json.put(JSON_DATE, mDate.getTime());
        if(mPhoto != null)
            json.put(JSON_PHOTO, mPhoto.toJSON());
            json.put(JSON_SUSPECT, mSuspect);


        return json;
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

    public Photo getPhoto() {return mPhoto;}

    public void setPhoto(Photo p) {mPhoto = p;}

    public String getSuspect() {return mSuspect;}

    public void setSuspect(String mSuspect) {this.mSuspect = mSuspect;}
}

