package closurekoans

import org.junit.*

import static closurekoans.SupportCode.___
import static org.junit.Assert.fail

// To run: click inside the first test,
// and hit control-shift-F10.
// Make the test pass by replacing the ___ with
// a correct value.
class Lesson1 {
    @Test void "adding up numbers is fun!"() {
        def variable = 2 + 3
        assert variable == ___
    }

    @Test void "assigning values to variables"() {
        def variable = ___
        assert variable == "No it isn't!"
    }

    @Test void "calling a closure"() {
        def closure = { assert "blah" == ___ }
        closure.call()
    }

    @Test void "calling a closure implicitly"() {
        def closure = { assert "blah" == ___ }
        closure()
    }

    @Test void "a closure does nothing until you call it"() {
        def closure = { fail "Don't run this!" }
        def shouldRun = ___
        if (shouldRun) closure()
    }

    @Test
    void accessingVariablesInTheEnclosingMethod() {
        def a = "but fun"
        def c = { -> ___}
        def s = c()
        assert s == "but fun"
    }

    @Test
    void thingsCanChange() {
        def a = 1
        def c = { -> a++ > 3 ? "too many poodles" : "keep going" }
        c()
        c()
        c()
        def result = c()
        assert result == ___
    }
}
