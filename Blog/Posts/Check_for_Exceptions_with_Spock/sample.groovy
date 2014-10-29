package com.mrhaki.blog

@Grab('org.spockframework:spock-core:0.5-groovy-1.7')
import spock.lang.Specification

class RecipeServiceSpec extends Specification {
    def "If cooking for more minutes than maximum allowed by recipe throw BurnedException"() {
        setup:
            def recipeService = new RecipeService()
            def recipe = new Recipe(time: 5, device: 'oven')

        when:
            recipeService.cook(recipe, 10, 'oven')

        then:
            thrown BurnedException
    }

    def "If cooking on wrong device throw InvalidDeviceException"() {
        setup:
            def recipeService = new RecipeService()
            def recipe = new Recipe(device: 'oven', time: 10)

        when:
            recipeService.cook(recipe, 2, 'microwave')

        then:
            InvalidDeviceException ex = thrown()
            // Alternative syntax: def ex = thrown(InvalidDeviceException)
            ex.message == 'Please use oven for this recipe.'
    }

    def """If the recipe is cooked on the right device and
           for the correct amount of minutes, 
           then no exception is thrown"""() {
        setup:
            def recipeService = new RecipeService()
            def recipe = new Recipe(device: 'oven', time: 30)

        when:
            recipeService.cook(recipe, 30, 'oven')

        then:
            notThrown BurnedException
            notThrown InvalidDeviceException
    }
}

class RecipeService {
    def cook(Recipe recipe, int minutes, String device) {
        if (minutes > recipe.time) {
            throw new BurnedException()
        }
        if (device != recipe.device) {
            throw new InvalidDeviceException("Please use $recipe.device for this recipe.")
        }
    }
}

class Recipe {
    int time
    String device
}

class BurnedException extends RuntimeException {
    BurnedException(String message) {
        super(message)
    }
}

class InvalidDeviceException extends RuntimeException {
    InvalidDeviceException(String message) {
        super(message)
    }
}
