package com.samiamharris.criminalintent;

/**
 * Created by samharris on 2/24/14.
 */

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CrimeListFragment extends ListFragment {

    private ArrayList<Crime> mCrimes;
    private static final String TAG = "CrimeListFragment";
    private boolean mSubtitleVisible;
    private Button mEmptyButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
        mSubtitleVisible = false;
        getActivity().setTitle(R.string.crimes_title);
        mCrimes = CrimeLab.get(getActivity()).getCrimes();

        CrimeAdapter adapter = new CrimeAdapter(mCrimes);
        setListAdapter(adapter);
    }

    @TargetApi(11)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_crime_list, container, false);

        mEmptyButton = (Button) v.findViewById(R.id.empty_button);
        mEmptyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Crime c = new Crime();
                CrimeLab.get(getActivity()).addCrime(c);


                //Start CrimePager Activity
                Intent i = new Intent(getActivity(), CrimePagerActivity.class);
                i.putExtra(CrimeFragment.EXTRA_CRIME_ID, c.getId());
                startActivityForResult(i,0);
            }
        });


        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            if(mSubtitleVisible) {
                getActivity().getActionBar().setSubtitle(R.string.subtitle);
            }
        }

        

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((CrimeAdapter)getListAdapter()).notifyDataSetChanged();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Crime c = ((CrimeAdapter)getListAdapter()).getItem(position);

        //Start CrimePager Activity
        Intent i = new Intent(getActivity(), CrimePagerActivity.class);
        i.putExtra(CrimeFragment.EXTRA_CRIME_ID, c.getId());
        startActivity(i);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_crime_list, menu);
        MenuItem showSubtitle = menu.findItem(R.id.menu_item_show_subtitle);
        if(mSubtitleVisible && showSubtitle != null) {
            showSubtitle.setTitle(R.string.hide_subtitle);
        }
    }

    @TargetApi(11)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.menu_item_new_crime:
                Crime crime = new Crime();
                CrimeLab.get(getActivity()).addCrime(crime);
                Intent i = new Intent(getActivity(), CrimePagerActivity.class);
                i.putExtra(CrimeFragment.EXTRA_CRIME_ID, crime.getId());
                startActivityForResult(i, 0);
                return true;
            case R.id.menu_item_show_subtitle:
                if(getActivity().getActionBar().getSubtitle() == null) {
                    getActivity().getActionBar().setSubtitle(R.string.subtitle);
                    mSubtitleVisible = true;
                    item.setTitle(R.string.hide_subtitle);
                } else {
                    getActivity().getActionBar().setSubtitle(null);
                    item.setTitle(R.string.show_subtitle);
                    mSubtitleVisible = false;
                }
                return true;

         default:
             return super.onOptionsItemSelected(item);


        }

    }

    public class CrimeAdapter extends ArrayAdapter<Crime> {

        public CrimeAdapter(ArrayList<Crime> crimes) {
            super(getActivity(), 0, crimes);
        }



        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //check if a recycled view was passed in
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.list_item_crime, null);
            }
            //Configure the view for this crime
            Crime c = getItem(position);

            //setting the views
            TextView titleTextView =
                    (TextView)convertView.findViewById(R.id.crime_list_item_titleTextView);
            titleTextView.setText(c.getTitle());
            TextView dateTextView =
                    (TextView)convertView.findViewById(R.id.crime_list_item_dateTextView);
            dateTextView.setText(android.text
                    .format.DateFormat.format("EEEE, MMM dd yyyy", c.getDate()));
            CheckBox solvedCheckBox =
                    (CheckBox)convertView.findViewById(R.id.crime_list_item_solvedCheckBox);
            solvedCheckBox.setChecked(c.isSolved());


            return convertView;
        }
    }
}
