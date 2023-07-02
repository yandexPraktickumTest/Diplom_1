
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import praktikum.*;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


@RunWith(Enclosed.class)
public class Tests
{
                                                                     //BUN
    public static class BunNonParameterizedTests
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


                                                                    //BURGER

    @RunWith(Parameterized.class)
    public static class BurgerParameterizedTests
    {
        private final int indexNumberForPositiveTests;
        private final int nextIndex;
        private final int indexNumberForNegativeTests;
        private final float negativePrice;
        private final String ingredient1;
        private final String ingredient2;
        private final String ingredient3;
        private final String ingredient4;
        private final String expectedReceipt;





        public BurgerParameterizedTests(int indexNumberForPositiveTests, int nextIndex, int  indexNumberForNegativeTests,
                                        float negativePrice, String ingredient1, String ingredient2,
                                        String ingredient3, String ingredient4, String expectedReceipt)

        {
            this.indexNumberForPositiveTests = indexNumberForPositiveTests;
            this.nextIndex = nextIndex;
            this.indexNumberForNegativeTests = indexNumberForNegativeTests;

            this.negativePrice = negativePrice;
            this.ingredient1 = ingredient1;
            this.ingredient2 = ingredient2;
            this.ingredient3 = ingredient3;
            this.ingredient4 = ingredient4;
            this.expectedReceipt = expectedReceipt;
        }


        @Parameterized.Parameters
        public static Object[][] getValues() {

            return new Object[][]
                    {
                            {0, 3, -1,-1.7F, "Соленный огурец", "Помидор",
                                                    "Сыр", "Говяжья котлета", "(==== Common ====)" + "\r\n" +
                                                                                "= filling Соленный огурец =" + "\r\n" +
                                                                                "= filling Помидор =" + "\r\n" +
                                                                                "= filling Сыр =" + "\r\n" +
                                                                                "= filling Говяжья котлета =" + "\r\n" +
                                                                                "(==== Common ====)" + "\r\n" +
                                                                                "" + "\r\n" +
                                                                                "Price: 24,500000" + "\r\n" +
                                                                                ""},
                            {3, 0, 4, 0F, "Банан", "Перец",
                                                    "Фасоль", "Курица",       "(==== Common ====)" + "\r\n" +
                                                                                "= filling Банан =" + "\r\n" +
                                                                                "= filling Перец =" + "\r\n" +
                                                                                "= filling Фасоль =" + "\r\n" +
                                                                                "= filling Курица =" + "\r\n" +
                                                                                "(==== Common ====)" + "\r\n" +
                                                                                "" + "\r\n" +
                                                                                "Price: 24,500000" + "\r\n" +
                                                                                ""},
                            {3, 3, -4,-3.2F, "Авакадо", "Курага",
                                                    "Капуста", "Апельсин", "(==== Common ====)" + "\r\n" +
                                                                                "= filling Авакадо =" + "\r\n" +
                                                                                "= filling Курага =" + "\r\n" +
                                                                                "= filling Капуста =" + "\r\n" +
                                                                                "= filling Апельсин =" + "\r\n" +
                                                                                "(==== Common ====)" + "\r\n" +
                                                                                "" + "\r\n" +
                                                                                "Price: 24,500000" + "\r\n" +
                                                                                ""},
                    };
        }


        @Test
        public void  moveIngredientShouldReplaceIngredientOnWantedPositionInArrayList()
        {
            //Arrange
            Ingredient ing1  = new Ingredient(IngredientType.FILLING,ingredient1,1.5F);
            Ingredient ing2 = new Ingredient(IngredientType.FILLING,ingredient2,2.4F);
            Ingredient ing3 = new Ingredient(IngredientType.FILLING,ingredient3,3.7F);
            Ingredient ing4 = new Ingredient(IngredientType.FILLING,ingredient4,4.3F);
            Burger burger = new Burger();
            burger.addIngredient(ing1);
            burger.addIngredient(ing2);
            burger.addIngredient(ing3);
            burger.addIngredient(ing4);
            String expectedIngredientForExchange1 = burger.ingredients.get(indexNumberForPositiveTests).getName();
            String expectedIngredientForExchange2 = burger.ingredients.get(nextIndex).getName();
            int expectedArrayListSize = burger.ingredients.size();
            System.out.println(burger.ingredients.get(nextIndex));
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
            Ingredient ing1  = new Ingredient(IngredientType.FILLING,ingredient1,1.5F);
            Ingredient ing2 = new Ingredient(IngredientType.FILLING,ingredient2,2.4F);
            Ingredient ing3 = new Ingredient(IngredientType.FILLING,ingredient3,3.7F);
            Ingredient ing4 = new Ingredient(IngredientType.FILLING,ingredient4,4.3F);
            Burger burger = new Burger();
            burger.addIngredient(ing1);
            burger.addIngredient(ing2);
            burger.addIngredient(ing3);
            burger.addIngredient(ing4);

            //Act
            burger.moveIngredient(indexNumberForNegativeTests, nextIndex);
        }



        @Test(expected = Exception.class)//Assert
        public void getPriceShouldTrowExceptionWhenBunOrAndIngredientHasMinusPriceAsParameter()
        {
            //Arrange
            Ingredient ing1  = new Ingredient(IngredientType.FILLING,ingredient1,1.5F);
            Ingredient ing2 = new Ingredient(IngredientType.FILLING,ingredient2,2.4F);
            Ingredient ing3 = new Ingredient(IngredientType.FILLING,ingredient3,3.7F);
            Ingredient ing4 = new Ingredient(IngredientType.FILLING,ingredient4,4.3F);
            Burger burger = new Burger();
            burger.addIngredient(ing1);
            burger.addIngredient(ing2);
            burger.addIngredient(ing3);
            burger.addIngredient(ing4);
            burger.setBuns(new Bun("Common", negativePrice));

            //Act
            burger.getPrice();
        }
        @Test
        public void getReceiptShouldReturnValidText()
        {
            //Arrange
            Ingredient ing1  = new Ingredient(IngredientType.FILLING,ingredient1,1.5F);
            Ingredient ing2 = new Ingredient(IngredientType.FILLING,ingredient2,2.4F);
            Ingredient ing3 = new Ingredient(IngredientType.FILLING,ingredient3,3.7F);
            Ingredient ing4 = new Ingredient(IngredientType.FILLING,ingredient4,4.3F);
            Burger spyBurger = spy(Burger.class);
            spyBurger.addIngredient(ing1);
            spyBurger.addIngredient(ing2);
            spyBurger.addIngredient(ing3);
            spyBurger.addIngredient(ing4);
            String actualReceipt;
            spyBurger.setBuns(mock());

            spyBurger.setBuns(mock());
            Mockito.when(spyBurger.bun.getPrice()).thenReturn(2.5F);
            Mockito.when(spyBurger.bun.getName()).thenReturn("Common");
            Mockito.when(spyBurger.getPrice()).thenReturn(24.5F);

            //Act
            actualReceipt= spyBurger.getReceipt();

            //Assert
            assertEquals("getReceipt вернул невалидный текст",expectedReceipt, actualReceipt);
        }
    }

    public static class BurgerNonParameterizedTests
    {
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
                                                                    //DATABASE

    public static class DataBaseNonParameterizedTests
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

                                                                            //INGREDIENT

    public static class IngredientNonParameterizedTests
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
}