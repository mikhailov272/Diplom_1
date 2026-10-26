package praktikum;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BurgerTest {

    private Burger burger;
    private Bun bun;
    private Ingredient sauce;
    private Ingredient filling;

    @Before
    public void setUp() {
        burger = new Burger();

        bun = mock(Bun.class);
        when(bun.getName()).thenReturn("space bun");
        when(bun.getPrice()).thenReturn(100.0f);

        sauce = mock(Ingredient.class);
        when(sauce.getType()).thenReturn(IngredientType.SAUCE);
        when(sauce.getName()).thenReturn("hot sauce");
        when(sauce.getPrice()).thenReturn(50.0f);

        filling = mock(Ingredient.class);
        when(filling.getType()).thenReturn(IngredientType.FILLING);
        when(filling.getName()).thenReturn("meteorite cutlet");
        when(filling.getPrice()).thenReturn(75.0f);
    }

    @Test
    public void setBunsSetsSelectedBun() {
        burger.setBuns(bun);

        assertEquals(bun, burger.bun);
    }

    @Test
    public void addIngredientAddsIngredientToEnd() {
        burger.addIngredient(sauce);

        assertEquals(1, burger.ingredients.size());
        assertEquals(sauce, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientRemovesIngredientByIndex() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        burger.removeIngredient(0);

        assertEquals(1, burger.ingredients.size());
        assertEquals(filling, burger.ingredients.get(0));
    }

    @Test
    public void moveIngredientChangesIngredientPosition() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        burger.moveIngredient(0, 1);

        assertEquals(filling, burger.ingredients.get(0));
        assertEquals(sauce, burger.ingredients.get(1));
    }

    @Test
    public void getReceiptReturnsBurgerCompositionAndTotalPrice() {
        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        String expected = String.format("(==== space bun ====)%n") +
                String.format("= sauce hot sauce =%n") +
                String.format("= filling meteorite cutlet =%n") +
                String.format("(==== space bun ====)%n") +
                String.format("%nPrice: %f%n", 325.0f);

        assertEquals(expected, burger.getReceipt());
    }
}
