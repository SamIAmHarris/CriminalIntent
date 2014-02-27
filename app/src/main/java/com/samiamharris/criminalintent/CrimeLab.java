package com.samiamharris.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by samharris on 2/24/14.
 */
//Singleton pattern
public class CrimeLab {

    private static CrimeLab sCrimeLab;
    private Context mAppContext;
    private ArrayList<Crime> mCrimes;

    //Context give singleton lots of access
    private CrimeLab(Context appContext) {
        mAppContext = appContext;
        mCrimes = new ArrayList<Crime>();
    }

    public static CrimeLab get(Context c) {
        //create new one and give it application context (global to app)
        if(sCrimeLab == null) {
            sCrimeLab = new CrimeLab(c.getApplicationContext());
        }
        //if it alreadye exists then return it
        return sCrimeLab;
    }

    public ArrayList<Crime> getCrimes() {
        return mCrimes;
    }

    public Crime getCrime(UUID id) {
        for(Crime c : mCrimes) {
            if(c.getId().equals(id))
                return c;
        }
        return null;
    }

    public void addCrime(Crime c) {
        mCrimes.add(c);
    }


}
