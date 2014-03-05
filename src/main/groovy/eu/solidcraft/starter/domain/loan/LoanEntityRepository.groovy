package eu.solidcraft.starter.domain.loan

//import eu.solidcraft.starter.domain.some.SomeEntity
//import org.springframework.stereotype.Component

import org.springframework.data.jpa.repository.JpaRepository

interface LoanEntityRepository extends JpaRepository<LoanEntity, Long> {
    List<LoanEntity> findByUsername(String username)
}

//@Component
//class LoanEntityRepository {
//    private List<LoanEntity> userLoans
//
//    LoanEntityRepository() {
//        this.userLoans = new ArrayList<LoanEntity>()
//
//        save(new LoanEntity('test', 1024, 0.05, new Date(), 2));
//        save(new LoanEntity('test', 2048, 0.06, new Date(), 1));
//        save(new LoanEntity('test', 4096, 0.07, new Date(), 1));
//    }
//
//    def save(LoanEntity loan) {
//        userLoans.add(loan)
//    }
//
//    def getByUsername(String username) {
//        return userLoans.findAll{ it.username == username }
//    }
//
//    def getAll() {
//        return userLoans
//    }
//}