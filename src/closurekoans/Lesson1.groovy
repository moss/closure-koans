package closurekoans

import closurekoans.supportcode.ClosureKoans
import org.junit.*

import static org.junit.Assert.fail

// To run: click inside the first test,
// and hit control-shift-F10.
// Make the test pass by replacing the ___ with
// a correct value.
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
        def object = _
        if (!object.isSandwich()) closure()
    }

    @Test void "returning a value from a closure"() {
        def closure = { return ___ }
        assert closure.call() == "winston"
    }

    @Test void "returning a value implicitly"() {
        def closure = { "winston" }
        assert closure.call() == ___
    }

    @Test void "closures can take arguments"() {
        def closure = { word -> word.toUpperCase() }
        assert closure.___("nooo!") == "NOOO!"
    }

    @Test void "closures can take an implicit argument"() {
        def closure = { it.toUpperCase() }
        assert closure.call(___) == "NOOO!"
    }

    @Test void "see what happens if you leave out the implicit argument"() {
        def closure = { assert it == ___ }
        closure.call()
    }

    @Test void "closing over variables"() {
        def a = "what"
        def c = { a.toUpperCase() }
        assert c.call() == ___
    }

    @Test void "changing your environment"() {
        def items = ["Bread", "Peanut Butter"]
        def addItem = { items.add(it) }
        addItem "Lime Pickle"
        assert items == ___
    }
}
