package closurekoans
import org.hamcrest.*
import org.junit.*
import org.junit.internal.matchers.*

import static org.hamcrest.CoreMatchers.not
import static org.junit.Assert.assertThat

class Lesson1Test {
    @Test
    void theTest() {
        assertKoan(new Lesson1().&"adding up numbers is fun!", 5)
        assertKoan(new Lesson1().&"assigning values to variables", "No it isn't!")
    }

    private void assertKoan(Closure<Void> koan, def solution) {
        assertThat("${name(koan)} starts out unsolved", koan, not(isSolved()))
        SupportCode.___ = solution
        assertThat("${name(koan)} solved by <$solution>", koan, isSolved())
    }

    private String name(Closure<Void> koan) {
        return """"${koan.method}" koan"""
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
