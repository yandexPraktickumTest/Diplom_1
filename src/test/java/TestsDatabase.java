import org.junit.Test;
import praktikum.Bun;
import praktikum.Database;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestsDatabase
{
    @Test
    public void availableBunsShouldReturnValidData()
    {
        //Arrange
        List<Bun> expectedBuns = new ArrayList<>();

        expectedBuns.add(new Bun("black bun", 100));
        expectedBuns.add(new Bun("white bun", 200));
        expectedBuns.add(new Bun("red bun", 300));

        Database database = new Database();
        String expectedBunName;
        String actualBunName;
        float expectedBunPrice;
        float actualBunPrice;
        Bun expectedObj;
        Bun actualObj;

        //Act
        List<Bun> actualBuns = database.availableBuns();

        //Assert
        assertEquals("Размеры списков должны совпадать",expectedBuns.size(),actualBuns.size());
        for (int i = 0; i < expectedBuns.size(); i++)
        {
            expectedObj = expectedBuns.get(i);
            actualObj = actualBuns.get(i);

            expectedBunName = expectedObj.getName();
            expectedBunPrice = expectedObj.getPrice();
            actualBunName = actualObj.getName();
            actualBunPrice = actualObj.getPrice();

            assertEquals("Name bun должно совпадать",expectedBunName, actualBunName);
            assertEquals("Price bun должна совпадать", expectedBunPrice, actualBunPrice, 0.00F);
        }
    }

    @Test
    public void availableIngredientsShouldReturnValidData()
    {
        //Arrange
        List<Ingredient> expectedIngredients = new ArrayList<>();

        expectedIngredients.add(new Ingredient(IngredientType.SAUCE, "hot sauce", 100));
        expectedIngredients.add(new Ingredient(IngredientType.SAUCE, "sour cream", 200));
        expectedIngredients.add(new Ingredient(IngredientType.SAUCE, "chili sauce", 300));

        expectedIngredients.add(new Ingredient(IngredientType.FILLING, "cutlet", 100));
        expectedIngredients.add(new Ingredient(IngredientType.FILLING, "dinosaur", 200));
        expectedIngredients.add(new Ingredient(IngredientType.FILLING, "sausage", 300));

        Database database = new Database();
        String expectedIngredientName;
        String actualIngredientName;
        float expectedIngredientPrice;
        float actualIngredientPrice;
        IngredientType expectedIngredientType;
        IngredientType actualIngredientType;
        Ingredient expectedObj;
        Ingredient actualObj;

        //Act
        List<Ingredient> actualIngredients = database.availableIngredients();

        //Assert
        assertEquals("Размеры списков должны совпадать",expectedIngredients.size(),actualIngredients.size());
        for (int i = 0; i < expectedIngredients.size(); i++)
        {
            expectedObj = expectedIngredients.get(i);
            actualObj = actualIngredients.get(i);

            expectedIngredientName = expectedObj.getName();
            expectedIngredientPrice = expectedObj.getPrice();
            expectedIngredientType = expectedObj.getType();
            actualIngredientName = actualObj.getName();
            actualIngredientPrice = actualObj.getPrice();
            actualIngredientType = actualObj.getType();

            assertEquals("Name ingredient должен совпадать",expectedIngredientName, actualIngredientName);
            assertEquals("Price ingredient должен совпадать", expectedIngredientPrice, actualIngredientPrice, 0.00F);
            assertEquals("Type ingredient должен совпадать",expectedIngredientType, actualIngredientType);
        }
    }
}
