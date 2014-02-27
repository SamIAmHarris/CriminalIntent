package com.samiamharris.criminalintent;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.TimePicker;

/**
 * Created by samharris on 2/25/14.
 */
public class TimePickerFragment extends DialogFragment {

    public static final String EXTRA_HOUR =
            "com.samiamharris.criminalintent.hour";

    public static final String EXTRA_MINUTE =
            "com.samiamharris.criminalintent.minute";

    private int mHour;
    private int mMinute;

    public static TimePickerFragment newInstance(int hour, int minute) {
        Bundle args = new Bundle();
        args.putInt(EXTRA_HOUR, hour);
        args.putInt(EXTRA_MINUTE, minute);

        TimePickerFragment fragment = new TimePickerFragment();
        fragment.setArguments(args);

        return fragment;
    }

    public void sendResult(int resultCode) {
        if (getTargetFragment() == null)
            return;

        Intent i = new Intent();
        i.putExtra(EXTRA_HOUR, mHour);
        i.putExtra(EXTRA_MINUTE, mMinute);

        getTargetFragment()
                .onActivityResult(getTargetRequestCode(), resultCode, i);

    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        mHour = getArguments().getInt(EXTRA_HOUR);
        mMinute = getArguments().getInt(EXTRA_MINUTE);

        View v = getActivity().getLayoutInflater()
                .inflate(R.layout.dialog_time, null);

        TimePicker timePicker = (TimePicker) v.findViewById(R.id.dialog_time_timePicker);
        timePicker.setCurrentHour(mHour);
        timePicker.setCurrentMinute(mMinute);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                mHour = hourOfDay;
                mMinute = minute;
                getArguments().putInt(EXTRA_HOUR, mHour);
                getArguments().putInt(EXTRA_MINUTE, mMinute);
            }
        });


        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.time_picker_title)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sendResult(Activity.RESULT_OK);
                    }
                })
                .create();
    }
}
