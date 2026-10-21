package praktikum;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BurgerEmptyIngredientsTest {

    @Test
    public void receiptWithoutIngredientsContainsTwoBunHalvesAndTotal() {
        Bun bun = mock(Bun.class);
        when(bun.getName()).thenReturn("space bun");
        when(bun.getPrice()).thenReturn(120.0f);
        Burger burger = new Burger();
        burger.setBuns(bun);

        assertEquals(String.format("(==== space bun ====)%n(==== space bun ====)%n%nPrice: %f%n", 240.0f),
                burger.getReceipt());
    }

    @Test
    public void getPriceWithoutIngredientsReturnsDoubleBunPrice() {
        Bun bun = mock(Bun.class);
        when(bun.getPrice()).thenReturn(120.0f);

        Burger burger = new Burger();
        burger.setBuns(bun);

        assertEquals(240.0f, burger.getPrice(), 0.001f);
    }
}
