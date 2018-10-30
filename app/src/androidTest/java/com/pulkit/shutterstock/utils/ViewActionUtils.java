package com.pulkit.shutterstock.utils;

import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.hamcrest.core.AllOf.allOf;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.v7.widget.SearchView;
import android.view.View;
import org.hamcrest.Matcher;

public class ViewActionUtils {

  public static ViewAction typeSearchViewText(final String text){
    return new ViewAction(){
      @Override
      public Matcher<View> getConstraints() {
        return allOf(isDisplayed(), isAssignableFrom(SearchView.class));
      }

      @Override
      public String getDescription() {
        return "Change view text";
      }

      @Override
      public void perform(UiController uiController, View view) {
        ((SearchView) view).setQuery(text,false);
      }
    };
  }
}
