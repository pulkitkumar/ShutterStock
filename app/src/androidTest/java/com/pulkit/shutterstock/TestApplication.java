package com.pulkit.shutterstock;

import com.pulkit.shutterstock.app.ShutterApplication;
import com.pulkit.shutterstock.di.DaggerTestComponent;
import com.pulkit.shutterstock.di.TestComponent;

public class TestApplication extends ShutterApplication {

  private TestComponent testComponent;

  @Override
  public void onCreate() {
    super.onCreate();
    testComponent = DaggerTestComponent.builder().app(this).build();
    testComponent.inject(this);
  }

  public TestComponent getTestComponent() {
    return testComponent;
  }
}
