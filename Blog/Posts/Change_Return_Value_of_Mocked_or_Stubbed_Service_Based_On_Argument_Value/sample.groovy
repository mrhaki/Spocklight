import spock.lang.*

public class CandyServiceSpecification extends Specification {

    private CandyService candyService = new CandyServiceImpl()
 
    def setup() {
        candyService.chocolateService = Stub(ChocolateService) {
            getCandiesLikeByCustomer(_) &gt;&gt; { Customer customer -&gt;
                customer?.firstName == 'Albert'
            }
        }
    }

    def "Customer Albert really likes chocolate"() {
        given:
        final Customer customer = new Customer(firstName: 'Albert')
        
        expect: 'Albert likes chocolate'
        candyService.getCandiesLikeByCustomer(customer).contains Candy.CHOCOLATE
    }

    def "Other customer do not like chocolate"() {
        given:
        final Customer customer = new Customer(firstName: 'Any other firstname')
        
        expect: 'Customer does not like chocolate'
        !candyService.getCandiesLikeByCustomer(customer).contains(Candy.CHOCOLATE)
    }

}
