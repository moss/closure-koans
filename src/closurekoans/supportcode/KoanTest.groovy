package closurekoans.supportcode

import closurekoans.Lesson1_BasicsOfClosures
import closurekoans.Lesson2_ClosingOverEnvironment
import closurekoans.Lesson3_ClosuresAreJustObjects
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
        assertKoan(Lesson2_ClosingOverEnvironment, "closures close over variables, not over values", ['ham', 'pork', 'swiss cheese', 'pickles', 'mustard'])
        assertKoan(Lesson2_ClosingOverEnvironment, "and now, time for a game of Find The Blank", 'lettuce')
        assertKoan(Lesson2_ClosingOverEnvironment, "functions making functions", 'lettuce')
        assertKoanSolvedByImplementingMethod(Lesson2_ClosingOverEnvironment, "setting up a high production volume function factory", "removeIngredient") { ingredient ->
            { sandwich -> sandwich.ingredients.remove(ingredient) }
        }
        assertKoan(Lesson2_ClosingOverEnvironment, "spooky action at a distance", ['peanut butter', 'jelly', 'lime pickle', 'sardines', 'marmite', 'sugar', 'salt'])
    }

    @Test
    void lesson3Test() {
        assertKoan(Lesson3_ClosuresAreJustObjects, "duplicated logic is ugly", 'PEANUT BUTTER')
        assertKoanSolvedByImplementingMethod(Lesson3_ClosuresAreJustObjects, "extracting a method can help", "yellFirstIngredient") { Sandwich sandwich ->
            sandwich.ingredients.first().toUpperCase()
        }
        assertKoan(Lesson3_ClosuresAreJustObjects, "sometimes duplication is more subtle", ['peanut butter', 'jelly'])
        assertKoanSolvedByImplementingMethod(Lesson3_ClosuresAreJustObjects, "a closure can address subtle duplication", "modifyOutsides") { Sandwich sandwich, Closure whatToDoWithTheIngredients ->
            def first = sandwich.ingredients.first()
            def last = sandwich.ingredients.last()
            first = whatToDoWithTheIngredients.call(first)
            last = whatToDoWithTheIngredients.call(last)
            return [first, last]
        }
    }

    private static void assertKoan(Class<? extends ClosureKoans> koanClass, String testName, def solution) {
        assertKoan(koanClass, testName, solution as String) { it.___ = solution }
    }

    private static void assertKoanSolvedByImplementingMethod(Class<? extends ClosureKoans> koanClass, String testName, String methodName, Closure implementation) {
        assertKoan(koanClass, testName, "implementing $methodName") {
            it.metaClass."$methodName" = implementation
        }
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
            @Override
            boolean matchesSafely(Closure<Void> testMethod) {
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
