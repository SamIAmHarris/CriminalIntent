package com.samiamharris.criminalintent;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by samharris on 2/24/14.
 */
//Singleton pattern
public class CrimeLab {

    private static final String TAG = "CrimeLab";
    private static final String FILENAME = "crimes.json";

    private ArrayList<Crime> mCrimes;
    private CriminalIntentJSONSerializer mSerializer;

    private Context mAppContext;
    private static CrimeLab sCrimeLab;

    //Context give singleton lots of access
    private CrimeLab(Context appContext) {
        mAppContext = appContext;
        mSerializer = new CriminalIntentJSONSerializer(mAppContext, FILENAME);

        try {
            mCrimes = mSerializer.loadCrimes();
        } catch (Exception e) {
            mCrimes = new ArrayList<Crime>();
            Log.e(TAG, "Error loading crimes: ", e);
            Toast.makeText(mAppContext, "Error loading crimes", Toast.LENGTH_SHORT).show();
        }
    }

    public static CrimeLab get(Context c) {
        //create new one and give it application context (global to app)
        if(sCrimeLab == null) {
            sCrimeLab = new CrimeLab(c.getApplicationContext());
        }
        //if it already exists then return it
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

    public void deleteCrime(Crime c) {
        mCrimes.remove(c);
    }

    public boolean saveCrimes() {
        try {
            mSerializer.saveCrimes(mCrimes);
            Log.d(TAG, "crimes saved to file");
            return true;
        } catch (Exception e) {
            Toast.makeText(mAppContext, "Error saving crimes", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "Error saving crimes: ", e);
            return false;
        }
    }


}
