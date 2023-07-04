import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

@RunWith(Enclosed.class)
class TestsBurger
{
    @RunWith(Parameterized.class)
    public static class BurgerParameterizedTests
    {
        private final int indexNumberForPositiveTests;
        private final int nextIndex;
        private final int indexNumberForNegativeTests;

        public BurgerParameterizedTests(int indexNumberForPositiveTests, int nextIndex, int  indexNumberForNegativeTests)
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

    public static class BurgerNonParameterizedTests
    {
        @Test
        public void getReceiptShouldReturnValidText()
        {
            //Arrange
            String expectedReceipt = "(==== Common ====)" + "\r\n" +
                    "= filling name1 =" + "\r\n" +
                    "= filling name2 =" + "\r\n" +
                    "= filling name3 =" + "\r\n" +
                    "= filling name4 =" + "\r\n" +
                    "(==== Common ====)" + "\r\n" +
                    "" + "\r\n" +
                    "Price: 24,500000" + "\r\n" +
                    "";
            Ingredient ing1 = mock();
            Ingredient ing2 = mock();
            Ingredient ing3 = mock();
            Ingredient ing4 = mock();
            Burger spyBurger = spy(Burger.class);
            spyBurger.addIngredient(ing1);
            spyBurger.addIngredient(ing2);
            spyBurger.addIngredient(ing3);
            spyBurger.addIngredient(ing4);
            String actualReceipt;
            spyBurger.setBuns(mock());

            Mockito.when(spyBurger.bun.getPrice()).thenReturn(2.5F);
            Mockito.when(spyBurger.bun.getName()).thenReturn("Common");
            Mockito.when(spyBurger.getPrice()).thenReturn(24.5F);
            Mockito.when(ing1.getPrice()).thenReturn(1.1F);
            Mockito.when(ing1.getName()).thenReturn("name1");
            Mockito.when(ing1.getType()).thenReturn(IngredientType.FILLING);
            Mockito.when(ing2.getPrice()).thenReturn(1.1F);
            Mockito.when(ing2.getName()).thenReturn("name2");
            Mockito.when(ing2.getType()).thenReturn(IngredientType.FILLING);
            Mockito.when(ing3.getPrice()).thenReturn(1.1F);
            Mockito.when(ing3.getName()).thenReturn("name3");
            Mockito.when(ing3.getType()).thenReturn(IngredientType.FILLING);
            Mockito.when(ing4.getPrice()).thenReturn(1.1F);
            Mockito.when(ing4.getName()).thenReturn("name4");
            Mockito.when(ing4.getType()).thenReturn(IngredientType.FILLING);

            //Act
            actualReceipt= spyBurger.getReceipt();

            //Assert
            assertEquals("getReceipt вернул невалидный текст",expectedReceipt, actualReceipt);
        }

        @Test(expected = Exception.class)//Assert
        public void getPriceShouldTrowExceptionWhenBunOrAndIngredientHasMinusPriceAsParameter()
        {
            //Arrange
            Ingredient ing1 = mock();
            Ingredient ing2 = mock();
            Ingredient ing3 = mock();
            Ingredient ing4 = mock();
            Mockito.when(ing1.getPrice()).thenReturn(-1.1F);
            Mockito.when(ing2.getPrice()).thenReturn(-1.1F);
            Mockito.when(ing3.getPrice()).thenReturn(-1.1F);
            Mockito.when(ing4.getPrice()).thenReturn(-1.1F);
            Burger burger = new Burger();
            burger.addIngredient(ing1);
            burger.addIngredient(ing2);
            burger.addIngredient(ing3);
            burger.addIngredient(ing4);
            burger.setBuns(mock());
            Mockito.when(burger.bun.getPrice()).thenReturn(-1.1F);

            //Act
            burger.getPrice();
        }

        @Test
        public void  getPriceShouldReturnCorrectResult()
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
            burger.setBuns(mock());
            burger.addIngredient(mock());
            Mockito.when(burger.bun.getPrice()).thenReturn(2F);
            Mockito.when(ing1.getPrice()).thenReturn(1F);
            Mockito.when(ing2.getPrice()).thenReturn(1F);
            Mockito.when(ing3.getPrice()).thenReturn(1F);
            Mockito.when(ing4.getPrice()).thenReturn(1F);
            float expectedPrice = 8;
            float actualPrice;

            //Act
            actualPrice= burger.getPrice();

            //Assert
            assertEquals("getPrice вернул невалидное значение",expectedPrice, actualPrice, 0.001F);
        }

        @Test
        public void  addIngredientShouldAddInformationToArrayList()
        {
            //Arrange
            Ingredient ingredient = new Ingredient(IngredientType.FILLING,"Соленный огурец", 1.1F);
            Burger burger = new Burger();


            //Act
            burger.addIngredient(ingredient);


            //Assert
            assertFalse("addIngredient не добавила в список ингредиент",burger.ingredients.isEmpty());
        }

        @Test
        public void  removeIngredientShouldDeleteIngredientInformationFromArrayList()
        {
            //Arrange
            Ingredient pickledCucumber = new Ingredient(IngredientType.FILLING,"Соленный огурец", 1.1F);
            Burger burger = new Burger();
            burger.addIngredient(pickledCucumber );

            //Act
            burger.removeIngredient(0);

            //Assert
            assertTrue("removeIngredient не удалил  ингредиент из списка",burger.ingredients.isEmpty());
        }
    }
}
