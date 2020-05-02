package com.rmondjone.camera;


import android.hardware.SensorEvent;
import android.support.v4.util.ArrayMap;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class SizeMap {

    private final ArrayMap<AspectRatio, SortedSet<Size>> mRatios = new ArrayMap<>();

    /**
     * Add a new {@link Size} to this collection.
     *
     * @param size The size to add.
     * @return {@code true} if it is added, {@code false} if it already exists and is not added.
     */
    public boolean add(Size size) {
        for (AspectRatio ratio : mRatios.keySet()) {
            if (ratio.matches(size)) {
                final SortedSet<Size> sizes = mRatios.get(ratio);
                if (sizes.contains(size)) {
                    return false;
                } else {
                    sizes.add(size);
                    return true;
                }
            }
        }
        // None of the existing ratio matches the provided size; add a new key
        SortedSet<Size> sizes = new TreeSet<>();
        sizes.add(size);
        mRatios.put(AspectRatio.of(size.getWidth(), size.getHeight()), sizes);
        return true;
    }

    /**
     * Removes the specified aspect ratio and all sizes associated with it.
     *
     * @param ratio The aspect ratio to be removed.
     */
    public void remove(AspectRatio ratio) {
        mRatios.remove(ratio);
    }

    Set<AspectRatio> ratios() {
        return mRatios.keySet();
    }

    SortedSet<Size> sizes(AspectRatio ratio) {

        SortedSet<Size> sizeSet =  mRatios.get(ratio);

        if ( null == sizeSet) {
            double diff = Double.MAX_VALUE;
            for(AspectRatio aspectRatio : mRatios.keySet()){
                if ( diff > ratio.getDiff(aspectRatio)) {
                    diff = ratio.getDiff(aspectRatio);
                    sizeSet = mRatios.get(aspectRatio);
                }
            }
        }
        return sizeSet;
    }

    void clear() {
        mRatios.clear();
    }

    boolean isEmpty() {
        return mRatios.isEmpty();
    }

}
