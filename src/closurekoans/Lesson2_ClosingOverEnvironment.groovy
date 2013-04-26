package closurekoans

import closurekoans.supportcode.ClosureKoans
import closurekoans.supportcode.Sandwich
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

    @Test void "closures close over variables, not over values"() {
        def sandwich = pbj
        def replaceSandwich = { sandwich = cubano }

        assert sandwich.ingredients == ['peanut butter', 'jelly']
        replaceSandwich()
        assert sandwich.ingredients == ___
    }

    @Test void "and now, time for a game of Find The Blank"() {
        def ruin = { sandwich -> sandwich.ingredients.add(anotherIngredient()) }
        ruin(italian)
        assert italian.ingredients.contains('lettuce') // "Lettuce ruins an Italian." --Al
    }

    String anotherIngredient() {
        return ___
    }

    @Test void "functions making functions"() {
        def ruin = addIngredient('lettuce')
        ruin(pbj)
        assert pbj.ingredients.contains('lettuce') // Lettuce's record is really not great at this point.
    }

    Closure addIngredient(ingredient) {
        return { sandwich -> sandwich.ingredients.add(___) }
    }

    @Test void "setting up a high production volume function factory"() {
        def veganize = removeIngredient('jack cheese')
        def blandify = removeIngredient('hots')
        def createLightVersion = removeIngredient('swiss cheese')

        veganize(portobello)
        blandify(italian)
        createLightVersion(cubano)

        assert portobello.ingredients == ['portobello mushrooms', 'pesto']
        assert italian.ingredients == ['salami', 'mortadella', 'capicola', 'cheese', 'tomatoes', 'onions', 'oil', 'vinegar']
        assert cubano.ingredients == ['ham', 'pork', 'pickles', 'mustard'] // totally healthy!
    }

    Closure removeIngredient(ingredient) {
        { sandwich -> sandwich.____ }
    }

    @Test void "spooky action at a distance"() {
        def enhancePbj = defSandwichEnhancerMethod(pbj)
        enhancePbj('lime pickle')
        enhancePbj('sardines')
        enhancePbj('marmite')
        enhancePbj('sugar')
        enhancePbj('salt')
        assert pbj.ingredients == ___
    }

    Closure defSandwichEnhancerMethod(Sandwich sandwich) {
        { ingredient -> sandwich.ingredients.add(ingredient) }
    }
}
