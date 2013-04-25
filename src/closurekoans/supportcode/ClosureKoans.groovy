package closurekoans.supportcode

import org.junit.Before;

abstract class ClosureKoans {
    def ___ = "Please fill in the blank"
    def cubano = new Sandwich('ham', 'pork', 'swiss cheese', 'pickles', 'mustard')
    def pbj = new Sandwich('peanut butter', 'jelly')
    def italian = new Sandwich('salami', 'mortadella', 'capicola', 'cheese', 'tomatoes', 'onions', 'oil', 'vinegar', 'hots')

    @Before void setUpBlanks() {
        Closure.metaClass.___ = { return "Please fill in the blank" }
        ___.metaClass.ingredients = 'Please fill in the blank'
        ___.metaClass.isSandwich = { false }
    }
}
