package com.timf.teamfreash.model.dto;

import com.timf.teamfreash.model.Compensation;
import com.timf.teamfreash.model.type.IssueType;
import lombok.Data;

@Data
public class CompensationDto {
    private Long id;
    private Long voc_id;
    private IssueType reason;
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
