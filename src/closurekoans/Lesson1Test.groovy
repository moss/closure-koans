package closurekoans

import org.junit.*

class Lesson1Test {
    @Test
    void theTest() {
        assertKoan(new Lesson1().&definingAString, 'microwaving')
        assertKoan(new Lesson1().&callingAClosureExplicitly, 'poodles')
        assertKoan(new Lesson1().&callingAClosureImplicitly, 'is inappropriate')
    }

    private void assertKoan(Closure<Void> koan, String solution) {
        assertNotSolved(koan, true)
        SupportCode.___ = solution
        assertNotSolved(koan, false)
    }

    void assertNotSolved(Closure<Void> testMethod, boolean expectedToFail) {
        def didItFail = false
        try {
            testMethod()
        } catch (Throwable t) {
            didItFail = true
        }
        assert didItFail == expectedToFail
    }
}
