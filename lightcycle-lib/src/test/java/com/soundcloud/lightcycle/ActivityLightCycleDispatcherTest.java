package com.soundcloud.lightcycle;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ActivityLightCycleDispatcherTest {
    @Mock
    private ActivityLightCycle<Activity> lightCycleComponent1;
    @Mock
    private ActivityLightCycle<Activity> lightCycleComponent2;
    @Mock
    private FragmentActivity activity;
    private ActivityLightCycleDispatcher<Activity> dispatcher;

    @Before
    public void setUp() throws Exception {
        dispatcher = new ActivityLightCycleDispatcher<>();
        dispatcher.bind(lightCycleComponent1);
        dispatcher.bind(lightCycleComponent2);
    }

    @Test
    public void dispatchOnCreate() {
        final Bundle bundle = Bundle.EMPTY;

        dispatcher.onCreate(activity, bundle);

        verify(lightCycleComponent1).onCreate(activity, bundle);
        verify(lightCycleComponent2).onCreate(activity, bundle);
    }

    @Test
    public void dispatchOnPostCreate() {
        final Bundle bundle = Bundle.EMPTY;

        dispatcher.onPostCreate(activity, bundle);

        verify(lightCycleComponent1).onPostCreate(activity, bundle);
        verify(lightCycleComponent2).onPostCreate(activity, bundle);
    }

    @Test
    public void dispatchOnNewIntent() {
        final Intent intent = mock(Intent.class);

        dispatcher.onNewIntent(activity, intent);

        verify(lightCycleComponent1).onNewIntent(activity, intent);
        verify(lightCycleComponent2).onNewIntent(activity, intent);
    }

    @Test
    public void dispatchOnStart() {
        dispatcher.onStart(activity);

        verify(lightCycleComponent1).onStart(activity);
        verify(lightCycleComponent2).onStart(activity);
    }

    @Test
    public void dispatchOnResume() {
        dispatcher.onResume(activity);

        verify(lightCycleComponent1).onResume(activity);
        verify(lightCycleComponent2).onResume(activity);
    }

    @Test
    public void dispatchOnPause() {
        dispatcher.onPause(activity);

        verify(lightCycleComponent1).onPause(activity);
        verify(lightCycleComponent2).onPause(activity);
    }

    @Test
    public void dispatchOnStop() {
        dispatcher.onStop(activity);

        verify(lightCycleComponent1).onStop(activity);
        verify(lightCycleComponent2).onStop(activity);
    }

    @Test
    public void dispatchOnSaveInstanceState() {
        final Bundle bundle = Bundle.EMPTY;

        dispatcher.onSaveInstanceState(activity, bundle);

        verify(lightCycleComponent1).onSaveInstanceState(activity, bundle);
        verify(lightCycleComponent2).onSaveInstanceState(activity, bundle);
    }

    @Test
    public void dispatchOnRestoreInstanceState() {
        final Bundle bundle = Bundle.EMPTY;

        dispatcher.onRestoreInstanceState(activity, bundle);

        verify(lightCycleComponent1).onRestoreInstanceState(activity, bundle);
        verify(lightCycleComponent2).onRestoreInstanceState(activity, bundle);
    }

    @Test
    public void dispatchOnWindowFocusChanged() {
        boolean hasFocus = true;

        dispatcher.onWindowFocusChanged(activity, hasFocus);

        verify(lightCycleComponent1).onWindowFocusChanged(activity, hasFocus);
        verify(lightCycleComponent2).onWindowFocusChanged(activity, hasFocus);
    }

    @Test
    public void dispatchOnActivityResult() {
        int requestCode = 1;
        int resultCode = 2;
        Intent data = new Intent();

        dispatcher.onActivityResult(activity, requestCode, resultCode, data);

        verify(lightCycleComponent1).onActivityResult(activity, requestCode, resultCode, data);
        verify(lightCycleComponent2).onActivityResult(activity, requestCode, resultCode, data);
    }

    @Test
    public void dispatchOnConfigurationChanged() {
        final Configuration configuration = new Configuration();

        dispatcher.onConfigurationChanged(activity, configuration);

        verify(lightCycleComponent1).onConfigurationChanged(activity, configuration);
        verify(lightCycleComponent2).onConfigurationChanged(activity, configuration);
    }

    @Test
    public void dispatchOnMultiWindowModeChanged() {
        dispatcher.onMultiWindowModeChanged(activity, true);

        verify(lightCycleComponent1).onMultiWindowModeChanged(activity, true);
        verify(lightCycleComponent2).onMultiWindowModeChanged(activity, true);
    }

    @Test
    public void dispatchOnDestroy() {
        dispatcher.onDestroy(activity);

        verify(lightCycleComponent1).onDestroy(activity);
        verify(lightCycleComponent2).onDestroy(activity);
    }

    @Test
    public void dispatchOnBackPressed() {
        dispatcher.onBackPressed(activity);

        verify(lightCycleComponent1).onBackPressed(activity);
        verify(lightCycleComponent2).onBackPressed(activity);
    }

    @Test
    public void dispatchOnlyOnceToDuplicatesComponents() {
        final Bundle bundle = Bundle.EMPTY;
        dispatcher.bind(lightCycleComponent1);
        dispatcher.bind(lightCycleComponent1);
        dispatcher.onCreate(activity, bundle);

        verify(lightCycleComponent1, times(1)).onCreate(activity, bundle);
    }

    @Test(expected = NullPointerException.class)
    public void nullBinderTarget() {
        dispatcher.bind(null);
    }
}
