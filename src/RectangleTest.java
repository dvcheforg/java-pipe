import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class RectangleTest{

Rectangle rectTest = new Rectangle(5,6);

@Test
public void testGetArea(){
    assertEquals(rectTest.getArea(),30);
}

@Test
public void testGetPerimeter(){
  assertEquals(rectTest.getPerimeter(), 60);

}

@Test
public void testLength() {
  assertEquals(rectTest.length,5);
}
@Test
public void testWidth() {
  assertEquals(rectTest.width,6);
}

}
