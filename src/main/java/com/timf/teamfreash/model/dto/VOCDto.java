package com.timf.teamfreash.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.timf.teamfreash.model.*;
import com.timf.teamfreash.model.type.ClientType;
import com.timf.teamfreash.service.ClientStrategy;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.annotation.Transient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class VOCDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    // response, request 분리하는거도 방법
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private long complainerId;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private ClientType complainerType;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private long defendantId;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private ClientType defendantType;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Object complainer;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Object defendant;

    private String reason;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<CompensationDto> penalty = new ArrayList<>();
    private boolean isCompleted;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<ComplaintDto> protest = new ArrayList<>();

    public static VOCDto from(VOC voc, Object complainer, Object defendant) {
        VOCDto vocDto = new VOCDto();
        vocDto.setId(voc.getId());
        vocDto.setComplainer(complainer);
        vocDto.setDefendant(defendant);
        vocDto.setReason(voc.getReason());
        if(voc.getPenalty() != null)
            vocDto.setPenalty(voc.getPenalty().stream().map(CompensationDto::from).collect(Collectors.toList()));
        if(voc.getPenalty() != null)
            vocDto.setProtest(voc.getProtest().stream().map(ComplaintDto::from).collect(Collectors.toList()));
        vocDto.setCompleted(voc.isCompleted());
        return vocDto;
    }


}
