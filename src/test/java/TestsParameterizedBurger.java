import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import praktikum.Burger;
import praktikum.Ingredient;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(Parameterized.class)
public class TestsParameterizedBurger
{
    private final int indexNumberForPositiveTests;
    private final int nextIndex;
    private final int indexNumberForNegativeTests;

    public TestsParameterizedBurger(int indexNumberForPositiveTests, int nextIndex, int  indexNumberForNegativeTests)
    {
        this.indexNumberForPositiveTests = indexNumberForPositiveTests;
        this.nextIndex = nextIndex;
        this.indexNumberForNegativeTests = indexNumberForNegativeTests;
    }

    @Parameterized.Parameters
    public static Object[][] getValues() {

        return new Object[][]
                {
                        {0, 3, -1},
                        {3, 0, 4},
                        {3, 3, -4},
                };
    }

    @Test
    public void  moveIngredientShouldReplaceIngredientOnWantedPositionInArrayList()
    {
        //Arrange
        Ingredient ing1 = mock();
        Ingredient ing2 = mock();
        Ingredient ing3 = mock();
        Ingredient ing4 = mock();
        Mockito.when(ing1.getName()).thenReturn("name1");
        Mockito.when(ing2.getName()).thenReturn("name2");
        Mockito.when(ing3.getName()).thenReturn("name3");
        Mockito.when(ing4.getName()).thenReturn("name4");

        Burger burger = new Burger();
        burger.addIngredient(ing1);
        burger.addIngredient(ing2);
        burger.addIngredient(ing3);
        burger.addIngredient(ing4);
        String expectedIngredientForExchange1 = burger.ingredients.get(indexNumberForPositiveTests).getName();
        String expectedIngredientForExchange2 = burger.ingredients.get(nextIndex).getName();
        int expectedArrayListSize = burger.ingredients.size();
        int actualArrayListSize;

        //Act
        burger.moveIngredient(indexNumberForPositiveTests, nextIndex);
        actualArrayListSize = burger.ingredients.size();
        String actualIngredientForExchange1 = burger.ingredients.get(nextIndex).getName();
        String actualIngredientForExchange2 = burger.ingredients.get(indexNumberForPositiveTests).getName();

        //Assert
        assertEquals("Ингредиент " + expectedIngredientForExchange1 + " должен был оказаться на позиции под индексом " + nextIndex,expectedIngredientForExchange1,actualIngredientForExchange1);
        assertEquals("Ингредиент " + expectedIngredientForExchange2 + " должен был оказаться на позиции под индексом " + indexNumberForPositiveTests,expectedIngredientForExchange2,actualIngredientForExchange2);
        assertEquals("moveIngredient удалил ингредиент из ArrayList",expectedArrayListSize, actualArrayListSize);
    }

    @Test(expected = IndexOutOfBoundsException.class)//Assert
    public void  moveIngredientShouldThrownIndexOutOfBoundsExceptionInCafeOfInputIndexIsMinus()
    {
        //Arrange
        Ingredient ing1 = mock();
        Ingredient ing2 = mock();
        Ingredient ing3 = mock();
        Ingredient ing4 = mock();
        Burger burger = new Burger();
        burger.addIngredient(ing1);
        burger.addIngredient(ing2);
        burger.addIngredient(ing3);
        burger.addIngredient(ing4);

        //Act
        burger.moveIngredient(indexNumberForNegativeTests, nextIndex);
    }
}
