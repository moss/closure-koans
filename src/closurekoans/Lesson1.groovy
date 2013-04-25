package closurekoans

import closurekoans.supportcode.ClosureKoans
import org.junit.*

import static org.junit.Assert.fail

/* Instructions:
 * 1) Run the first test.
 *    (In IntelliJ: click somewhere inside the test, and hit Control-Shift-F10)
 * 2) Fill in the ___ to make the test pass.
 * 3) Run it again and see how you did.
 * 4) Once the test is passing, meditate on what you've learned.
 */
class Lesson1 extends ClosureKoans {
    @Test void "assigning values to variables"() {
        def ingredients = cubano.ingredients
        assert ingredients == ___
    }

    @Test void "calling a closure"() {
        def closure = { assert pbj.___ == ['peanut butter', 'jelly'] }
        closure.call()
    }

    @Test void "calling a closure implicitly"() {
        def closure = { assert ___.ingredients == ['peanut butter', 'jelly'] }
        closure()
    }

    @Test void "a closure does nothing until you call it"() {
        def closure = { fail "Make me a sandwich! And fill in the blank!" }
        def object = ___
        if (!object.isSandwich()) closure()
    }

    @Test void "returning a value from a closure"() {
        def closure = { return ['peanut butter', ___] }
        assert closure.call() == pbj.ingredients
    }

    @Test void "returning a value implicitly"() {
        def closure = { italian.ingredients }
        assert closure.call() == ___
    }

    @Test void "closures can take arguments"() {
        def closure = { sandwich -> assert sandwich.ingredients == ___ }
        closure.call(cubano)
    }

    @Test void "closures can take an implicit argument"() {
        def closure = { assert it.ingredients == ['salami', 'mortadella', 'capicola', 'cheese', 'tomatoes', 'onions', 'oil', 'vinegar', 'hots'] }
        closure.call(___)
    }

    @Test void "see what happens if you leave out the implicit argument"() {
        def closure = { assert it == ___ }
        closure.call()
    }

    @Test void "closing over variables"() {
        def sandwich = portobello
        def c = { sandwich.ingredients }
        assert c.call() == ___
    }
}
