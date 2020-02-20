
package com.example.employeedirectory.Utils;

import android.content.res.AssetManager;
import android.graphics.Typeface;

import java.util.Hashtable;



public class TypefaceCache {
    private static final String _LIGHT = "fonts/light.ttf";
    private static final String _BOLD = "fonts/bold.ttf";
    private static final String _REGULAR = "fonts/regular.ttf";
    private static final Hashtable<String, Typeface> CACHE = new Hashtable<String, Typeface>();

    public static Typeface get(AssetManager manager, int typefaceCode) {
        synchronized (CACHE) {
            String typefaceName = getTypefaceName(typefaceCode);
            if (!CACHE.containsKey(typefaceName)) {
                Typeface t = Typeface.createFromAsset(manager, typefaceName);
                CACHE.put(typefaceName, t);
            }
            return CACHE.get(typefaceName);
        }
    }

    private static String getTypefaceName(int typefaceCode) {
        String typefaceTemp = "";
        switch (typefaceCode) {
            case 0:
                typefaceTemp = _LIGHT;
                break;
            case 1:
                typefaceTemp = _BOLD;
                break;
            case 2:
                typefaceTemp = _REGULAR;
                break;
            default:
                typefaceTemp = _REGULAR;
        }
        return typefaceTemp;
    }
}
