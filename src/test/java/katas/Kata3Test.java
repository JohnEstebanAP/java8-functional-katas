package katas;

import com.google.common.collect.ImmutableList;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;


public class Kata3Test {

    @Test
    public void testExecute() {
        Assert.assertThat(Kata3.execute().size(), equalTo(4));
    }
}
