package com.timf.teamfreash.model.dto;

import com.timf.teamfreash.model.Complaint;
import lombok.Data;

@Data
public class ComplaintDto {
    private Long id;
    private Long penalty_id;
    private String reason;
    private boolean checked;

    public static ComplaintDto from(Complaint complaint) {
        ComplaintDto complaintDto = new ComplaintDto();
        complaintDto.setId(complaint.getId());
        complaintDto.setPenalty_id(complaint.getPenalty().getId());
        complaintDto.setReason(complaint.getReason());
        complaintDto.setChecked(complaint.isChecked());

        return complaintDto;
    }
}
