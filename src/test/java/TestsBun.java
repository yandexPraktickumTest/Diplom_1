import org.junit.Test;
import praktikum.Bun;

import static org.junit.Assert.assertEquals;

public class TestsBun
{
    @Test
    public void  getNameShouldGetCorrectName()
    {
        //Arrange
        String expectedName = "Common";
        float price = 1.1F;
        String actualName;

        //Act
        Bun bun = new Bun(expectedName, price);
        actualName = bun.getName();

        //Assert
        assertEquals("getName вернул name отличный от заложенного в параметрах Bun obj ", expectedName, actualName);
    }

    @Test
    public void  getPriceShouldGetCorrectPrice()
    {
        //Arrange
        String name = "Common";
        int i = 1;
        float expectedPrice = 1.1F;
        float actualPrice;

        //Act
        Bun bun = new Bun(name, expectedPrice);
        actualPrice = bun.getPrice();


        //Assert
        assertEquals("getPrice вернул price отличный от заложенного в параметрах Bun obj", expectedPrice, actualPrice, 0.0F);
    }
}
