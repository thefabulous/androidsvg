package com.caverock.androidsvg;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by aminelaadhari on 05/03/15.
 */
public class SVGBuilder {
    private InputStream data;
    private ColorFilter strokeColorFilter = null, fillColorFilter = null;

    /**
     * Parse SVG data from an input stream.
     *
     * @param svgData the input stream, with SVG XML data in UTF-8 character encoding.
     * @return the parsed SVG.
     */
    public SVGBuilder readFromInputStream(InputStream svgData) {
        this.data = svgData;
        return this;
    }

    /**
     * Parse SVG data from a string.
     *
     * @param svgData the string containing SVG XML data.
     */
    public SVGBuilder readFromString(String svgData) {
        this.data = new ByteArrayInputStream(svgData.getBytes());
        return this;
    }

    /**
     * Parse SVG data from an Android application resource.
     *
     * @param resources the Android context resources.
     * @param resId     the ID of the raw resource SVG.
     */
    public SVGBuilder readFromResource(Resources resources, int resId) {
        this.data = resources.openRawResource(resId);
        return this;
    }

    /**
     * Parse SVG data from an Android application asset.
     *
     * @param assetMngr the Android asset manager.
     * @param svgPath   the path to the SVG file in the application's assets.
     * @throws IOException if there was a problem reading the file.
     */
    public SVGBuilder readFromAsset(AssetManager assetMngr, String svgPath) throws IOException {
        this.data = assetMngr.open(svgPath);
        return this;
    }

    /**
     * In white-mode, fills are drawn in white and strokes are not drawn at all.
     */
    public SVGBuilder setWhiteMode() {
        this.fillColorFilter = new PorterDuffColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
        return this;
    }

    /**
     * Applies a {@link ColorFilter} to strokes in the SVG.
     */
    public SVGBuilder setStrokeColorFilter(ColorFilter colorFilter) {
        this.strokeColorFilter = colorFilter;
        return this;
    }

    /**
     * Applies a {@link ColorFilter} to fills in the SVG.
     */
    public SVGBuilder setFillColorFilter(ColorFilter colorFilter) {
        this.fillColorFilter = colorFilter;
        return this;
    }

    /**
     * Loads, reads, parses the SVG (or SVGZ).
     *
     * @return the parsed SVG.
     * @throws SVGParseException if there is an error while parsing.
     */
    public SVG build() throws SVGParseException {
        if (data == null) {
            throw new IllegalStateException("SVG input not specified. Call one of the readFrom...() methods first.");
        }
        SVGParser parser = new SVGParser();
        SVG svg = parser.parse(data);
        svg.setFillColorFilter(fillColorFilter);
        svg.setStrokeColorFilter(strokeColorFilter);
        return svg;
    }
}