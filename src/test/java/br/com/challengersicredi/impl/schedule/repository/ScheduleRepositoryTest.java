package br.com.challengersicredi.impl.schedule.repository;

import br.com.challengersicredi.commons.enums.ScheduleStatusEnum;
import br.com.challengersicredi.impl.schedule.entity.ScheduleModelEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.test.StepVerifier;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
public class ScheduleRepositoryTest {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private ReactiveMongoTemplate reactiveMongoTemplate;

    @Test
    void testFindByName() {

        var name = generateRandomName();
        var scheduleModelEntity = new ScheduleModelEntity();
        scheduleModelEntity.setName(name);
        scheduleModelEntity.setStatus(ScheduleStatusEnum.OPEN);

        reactiveMongoTemplate.save(scheduleModelEntity).block();

        // Test the findByName method
        var foundScheduleMono = scheduleRepository.findByName(name);

        StepVerifier.create(foundScheduleMono)
                .assertNext(schedule -> {
                    assertEquals(name, schedule.getName());
                    assertEquals(ScheduleStatusEnum.OPEN, schedule.getStatus());
                })
                .verifyComplete();
    }

    public static String generateRandomName() {
        String[] FIRST_NAMES = {
                "John", "Alice", "Michael", "Emma", "David", "Olivia", "James", "Sophia", "Robert", "Isabella", "William", "Ava",
                "Richard", "Mia", "Charles", "Abigail", "Joseph", "Emily", "Thomas", "Ella", "Daniel", "Scarlett", "Matthew", "Grace",
                "Andrew", "Madison", "George", "Amelia", "Elizabeth", "Benjamin", "Charlotte", "Samuel", "Lily", "Joshua", "Chloe",
                "Christopher", "Nora", "Ethan", "Sofia", "Josephine", "Victoria", "Anthony", "Harper", "Ryan", "Evelyn", "Aiden", "Anna"
        };

        String[] LAST_NAMES = {
                "Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Anderson", "Thomas", "Jackson",
                "White", "Harris", "Martin", "Thompson", "Garcia", "Martinez", "Robinson", "Clark", "Rodriguez", "Lewis", "Lee",
                "Walker", "Hall", "Allen", "Young", "Hernandez", "King", "Wright", "Lopez", "Hill", "Scott", "Green", "Adams",
                "Baker", "Gonzalez", "Nelson", "Carter", "Mitchell", "Perez", "Roberts", "Turner", "Phillips", "Campbell", "Parker"
        };
        Random random = new Random();
        var firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
        var lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];

        return firstName + " " + lastName;
    }
}