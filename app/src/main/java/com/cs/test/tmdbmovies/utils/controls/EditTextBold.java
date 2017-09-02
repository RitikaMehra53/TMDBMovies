package com.cs.test.tmdbmovies.utils.controls;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

import com.cs.test.tmdbmovies.utils.fonts.ApplicationFonts;


/**
 * Created by Amit on 3/27/2017.
 */

public class EditTextBold extends EditText {
    public EditTextBold(Context context, AttributeSet attrs) {
        super(context, attrs);

        setTypeface(ApplicationFonts.getBoldFont(context));

    }
}
