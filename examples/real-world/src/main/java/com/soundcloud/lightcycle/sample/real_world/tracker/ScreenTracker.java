package com.soundcloud.lightcycle.sample.real_world.tracker;

import com.soundcloud.lightcycle.DefaultActivityLightCycle;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

public class ScreenTracker extends DefaultActivityLightCycle<Screen> {

    private final TrackingOperations operations;

    @Inject
    public ScreenTracker(TrackingOperations operations) {
        this.operations = operations;
    }

    @Override
    public void onResume(@NotNull Screen screen) {
        operations.trackScreen(screen);
    }
}
