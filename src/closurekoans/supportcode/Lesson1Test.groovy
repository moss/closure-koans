package closurekoans.supportcode

import closurekoans.Lesson1
import org.hamcrest.*
import org.junit.*
import org.junit.internal.matchers.*

import static org.hamcrest.CoreMatchers.not
import static org.junit.Assert.assertThat

class Lesson1Test {
    @Test
    void theTest() {
        assertKoan(new Lesson1(), "assigning values to variables", ['ham', 'pork', 'swiss cheese', 'pickles', 'mustard'])
        assertKoan(new Lesson1(), "calling a closure", "ingredients") { it.pbj.___ = it.pbj.ingredients }
    }

    private void assertKoan(ClosureKoans koan, String testName, def solution) {
        assertKoan(koan, testName, solution as String) { it.___ = solution }
    }

    private void assertKoan(ClosureKoans koan, String testName, String description, Closure<Void> solution) {
        Closure<Void> koanMethod = koan.&"$testName"
        assertThat("${testName} starts out unsolved", koanMethod, not(isSolved()))
        solution.call(koan)
        assertThat("${testName} solved by <$description>", koanMethod, isSolved())
    }

    private static Matcher<Closure<Void>> isSolved() {
        return new TypeSafeMatcher<Closure<Void>>() {
            @Override boolean matchesSafely(Closure<Void> testMethod) {
                def didItFail = false
                try {
                    testMethod()
                } catch (Throwable t) {
                    didItFail = true
                }
                return !didItFail
            }

            void describeTo(Description description) {
                description.appendText("a solved koan")
            }
        }
    }
}
