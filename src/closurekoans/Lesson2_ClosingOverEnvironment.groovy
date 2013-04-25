package closurekoans

import closurekoans.supportcode.ClosureKoans
import org.junit.Test

/* Instructions:
 * 1) Find the first failing test method.
 *    (In IntelliJ: click somewhere inside the test, and hit Control-Shift-F10)
 * 2) Fill in the blank ___ to make it pass.
 * 3) Run it again and see how you did.
 * 4) Once the test is passing, meditate on what you've learned.
 *
 * Note: Do not change anything other than the blank.
 */
class Lesson2_ClosingOverEnvironment extends ClosureKoans {
    @Test void "a closure has access to ('closes over') its environment"() {
        def sandwich = portobello
        def c = { sandwich.ingredients }
        assert c.call() == ___
    }

    @Test void "watch how things change"() {
        def sandwich = pbj
        def checkIngredients = { expectedIngredients ->
            assert sandwich.ingredients == expectedIngredients
        }

        checkIngredients(['peanut butter', 'jelly'])

        sandwich = cubano
        checkIngredients(___)
    }

    @Test void "I'm changing the environment -- ask me how!"() {
        def sandwich = pbj
        def improveSandwich = { sandwich.ingredients.add(secretIngredient) }

        assert sandwich.ingredients == ['peanut butter', 'jelly']
        improveSandwich()
        assert sandwich.ingredients == ___
    }
}
