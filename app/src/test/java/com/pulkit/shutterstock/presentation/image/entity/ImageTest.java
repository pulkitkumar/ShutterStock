package com.pulkit.shutterstock.presentation.image.entity;

import static org.junit.Assert.assertEquals;

import com.pulkit.shutterstock.di.DaggerUnitTestComponent;
import com.pulkit.shutterstock.domain.entity.ShutterData;
import com.pulkit.shutterstock.domain.entity.ShutterPage;
import com.pulkit.shutterstock.presentation.commons.SampleData;
import java.io.IOException;
import javax.inject.Inject;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for {@link Image}
 */
public class ImageTest {

  @Inject
  SampleData dataReader;

  @Before
  public void setup() {
    DaggerUnitTestComponent.builder().build().inject(this);
  }

  @Test
  public void test_conversion() throws IOException {
    ShutterPage page = dataReader.page1();
    ShutterData data = page.getData().get(0);
    Image image = new Image(data);

    assertEquals(image.getUrl(), data.getAssets().getHugeThumb().getUrl());
    assertEquals(image.getId(), data.getId());
  }

}
