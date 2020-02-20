package com.example.employeedirectory.Utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.Html;
import android.util.AttributeSet;
import android.widget.TextView;

import com.example.employeedirectory.R;
import com.example.employeedirectory.Utils.TypefaceCache;



public class NativeTextView extends TextView {
    public int typefaceCode;

    public NativeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (attrs != null) {
            // Get Custom Attribute Name and value
            TypedArray styledAttrs = context.obtainStyledAttributes(attrs,
                    R.styleable.NativeText);
            typefaceCode = styledAttrs.getInt(R.styleable.NativeText_fontType, -1);
            Typeface typeface = TypefaceCache.get(context.getAssets(), typefaceCode);
            setTypeface(typeface);

            styledAttrs.recycle();
            if (isInEditMode()) {
                return;
            }
        }
    }

    public NativeTextView(Context context) {
        super(context);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        if (text != null) {

          /*  if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                text=   Html.fromHtml(text.toString(),Html.FROM_HTML_MODE_LEGACY);
            } else {*/
            text = Html.fromHtml(text.toString());
            /*}*/


        }
        super.setText(text, type);
    }

}
