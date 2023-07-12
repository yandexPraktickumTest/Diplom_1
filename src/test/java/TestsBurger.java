import org.junit.Test;
import org.mockito.Mockito;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

    public  class TestsBurger
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
            Ingredient ingredient = mock();
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
            Ingredient ingredient = mock();
            Burger burger = new Burger();
            burger.addIngredient(ingredient);

            //Act
            burger.removeIngredient(0);

            //Assert
            assertTrue("removeIngredient не удалил  ингредиент из списка",burger.ingredients.isEmpty());
        }
    }
