package com.timf.teamfreash.model.dto;

import com.timf.teamfreash.model.Compensation;
import lombok.Data;

@Data
public class CompensationDto {
    private Long id;
    private Long voc_id;
    private String reason;
    private Long amount;

    public static CompensationDto from(Compensation compensation) {
        CompensationDto compensationDto = new CompensationDto();
        compensationDto.setId(compensation.getId());
        compensationDto.setVoc_id(compensation.getVoc().getId());
        compensationDto.setReason(compensation.getReason());
        compensationDto.setAmount(compensation.getAmount());
        return compensationDto;
    }
}
