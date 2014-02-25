package com.samiamharris.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by samharris on 2/24/14.
 */


public class CrimeListActivity extends SingleFragmentActivity {


    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
