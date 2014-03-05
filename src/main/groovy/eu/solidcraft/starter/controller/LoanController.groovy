package eu.solidcraft.starter.controller

import com.google.common.collect.ImmutableMap
import eu.solidcraft.starter.domain.loan.LoanEntity
import eu.solidcraft.starter.domain.loan.LoanEntityRepository
import eu.solidcraft.starter.domain.some.SomeEntity
import eu.solidcraft.starter.domain.some.SomeEntityRepository
import eu.solidcraft.starter.infrastructure.security.LoggedUserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.util.Assert
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

@Controller
class LoanController {
    private LoanEntityRepository loanEntityRepository;
    private LoggedUserRepository loggedUserRepository;

    @Autowired
    LoanController(LoanEntityRepository loanEntityRepository, LoggedUserRepository loggedUserRepository) {
        this.loanEntityRepository = loanEntityRepository;
        this.loggedUserRepository = loggedUserRepository;
    }

    @RequestMapping(value = "/loan/list", method = RequestMethod.GET)
    Map index() {
        List<LoanEntity> entities = loanEntityRepository.findByUsername(loggedUserRepository.getLoggedUserName());
        return ImmutableMap.of("entities", entities);
    }

    @RequestMapping(value = "/loan/add", consumes="application/json")
    void add(@RequestBody LoanEntity loan) {
        Assert.notNull(loan);
        loanEntityRepository.save(loan);
    }

    @RequestMapping(value = "/loan/extend", consumes="application/json")
    Map extend(@RequestBody LoanEntity loan) {
        Assert.notNull(loan);
        if (loan.extensions != null) loan.extensions++
        else loan.extensions = 1
        loanEntityRepository.save(loan);
        return ImmutableMap.of("entity", loan);
    }
}
