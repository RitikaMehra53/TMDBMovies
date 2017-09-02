package com.cs.test.tmdbmovies.utils.fonts;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by Amit on 3/27/2017.
 */

public class ApplicationFonts {

    public static Typeface getLightFont(Context context)
    {
        Typeface tf = Typeface.createFromAsset(context.getAssets(),
                "fonts/AvenirLTStd-Book_1.otf");
        return tf;
    }

    public static Typeface getBoldFont(Context context)
    {
        Typeface tf = Typeface.createFromAsset(context.getAssets(),
                "fonts/AvenirLTStd-Heavy_1.otf");
        return tf;
    }
}
