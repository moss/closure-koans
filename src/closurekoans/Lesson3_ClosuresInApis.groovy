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
class Lesson3_ClosuresInApis extends ClosureKoans {
    @Test void "duplicated logic is ugly"() {
        assert cubano.ingredients.first().toUpperCase() == 'HAM'
        assert pbj.ingredients.first().toUpperCase() == ___
    }

    @Test void "extracting a method can help"() {
        assert yellFirstIngredient(cubano) == 'HAM'
        assert yellFirstIngredient(pbj) == 'PEANUT BUTTER'
    }

    def yellFirstIngredient(Sandwich sandwich) {
        ___
    }

    @Test void "sometimes duplication is more subtle"() {
        def cubanFirst = cubano.ingredients.first()
        def cubanLast = cubano.ingredients.last()
        cubanFirst = cubanFirst.toUpperCase()
        cubanLast = cubanLast.toUpperCase()
        def cubanResult = [cubanFirst, cubanLast]

        def pbjFirst = pbj.ingredients.first()
        def pbjLast = pbj.ingredients.last()
        pbjFirst = pbjFirst.toLowerCase()
        pbjLast = pbjLast.toLowerCase()
        def pbjResult = [pbjFirst, pbjLast]

        assert cubanResult == ['HAM', 'MUSTARD']
        assert pbjResult == ___
    }

    @Test void "a closure can address subtle duplication"() {
        def cubanResult = modifyOutsides(cubano) { it.toUpperCase() }
        def pbjResult = modifyOutsides(pbj) { it.toLowerCase() }

        assert cubanResult == ['HAM', 'MUSTARD']
        assert pbjResult == ['peanut butter', 'jelly']
    }

    def modifyOutsides(Sandwich sandwich, Closure whatToDoWithTheIngredients) {
        def first = sandwich.ingredients.first()
        def last = sandwich.ingredients.last()
        first = whatToDoWithTheIngredients.call(first)
        last = ___
        return [first, last]
    }
}
