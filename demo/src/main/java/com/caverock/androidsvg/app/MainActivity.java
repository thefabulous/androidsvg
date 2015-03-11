package com.caverock.androidsvg.app;

import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGBuilder;
import com.caverock.androidsvg.SVGImageView;
import com.caverock.androidsvg.SVGParseException;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ColorFilter colorFilter = new ColorFilter();
        SVGImageView svgImageView = (SVGImageView) findViewById(R.id.svgImageView);

        SVG svg = null;
        try {
            svg = new SVGBuilder()
                    .readFromResource(getResources(), R.raw.water)
                    .setFillColorFilter(new PorterDuffColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN))
                    .build();

//            PictureDrawable pictureDrawable = new PictureDrawable(svg.renderToPicture());
//            Bitmap bitmap = Bitmap.createBitmap(pictureDrawable.getIntrinsicWidth(), pictureDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
//            Canvas canvas = new Canvas(bitmap);
//            canvas.drawPicture(pictureDrawable.getPicture());
//            svgImageView.setColorFilter(new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.SRC_IN));
//            svgImageView.setImageBitmap(bitmap);

            svgImageView.setSVG(svg);
        } catch (SVGParseException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
