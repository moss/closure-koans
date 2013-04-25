package closurekoans.supportcode

import closurekoans.Lesson1_BasicsOfClosures
import closurekoans.Lesson2_ClosingOverEnvironment
import org.hamcrest.*
import org.junit.*
import org.junit.internal.matchers.*

import static org.hamcrest.CoreMatchers.not
import static org.junit.Assert.assertThat

class KoanTest {
    @Test
    void lesson1Test() {
        assertKoan(Lesson1_BasicsOfClosures, "assigning values to variables", ['ham', 'pork', 'swiss cheese', 'pickles', 'mustard'])
        assertKoan(Lesson1_BasicsOfClosures, "calling a closure", "ingredients") { it.pbj.___ = it.pbj.ingredients }
        assertKoan(Lesson1_BasicsOfClosures, "calling a closure implicitly", "pbj") { it.___ = it.pbj }
        assertKoan(Lesson1_BasicsOfClosures, "a closure does nothing until you call it", "pbj") { it.___ = it.pbj }
        assertKoan(Lesson1_BasicsOfClosures, "a closure does nothing until you call it", "cubano") { it.___ = it.cubano }
        assertKoan(Lesson1_BasicsOfClosures, "returning a value from a closure", "jelly")
        assertKoan(Lesson1_BasicsOfClosures, "returning a value implicitly", ['salami', 'mortadella', 'capicola', 'cheese', 'tomatoes', 'onions', 'oil', 'vinegar', 'hots'])
        assertKoan(Lesson1_BasicsOfClosures, "closures can take arguments", ['ham', 'pork', 'swiss cheese', 'pickles', 'mustard'])
        assertKoan(Lesson1_BasicsOfClosures, "closures can take an implicit argument", "italian") { it.___ = it.italian }
        assertKoan(Lesson1_BasicsOfClosures, "see what happens if you leave out the implicit argument", "null") { it.___ = null }
        assertKoan(Lesson1_BasicsOfClosures, "a closure can be a tiny expression or a multi-line monstrosity", 'HAM!!!')
        assertKoan(Lesson1_BasicsOfClosures, "a closure has access to ('closes over') its environment", ['portobello mushrooms', 'jack cheese', 'pesto'])
    }

    @Test
    void lesson2Test() {
        assertKoan(Lesson2_ClosingOverEnvironment, "a closure has access to ('closes over') its environment", ['portobello mushrooms', 'jack cheese', 'pesto'])
        assertKoan(Lesson2_ClosingOverEnvironment, "watch how things change", ['ham', 'pork', 'swiss cheese', 'pickles', 'mustard'])
        assertKoan(Lesson2_ClosingOverEnvironment, "I'm changing the environment -- ask me how!", ['peanut butter', 'jelly', 'lime pickle'])
    }

    private static void assertKoan(Class<? extends ClosureKoans> koanClass, String testName, def solution) {
        assertKoan(koanClass, testName, solution as String) { it.___ = solution }
    }

    private static void assertKoan(Class<? extends ClosureKoans> koanClass, String testName, String description, Closure solution) {
        ClosureKoans koan = koanClass.newInstance()
        Closure<Void> koanMethod = koan.&"$testName"
        assertThat("${testName} starts out unsolved", koanMethod, not(isSolved()))

        koan = koanClass.newInstance()
        koanMethod = koan.&"$testName"
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
