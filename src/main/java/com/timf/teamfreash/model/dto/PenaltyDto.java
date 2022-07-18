package com.timf.teamfreash.model.dto;

import com.timf.teamfreash.model.Complaint;
import com.timf.teamfreash.model.Penalty;
import com.timf.teamfreash.model.type.IssueType;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class PenaltyDto {
    private long id;
    private long voc_id;
    private IssueType reason;
    private long price;
    private long amount;
    private boolean checked;
    private List<ComplaintDto> complaintList = new ArrayList<>();

    public static PenaltyDto from(Penalty penalty) {
        PenaltyDto penaltyDto = new PenaltyDto();
        penaltyDto.setId(penalty.getId());
        penaltyDto.setVoc_id(penalty.getVoc().getId());
        penaltyDto.setReason(penalty.getReason());
        penaltyDto.setPrice(penalty.getPrice());
        penaltyDto.setAmount(penalty.getAmount());
        penaltyDto.setChecked(penalty.isChecked());
        penaltyDto.setComplaintList(penalty.getComplaintList().stream().map(ComplaintDto::from).collect(Collectors.toList()));

        return penaltyDto;
    }
}
