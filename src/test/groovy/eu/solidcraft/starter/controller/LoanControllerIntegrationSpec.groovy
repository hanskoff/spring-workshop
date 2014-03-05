package eu.solidcraft.starter.controller
import base.IntegrationSpec
import eu.solidcraft.starter.domain.loan.LoanEntity
import eu.solidcraft.starter.domain.loan.LoanEntityRepository
import eu.solidcraft.starter.domain.some.SomeEntity
import eu.solidcraft.starter.domain.some.SomeEntityRepository
import org.springframework.beans.factory.annotation.Autowired

class LoanControllerIntegrationSpec extends IntegrationSpec {
    @Autowired LoanController loanController
    @Autowired LoanEntityRepository loanEntityRepository

    def "add should save entity to DB"() {
        when:
        loanController.add(new LoanEntity(user.username, new BigDecimal(1), new BigDecimal(1), new Date(), 1))

        then:
            List<LoanEntity> entities = loanEntityRepository.findByUsername(user.username)
            entities.size() == 1
            LoanEntity loanEntity = entities.first()
            loanEntity.amount == new BigDecimal(1)
    }

    def "should show my loans"() {
        given:
            loanController.add(new LoanEntity(user.username, new BigDecimal(1), new BigDecimal(1), new Date(), 1))

        when:
            Map response = loanController.index()

        then:
            LoanEntity loanEntity = response.entities.first()
            loanEntity.amount == 1
            loanEntity.username == user.username
    }
}
