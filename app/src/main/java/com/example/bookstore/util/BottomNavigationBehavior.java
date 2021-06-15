package com.example.bookstore.util;

import android.content.Context;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import org.jetbrains.annotations.NotNull;

public class BottomNavigationBehavior extends CoordinatorLayout.Behavior<BottomNavigationView> {

    public BottomNavigationBehavior() {
        super();
    }

    public BottomNavigationBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(@NotNull CoordinatorLayout parent, @NotNull BottomNavigationView child, @NotNull View dependency) {
        return dependency instanceof FrameLayout;
    }

    @Override
    public boolean onStartNestedScroll(
            @NotNull CoordinatorLayout coordinatorLayout,
            @NotNull BottomNavigationView child,
            @NotNull View directTargetChild,
            @NotNull View target,
            int nestedScrollAxes,
            int i) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

//    @Override
//    public void onNestedPreScroll(
//            @NotNull CoordinatorLayout coordinatorLayout,
//            @NotNull BottomNavigationView child,
//            @NotNull View target,
//            int dx,
//            int dy,
//            @NotNull int[] consumed,
//            int i
//    ) {
//        if (dy < 0) {
//            showBottomNavigationView(child);
//        } else if (dy > 0) {
//            hideBottomNavigationView(child);
//        }
//    }
//
//    private void hideBottomNavigationView(BottomNavigationView view) {
//        view.animate().translationY(view.getHeight());
//    }
//
//    private void showBottomNavigationView(BottomNavigationView view) {
//        view.animate().translationY(0);
//    }
}