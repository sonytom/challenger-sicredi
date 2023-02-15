package br.com.challengersicredi.impl.vote.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document(collection = "vote")
@CompoundIndex(def = "{'userId':1, 'scheduleName':1}", unique = true)
public class VoteModelEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String userId;
    private String name;
    private LocalDateTime votedDate;
    private String voteOptionType;
    private String scheduleName;
}

