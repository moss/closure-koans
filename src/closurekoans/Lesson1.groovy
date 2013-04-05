package closurekoans

import org.junit.*

import static closurekoans.SupportCode.___

// To run: click inside the first test,
// and hit control-shift-F10.
// Make the test pass by replacing the ___ with
// a correct value.
class Lesson1 {
    @Test
    void definingAString() {
        def s = ___
        assert s == "microwaving"
    }

    @Test
    void callingAClosureExplicitly() {
        def c = { -> "poodles"}
        def s = c.call()
        assert s == ___
    }

    @Test
    void callingAClosureImplicitly() {
        def c = { -> "is inappropriate"}
        def s = c()
        assert s == ___
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
