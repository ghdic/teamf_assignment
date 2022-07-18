package com.timf.teamfreash.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.timf.teamfreash.model.Compensation;
import com.timf.teamfreash.model.type.IssueType;
import lombok.Data;

@Data
public class CompensationDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private Long voc_id;
    private Long amount;
    private boolean complete;

    public static CompensationDto from(Compensation compensation) {
        CompensationDto compensationDto = new CompensationDto();
        compensationDto.setId(compensation.getId());
        compensationDto.setVoc_id(compensation.getVoc().getId());
        compensationDto.setAmount(compensation.getAmount());
        compensationDto.setComplete(compensation.isComplete());
        return compensationDto;
    }
}
