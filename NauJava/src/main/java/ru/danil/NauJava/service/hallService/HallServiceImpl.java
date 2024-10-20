package ru.danil.NauJava.service.hallService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import ru.danil.NauJava.Entities.HallRow.HallRow;
import ru.danil.NauJava.Repository.hallRepository.HallRepository;
import ru.danil.NauJava.Repository.hallRowRepository.HallRowRepository;

import java.util.List;

@Service
public class HallServiceImpl implements HallService {

    private final HallRepository hallRepository;
    private final HallRowRepository hallRowRepository;
    private final PlatformTransactionManager transactionManager;

    @Autowired
    public HallServiceImpl(HallRepository hallRepository, HallRowRepository hallRowRepository,
                           PlatformTransactionManager transactionManager)
    {
        this.hallRepository = hallRepository;
        this.hallRowRepository = hallRowRepository;
        this.transactionManager = transactionManager;
    }

    @Override
    public void deleteHall(String hallName)
    {

        TransactionStatus status = transactionManager.getTransaction(new
                DefaultTransactionDefinition());
        try {

            List<HallRow> hallRows = hallRowRepository.findHallRowsByHallName(hallName);
            hallRowRepository.deleteAll(hallRows);

            hallRepository.deleteByName(hallName);

            transactionManager.commit(status);
        }
        catch (DataAccessException e) {
            transactionManager.rollback(status);
            throw e;
        }
    }
}
