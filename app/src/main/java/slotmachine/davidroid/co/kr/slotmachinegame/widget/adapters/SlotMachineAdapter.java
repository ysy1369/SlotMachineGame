package slotmachine.davidroid.co.kr.slotmachinegame.widget.adapters;

/**
 * Created by YSY on 2015-08-12.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Slot machine adapter
 */
public class SlotMachineAdapter extends AbstractWheelAdapter {
    // Image size
    final int IMAGE_WIDTH = 350;
    final int IMAGE_HEIGHT = 350;
//        LinearLayout.LayoutPaams slotMachineParam = new LinearLayout.LayoutParams(IMAGE_WIDTH, IMAGE_HEIGHT);
//
//        public LinearLayout.LayoutParams getSlotMachineParam() {
//            return slotMachineParam;
//        }
//
//        slotMachine.setLayoutParams();

    // Slot machine symbols
    private final int items[] = new int[] {
            android.R.drawable.star_big_on,
            android.R.drawable.stat_sys_warning,
            android.R.drawable.radiobutton_on_background,
            android.R.drawable.ic_delete
    };

    // Cached images
    private List<SoftReference<Bitmap>> images;

    // Layout inflater
    private Context context;

    /**
     * Constructor
     */
    public SlotMachineAdapter(Context context) {
        this.context = context;
        images = new ArrayList<SoftReference<Bitmap>>(items.length);
        for (int id : items) {
            images.add(new SoftReference<Bitmap>(loadImage(id)));
        }
    }

    /**
     * Loads image from resources
     */
    private Bitmap loadImage(int id) {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), id);
        Bitmap scaled = Bitmap.createScaledBitmap(bitmap, IMAGE_WIDTH, IMAGE_HEIGHT, true);
        bitmap.recycle();
        return scaled;
    }

    @Override
    public int getItemsCount() {
        return items.length;
    }

    // Layout params for image view
    final ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(IMAGE_WIDTH, IMAGE_HEIGHT);

    @Override
    public View getItem(int index, View cachedView, ViewGroup parent) {
        ImageView img;
        if (cachedView != null) {
            img = (ImageView) cachedView;
        } else {
            img = new ImageView(context);
        }
        img.setLayoutParams(params);
        SoftReference<Bitmap> bitmapRef = images.get(index);
        Bitmap bitmap = bitmapRef.get();
        if (bitmap == null) {
            bitmap = loadImage(items[index]);
            images.set(index, new SoftReference<Bitmap>(bitmap));
        }
        img.setImageBitmap(bitmap);

        return img;
    }
}
