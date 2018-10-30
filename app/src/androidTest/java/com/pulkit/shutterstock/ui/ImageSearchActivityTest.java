package com.pulkit.shutterstock.ui;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.pulkit.shutterstock.utils.RecyclerViewItemCountAssertion.withItemCount;
import static com.pulkit.shutterstock.utils.ViewActionUtils.typeSearchViewText;
import static org.mockito.Mockito.when;

import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.pulkit.shutterstock.R;
import com.pulkit.shutterstock.TestApplication;
import com.pulkit.shutterstock.di.TestComponent;
import com.pulkit.shutterstock.domain.ShutterSearcher;
import com.pulkit.shutterstock.ui.image.ImageSearchActivity;
import com.pulkit.shutterstock.utils.SampleDataReader;
import io.reactivex.Single;
import io.reactivex.schedulers.TestScheduler;
import javax.inject.Inject;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;

@RunWith(AndroidJUnit4.class)
public class ImageSearchActivityTest {

  @Inject
  SampleDataReader dataReader;
  @Inject
  ShutterSearcher searcher;
  @Inject
  TestScheduler testScheduler;


  @Rule
  public ActivityTestRule<ImageSearchActivity> activityRule = new ActivityTestRule<>(
      ImageSearchActivity.class, true);

  @Before
  public void setUp() {
    Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
    TestApplication app = (TestApplication) instrumentation.getTargetContext().getApplicationContext();
    TestComponent component = app.getTestComponent();
    component.inject(this);
  }

  @Test
  public void test1() throws InterruptedException {
    when(searcher.search(ArgumentMatchers.anyString(), ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt()))
        .thenReturn(Single.just(dataReader.page1()));

    ImageSearchActivity activity = activityRule.launchActivity(new Intent());

    onView(withId(R.id.searchView)).perform(click());
    onView(withId(R.id.searchView)).perform(typeSearchViewText("test"));
    Thread.sleep(ImageSearchActivity.DEBOUNCE_TIMEOUT); // wait for debounce

    testScheduler.triggerActions();

    onView(withId(R.id.recyclerView)).check(withItemCount(20));
  }

}
