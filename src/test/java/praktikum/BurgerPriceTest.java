package praktikum;

import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerPriceTest {
    private final float bunPrice;
    private final float[] ingredientPrices;
    private final float expected;

    public BurgerPriceTest(float bunPrice, float[] ingredientPrices, float expected) {
        this.bunPrice = bunPrice;
        this.ingredientPrices = ingredientPrices;
        this.expected = expected;
    }

    @Parameterized.Parameters(name = "bun={0}, expected total={2}")
    public static Collection<Object[]> prices() {
        return Arrays.asList(new Object[][] {
            {0f, new float[] {}, 0f},
            {120f, new float[] {}, 240f},
            {100f, new float[] {50f}, 250f},
            {100f, new float[] {50f, 75f}, 325f},
            {12.5f, new float[] {0.25f, 1.75f}, 27f},
            {0f, new float[] {3f, 3f, 3f}, 9f}
        });
    }

    @Test
    public void priceIncludesTwoBunHalvesAndEveryIngredient() {
        Burger burger = new Burger();
        Bun bun = mock(Bun.class);
        when(bun.getPrice()).thenReturn(bunPrice);
        burger.setBuns(bun);
        for (float price : ingredientPrices) {
            Ingredient ingredient = mock(Ingredient.class);
            when(ingredient.getPrice()).thenReturn(price);
            burger.addIngredient(ingredient);
        }
        assertEquals(expected, burger.getPrice(), 0.0001f);
    }
}
