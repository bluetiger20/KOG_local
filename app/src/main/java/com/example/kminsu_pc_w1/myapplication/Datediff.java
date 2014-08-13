package com.example.kminsu_pc_w1.myapplication;

import android.content.Context;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by KMINSU-PC-W1 on 2014-08-14.
 */
public class Datediff {

        public static long main(Context context) {

            DBContactHelper helper = new DBContactHelper(context);
            Contact contact=helper.getContact(1);
            int hour=contact.gethour();
            int min=contact.getminute();
            /** The date at the end of the last century */

            Date d1 = new GregorianCalendar(Calendar.YEAR,Calendar.MONTH,Calendar.DAY_OF_MONTH, hour, min).getTime();

            /** Today's date */
            Date today = new Date();

            // Get msec from each, and subtract.
            long diff = today.getTime() - d1.getTime();
            return  (diff / (1000 * 60 * 60 ));
        }
}


