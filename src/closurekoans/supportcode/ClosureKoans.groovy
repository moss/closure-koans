package closurekoans.supportcode

import org.junit.Before;

abstract class ClosureKoans {
    def ___ = "Please fill in the blank"

    @Before void setUpBlankMethod() {
        Closure.metaClass.___ = { return "Please fill in the blank" }
    }
}
