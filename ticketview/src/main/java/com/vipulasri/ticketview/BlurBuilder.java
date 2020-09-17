package com.vipulasri.ticketview;

import android.content.Context;
import android.graphics.Bitmap;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;

public class BlurBuilder {

    public static Bitmap blur(Context context, Bitmap image , float radius) {
        if(image ==null || image.isRecycled()) return image;

        if (Utils.isJellyBeanAndAbove()) {
            RenderScript rs = RenderScript.create(context);
            ScriptIntrinsicBlur blur = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
            Allocation input = Allocation.createFromBitmap(rs, image);
            Allocation output = Allocation.createTyped(rs, input.getType());
            blur.setRadius(radius);
            blur.setInput(input);
            blur.forEach(output);
            output.copyTo(image);
            input.destroy();
            output.destroy();
        }

        return image;
    }

}
