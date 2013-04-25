package closurekoans.supportcode

class Sandwich {
    def ___ = 'Please fill in the blank.'
    List<String> ingredients

    Sandwich(String... ingredients) {
        this.ingredients = ingredients as ArrayList
    }

    boolean isSandwich() {
        true
    }

    def get____() {
        ingredients.add('Please fill in the blank.')
    }
}
