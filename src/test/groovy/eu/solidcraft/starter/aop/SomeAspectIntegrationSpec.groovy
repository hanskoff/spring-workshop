
package eu.solidcraft.starter.aop
import base.IntegrationSpec
import eu.solidcraft.starter.domain.loan.LoanEntityRepository
import eu.solidcraft.starter.infrastructure.security.LoggedUserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ActiveProfiles

class SomeAspectIntegrationSpec extends IntegrationSpec {
    @Autowired LoanEntityRepository loanEntityRepository;

    def "returned user should have Aspect info attached"() {
        expect:
        loanEntityRepository.getByUsername('test').size() == 4
    }
}
