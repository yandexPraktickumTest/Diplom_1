import org.junit.Test;
import praktikum.IngredientType;

import static junit.framework.TestCase.assertEquals;


public class TestsIngredientType
{
    @Test
    public void newEnumObjectShouldHaveNameFillingAsParameter()
    {
        //Arrange
        IngredientType expectedEnum = IngredientType.FILLING;
        IngredientType actualEnum;

        //Act
        actualEnum = IngredientType.valueOf("FILLING");

        //Assert
        assertEquals("name должен совпадать",expectedEnum,actualEnum);
    }

    @Test
    public void newEnumObjectShouldHaveNameSauceAsParameter()
    {
        //Arrange
        IngredientType expectedEnum = IngredientType.SAUCE;
        IngredientType actualEnum;

        //Act
        actualEnum = IngredientType.valueOf("SAUCE");

        //Assert
        assertEquals("name должен совпадать",expectedEnum,actualEnum);
    }
}
