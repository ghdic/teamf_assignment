package com.timf.teamfreash.model.dto;

import com.timf.teamfreash.model.Complaint;
import lombok.Data;

@Data
public class ComplaintDto {
    private Long id;
    private Long voc_id;
    private String reason;
    private boolean check;

    public static ComplaintDto from(Complaint complaint) {
        ComplaintDto complaintDto = new ComplaintDto();
        complaintDto.setId(complaint.getId());
        complaintDto.setVoc_id(complaint.getVoc().getId());
        complaintDto.setReason(complaint.getReason());
        complaintDto.setCheck(complaint.isCheck());

        return complaintDto;
    }
}
