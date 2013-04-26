package closurekoans.supportcode

import org.junit.Before;

abstract class ClosureKoans {
    def ___ = "Please fill in the blank"
    def ____ = { "Please fill in the blank" }
    def cubano = new Sandwich('ham', 'pork', 'swiss cheese', 'pickles', 'mustard')
    def pbj = new Sandwich('peanut butter', 'jelly')
    def italian = new Sandwich('salami', 'mortadella', 'capicola', 'cheese', 'tomatoes', 'onions', 'oil', 'vinegar', 'hots')
    def portobello = new Sandwich('portobello mushrooms', 'jack cheese', 'pesto')
    def secretIngredient = 'lime pickle'

    @Before void setUpBlanks() {
        Closure.metaClass.___ = { return "Please fill in the blank" }
        ___.metaClass.ingredients = 'Please fill in the blank'
        ___.metaClass.isSandwich = { false }
    }
}
