package ru.danil.NauJava.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.danil.NauJava.Entities.Hall.Hall;
import ru.danil.NauJava.Entities.HallRow.HallRow;
import ru.danil.NauJava.Repository.hallRepository.HallRepository;
import ru.danil.NauJava.Repository.hallRowRepository.HallRowRepository;
import ru.danil.NauJava.service.hallService.HallService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@SpringBootTest
class HallTest {
    private final HallService hallService;
    private final HallRowRepository rowRepository;
    private final HallRepository hallRepository;

    @Autowired
    private HallRowRepository hallRowRepository;

    @Autowired
    public HallTest(HallRepository hallRepository, HallRowRepository hallRowRepository,
                    HallService hallService) {
        this.hallRepository = hallRepository;
        this.hallService = hallService;
        this.rowRepository = hallRowRepository;

    }

    @Test
    void testDeleteHallInTx() {
        Hall mainHall = new Hall();
        mainHall.setName(UUID.randomUUID().toString());
        hallRepository.save(mainHall);

        Set<Integer> existingRows = new HashSet<>();

        HallRow mainHallRow1 = createUniqueHallRow(mainHall, existingRows);
        HallRow mainHallRow2 = createUniqueHallRow(mainHall, existingRows);

        rowRepository.save(mainHallRow1);
        rowRepository.save(mainHallRow2);

        Assertions.assertNotNull(rowRepository.findByRowAndHall(mainHallRow1.getRow(), mainHall));
        Assertions.assertNotNull(rowRepository.findByRowAndHall(mainHallRow2.getRow(), mainHall));

        hallService.deleteHall(mainHall.getName());

        Assertions.assertFalse(hallRepository.findById(mainHall.getId()).isPresent());

        Optional<HallRow> foundRow1 = hallRowRepository.findById(mainHallRow1.getId());
        Assertions.assertTrue(foundRow1.isEmpty());

        Optional<HallRow> foundRow2 = hallRowRepository.findById(mainHallRow2.getId());
        Assertions.assertTrue(foundRow2.isEmpty());


    }

    private HallRow createUniqueHallRow(Hall hall, Set<Integer> existingRows) {
        HallRow hallRow = new HallRow();
        int rowNum;
        do {
            rowNum = (int) (Math.random() * 15) + 1;
        } while (!existingRows.add(rowNum));
        hallRow.setRow(rowNum);
        hallRow.setHall(hall);
        return hallRow;
    }

}
