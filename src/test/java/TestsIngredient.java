import org.junit.Test;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;

public class TestsIngredient
{
    @Test
    public void getPriceShouldReturnValidData()
    {
        //Arrange
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "Соленный огурец", 1.5F );
        float expectedPrice = 1.5F;
        float actualPrice;

        //Act
        actualPrice = ingredient.getPrice();

        //Assert
        assertEquals("Price ingredient должен совпадать", expectedPrice, actualPrice, 0.00F);
    }

    @Test
    public void getNameShouldReturnValidData()
    {

        //Arrange
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "Соленный огурец", 1.5F );
        String expectedName = "Соленный огурец";
        String actualName;

        //Act
        actualName = ingredient.getName();

        //Assert
        assertEquals("Name ingredient должен совпадать", expectedName, actualName);
    }

    @Test
    public void getTypeShouldReturnValidData()
    {
        //Arrange
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "Соленный огурец", 1.5F );
        IngredientType expectedIngredientType = IngredientType.FILLING;
        IngredientType actualIngredientType;

        //Act
        actualIngredientType = ingredient.getType();

        //Assert
        assertEquals("Type ingredient должен совпадать", expectedIngredientType, actualIngredientType);

    }
}
